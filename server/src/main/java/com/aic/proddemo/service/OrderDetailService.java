package com.aic.proddemo.service;

import com.aic.proddemo.domain.Order;
import com.aic.proddemo.domain.OrderDetail;
import com.aic.proddemo.domain.ProdOrderDetail;
import com.aic.proddemo.repository.OrderDetailRepository;
import com.aic.proddemo.repository.OrderRepository;
import com.aic.proddemo.repository.ProdOrderDetailRepository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

@Service
public class OrderDetailService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProdOrderDetailRepository prodOrderDetailRepository;
	private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int CODE_LENGTH = 16;
	private static final SecureRandom random = new SecureRandom();
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	public String createOrder(JsonNode requestBody) {
		JsonNode jsonData = requestBody.get("cartData");
		int accId = requestBody.get("memberId").asInt();
		String originalAmountStr = requestBody.get("originalAmount").asText().replaceAll("[^0-9]", "");
		String checkoutAmountStr = requestBody.get("checkoutAmount").asText().replaceAll("[^0-9]", "");
		int originalAmount = Integer.parseInt(originalAmountStr);
		int checkoutAmount = Integer.parseInt(checkoutAmountStr);

		// 訂單物件
		Order order = new Order();
		order.setAccId(accId);
		order.setOrderState(0);
		order.setAmountBeforeCoupon(originalAmount);
		order.setFinalAmount(checkoutAmount);
		order.setOrderInvoice(generateRandomInvoice());

		// 儲存訂單
		Order savedOrder = orderRepository.save(order);
		if (savedOrder != null && savedOrder.getOrderId() > 0) {
			int generatedOrderId = savedOrder.getOrderId();
			System.out.println("訂單建立成功！ORDER_ID為：" + generatedOrderId);

			// 儲存訂單明細和商品訂單明細
			Iterator<Map.Entry<String, JsonNode>> productIterator = jsonData.fields();
			while (productIterator.hasNext()) {
				Map.Entry<String, JsonNode> entry = productIterator.next();
				String productId = entry.getKey();
				JsonNode productData = entry.getValue();
				int qty = productData.get("qty").asInt();
				String unipriceStr = productData.get("uniprice").asText().replaceAll("[^0-9]", "");
				int uniprice = Integer.parseInt(unipriceStr);

				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrderId(generatedOrderId);
				orderDetail.setProdId(Integer.parseInt(productId));
				orderDetail.setProdPrice(uniprice);
				orderDetail.setOrderProdQty(qty);

				// 儲存訂單明細
				OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
				if (savedOrderDetail != null && savedOrderDetail.getOrderDetailId() > 0) {
					int generatedOrderDetailId = savedOrderDetail.getOrderDetailId();
					System.out.println("訂單明細建立成功！ORDER_DETAIL_ID為：" + generatedOrderDetailId);

					// 儲存商品訂單明細
					ProdOrderDetail prodOrderDetail = new ProdOrderDetail();
					prodOrderDetail.setOrderDetailId(generatedOrderDetailId);
					prodOrderDetail.setProdOrderCode(generateRandomCode());
					prodOrderDetail.setProdOrderState(0);

					ProdOrderDetail savedProdOrderDetail = prodOrderDetailRepository.save(prodOrderDetail);
					if (savedProdOrderDetail != null && savedProdOrderDetail.getProdOrderDetailId() > 0) {
						int generatedProdOrderDetailId = savedProdOrderDetail.getProdOrderDetailId();
						System.out.println("商品訂單明細建立成功！PROD_ORDER_DETAIL_ID為：" + generatedProdOrderDetailId);
					} else {
						System.out.println("無法建立商品訂單明細。");
					}
				} else {
					System.out.println("無法建立訂單明細。");
				}
			}
		} else {
			System.out.println("無法建立訂單。");
		}


		// 刪除Redis相應資料
		try (Jedis jedis = new Jedis("localhost", 6379)) {
			String cartKey = "cart:user" + accId;
			jedis.select(1);

			// 使用Jackson的ObjectMapper解析JSON字串
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode cartData = objectMapper.readTree(jsonData.toString());

			for (JsonNode productNode : cartData) {
				String productId = productNode.asText();
				String redisKey = "productId:" + productId;
				jedis.hdel(cartKey, redisKey);
				System.out.println("已從Redis刪除資料，鍵值：" + redisKey);
			}

			return "{\"status\": \"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"status\": \"error\"}";
		}
	}

	private static String generateRandomCode() {
		StringBuilder sb = new StringBuilder(CODE_LENGTH);
		for (int i = 0; i < CODE_LENGTH; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			char randomChar = CHARACTERS.charAt(randomIndex);
			sb.append(randomChar);
		}
		return sb.toString();
	}

	private String generateRandomInvoice() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		char firstChar = characters.charAt(random.nextInt(characters.length()));
		char secondChar = characters.charAt(random.nextInt(characters.length()));
		String numbers = String.format("%08d", random.nextInt(100000000));
		return firstChar + "" + secondChar + "-" + numbers;
	}

}
