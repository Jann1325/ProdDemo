package com.aic.proddemo.service;

import com.aic.proddemo.domain.Prod;
import com.aic.proddemo.repository.ProdRepository;
import com.aic.proddemo.web.vm.CartItemVM;
import com.aic.proddemo.web.vm.ProdVM;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CheckoutService {

    private Jedis jedis;
    @Autowired
    private ProdRepository prodRepository;
    @Autowired
    private ProdVMConverter prodVMConverter;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    public CheckoutService() {
        jedis = new Jedis("localhost", 6379);
        jedis.select(1);
    }

    public void processCheckout(Map<String, Object> requestBody, HttpServletRequest request) {
        String[] selectedProducts = objectMapper.convertValue(requestBody.get("selectedProducts"), String[].class);
        boolean isDirectBuy = (boolean) requestBody.get("isDirectBuy");

        HttpSession session = request.getSession();
        session.setAttribute("selectedProducts", selectedProducts);
        session.setAttribute("isDirectBuy", isDirectBuy);
    }


    public String getCheckoutData(String accId, HttpServletRequest request) {
        String cartKey = "cart:user" + accId;
        HttpSession session = request.getSession();
        String[] selectedProducts = (String[]) session.getAttribute("selectedProducts");

        Map<String, CartItemVM> cartItems = new HashMap<>();
        boolean isDirectBuy = (boolean) session.getAttribute("isDirectBuy");

        if (isDirectBuy && selectedProducts.length > 0) {
            int prodId = Integer.parseInt(selectedProducts[0]);
            Prod prod = prodRepository.getById(prodId);
            Object[] prodValues = new Object[]{
                    prod.getProdId(),
                    prod.getRestaurantId(),
                    prod.getProdName(),
                    prod.getProdText(),
                    prod.getProdUserguide(),
                    prod.getProdPrice(),
                    prod.getProdQty(),
                    prod.getProdState(),
                    prod.getProdPic(),
                    prod.getProdCommentNumber(),
                    prod.getProdCommentScore()
            };
            ProdVM prodVO = prodVMConverter.convert(prodValues);

            CartItemVM cartItem = new CartItemVM();
            cartItem.setProductId(String.valueOf(prodVO.getProdId()));
            String productName = prodVO.getResName() + "｜" + prodVO.getProdName();
            cartItem.setProductName(productName);
            cartItem.setPrice(prodVO.getProdPrice());
            cartItem.setQty(1);
            cartItem.setProdPic(prodVO.getProdPic());

            cartItems.put(String.valueOf(prodVO.getProdId()), cartItem);
        } else {
            for (String productKey : selectedProducts) {
                jedis.select(1);
                String productData = jedis.hget(cartKey, "productId:" + productKey);

                if (productData != null) {
                    String productId = "productId:" + productKey;
                    String cartItemJson = productData;
                    String redisValue = productId + ", " + cartItemJson;
                    CartItemVM cartItem = convertToCartItem(redisValue);
                    cartItems.put(extractProductId(productId), cartItem);

                    Integer prodId = Integer.parseInt(productKey);
                    Prod prod = prodRepository.getById(prodId);
                    byte[] imageBytes = prod.getProdPic();
                    cartItem.setProdPic(imageBytes);
                } else {
                    System.out.println("未找到商品：" + productKey);
                }
            }
        }

        try {
            return objectMapper.writeValueAsString(cartItems);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    private static String extractProductId(String productId) {
        Pattern pattern = Pattern.compile("productId:(\\d+)");
        Matcher matcher = pattern.matcher(productId);


        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null; // 無法找到productId，返回null或其他適當的預設值
        }

    }

    public static CartItemVM convertToCartItem(String keyValueString) {
        CartItemVM cartItem = new CartItemVM();
        String[] keyValuePairs = keyValueString.split(", ");
        for (String keyValuePair : keyValuePairs) {
            String[] keyValue = keyValuePair.split(":");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            if (key.equals("productId")) {
                cartItem.setProductId(value);
            } else if (key.equals("name")) {
                cartItem.setProductName(value);
            } else if (key.equals("price")) {
                cartItem.setPrice(Double.parseDouble(value));
            } else if (key.equals("qty")) {
                cartItem.setQty(Integer.parseInt(value));
            }
        }
        return cartItem;
    }

}
