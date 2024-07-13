package com.ecommerce.orderservice.Service;

import com.ecommerce.orderservice.DTO.OrderLineItemsDto;
import com.ecommerce.orderservice.DTO.OrderRequest;
import com.ecommerce.orderservice.Model.Order;
import com.ecommerce.orderservice.Model.OrderLineItems;
import com.ecommerce.orderservice.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;



    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

       List<OrderLineItems> orderLineItemsList= orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto).toList();
       order.setOrderLineItemsList(orderLineItemsList);
       orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
      return   OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();
    }
}
