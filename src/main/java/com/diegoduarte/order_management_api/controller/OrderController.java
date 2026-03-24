package com.diegoduarte.order_management_api.controller;

import com.diegoduarte.order_management_api.business.OrderService;
import com.diegoduarte.order_management_api.business.dto.order.OrderRequestDTO;
import com.diegoduarte.order_management_api.business.dto.order.OrderResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Order management APIs")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Create order")
    @ApiResponse(responseCode = "200", description = "Order created")
    public ResponseEntity<OrderResponseDTO> createOrder(
            @RequestBody OrderRequestDTO dto,
            @RequestParam Long userId) {

        return ResponseEntity.ok(orderService.createOrder(dto, userId));
    }

    @GetMapping
    @Operation(summary = "Get all orders")
    @ApiResponse(responseCode = "200", description = "Orders list")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID")
    @ApiResponse(responseCode = "200", description = "Order found")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PatchMapping("/{id}/cancel")
    @Operation(summary = "Cancel order")
    @ApiResponse(responseCode = "200", description = "Order cancelled")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<Void> cancelOrderById(@PathVariable Long id) {
        orderService.cancelOrderById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/pay")
    @Operation(summary = "Pay order")
    @ApiResponse(responseCode = "200", description = "Order paid")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<Void> payOrder(@PathVariable Long id) {
        orderService.payOrder(id);
        return ResponseEntity.ok().build();
    }
}