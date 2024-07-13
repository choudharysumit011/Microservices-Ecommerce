package com.ecommerce.orderservice.Service;

import com.ecommerce.orderservice.Config.FeignClient;
import com.ecommerce.orderservice.DTO.InventoryResponse;
import com.ecommerce.orderservice.DTO.OrderLineItemsDto;
import com.ecommerce.orderservice.DTO.OrderRequest;
import com.ecommerce.orderservice.Model.Order;
import com.ecommerce.orderservice.Model.OrderLineItems;
import com.ecommerce.orderservice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final FeignClient feignClient;




    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

       List<OrderLineItems> orderLineItemsList= orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto).toList();
       order.setOrderLineItemsList(orderLineItemsList);
        List<String> skuCodes = orderLineItemsList.stream()
                .map(OrderLineItems::getSkuCode)
                .collect(Collectors.toList());

        // Check if the products exist in the inventory
        List<InventoryResponse> inventoryResponses = feignClient.isProductExist(skuCodes);

        boolean allProductsExist = inventoryResponses.stream()
                .allMatch(InventoryResponse::isInStock);

        // If all products exist, save the order
        if (allProductsExist) {
            orderRepository.save(order);
            log.info("Order Placed Successfully");
        } else {
            throw new RuntimeException("One or more products are out of stock.");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
      return   OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();
    }
}
