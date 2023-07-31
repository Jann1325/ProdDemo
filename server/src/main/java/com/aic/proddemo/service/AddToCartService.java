package com.aic.proddemo.service;

import com.aic.proddemo.domain.Prod;
import com.aic.proddemo.repository.ProdRepository;
import com.aic.proddemo.web.vm.CartItemVM;
import com.aic.proddemo.web.vm.ProdVM;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import redis.clients.jedis.Jedis;

@Service
public class AddToCartService {

    @Autowired
    private ProdRepository prodRepository;
    @Autowired
    private ProdVMConverter prodVoConverter;
    private Jedis jedis = new Jedis("localhost", 6379);

    public String addToCart(Map<String, String> requestParams) {
        String operation = requestParams.get("operation");
        String productId = requestParams.get("productId");
        String productName = requestParams.get("productName");
        int price = Integer.parseInt(requestParams.get("price"));
        int qty = Integer.parseInt(requestParams.get("qty"));
        int accId = Integer.parseInt(requestParams.get("accId"));
        String cartKey = "cart:user" + accId;

        switch (operation) {
            case "deleteSelected":
                jedis.select(1);
                jedis.hdel(cartKey, "productId:" + productId);
                return "{\"status\": \"success\"}";

            case "updateQuantity":
                String cartItemJson = "name:" + productName + ", price:" + price + ", qty:" + qty;
                jedis.select(1);
                jedis.hset(cartKey, "productId:" + productId, cartItemJson);
                return "{\"status\": \"success\"}";

            case "addcart":
                jedis.select(1);
                jedis.hset(cartKey, "productId:" + productId, "name:" + productName + ", price:" + price + ", qty:" + qty);
                return "{\"status\": \"success\"}";

            default:
                return "{\"status\": \"error\"}";
        }
    }

    public String getCart(int accId) {
        String cartKey = "cart:user" + accId;
        jedis.select(1);
        Map<String, String> cartData = jedis.hgetAll(cartKey);
        if (accId != 0) {
            String oldCartKey = "cart:user0";
            Map<String, String> oldCartData = jedis.hgetAll(oldCartKey);
            cartData.putAll(oldCartData);
            jedis.del(oldCartKey);
        }
        Map<String, CartItemVM> cartItems = new HashMap<>();

        for (Map.Entry<String, String> entry : cartData.entrySet()) {
            String productId = entry.getKey();
            String cartItemJson = entry.getValue();
            String redisValue = productId + ", " + cartItemJson;

            String[] parts = productId.split(":");
            String numberPart = parts[1].trim();
            Integer prodId = Integer.parseInt(numberPart);
            Prod prod = prodRepository.getById(prodId);
            //  ProdVM prodVM = prodVoConverter.convert(prod);
            byte[] imageBytes = prod.getProdPic();

            CartItemVM cartItem = convertToCartItem(redisValue);
            cartItem.setProdPic(imageBytes);
            cartItems.put(extractProductId(productId), cartItem);
        }

        // 使用Jackson的ObjectMapper轉換json
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResult;
        try {
            jsonResult = objectMapper.writeValueAsString(cartItems);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            jsonResult = "{}";
        }

        return jsonResult;
    }

    private String extractProductId(String productId) {
        Pattern pattern = Pattern.compile("productId:(\\d+)");
        Matcher matcher = pattern.matcher(productId);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null; // 無法找到productId，返回null或其他適當的預設值
        }
    }

    public CartItemVM convertToCartItem(String keyValueString) {
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

    public void destroy() {
        jedis.close();
    }
}
