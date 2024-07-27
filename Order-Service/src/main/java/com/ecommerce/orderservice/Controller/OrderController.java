package com.ecommerce.orderservice.Controller;

import com.ecommerce.orderservice.DTO.OrderRequest;
import com.ecommerce.orderservice.Service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest){
       return orderService.placeOrder(orderRequest);
       // return "Order Placed";
    }

    public ResponseEntity<String> fallbackMethod(OrderRequest orderRequest,Throwable throwable){
        return ResponseEntity.internalServerError().body("Server down, please try again later");
    }
}
