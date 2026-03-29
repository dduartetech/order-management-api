package com.diegoduarte.order_management_api.business;

import com.diegoduarte.order_management_api.business.dto.order.OrderItemRequestDTO;
import com.diegoduarte.order_management_api.business.dto.order.OrderRequestDTO;
import com.diegoduarte.order_management_api.business.dto.order.OrderResponseDTO;
import com.diegoduarte.order_management_api.business.dto.product.ProductResponseDTO;
import com.diegoduarte.order_management_api.business.mapper.AppMapper;
import com.diegoduarte.order_management_api.infrastructure.entity.OrderEntity;
import com.diegoduarte.order_management_api.infrastructure.entity.OrderItemEntity;
import com.diegoduarte.order_management_api.infrastructure.entity.ProductEntity;
import com.diegoduarte.order_management_api.infrastructure.entity.UserEntity;
import com.diegoduarte.order_management_api.infrastructure.enums.StatusEnum;
import com.diegoduarte.order_management_api.infrastructure.exceptions.ResourceNotFoundException;
import com.diegoduarte.order_management_api.infrastructure.repository.OrderRepository;
import com.diegoduarte.order_management_api.infrastructure.repository.ProductRepository;
import com.diegoduarte.order_management_api.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final AppMapper appMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderResponseDTO createOrder (OrderRequestDTO dto, Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Usuário não encontrado."));

        OrderEntity order = new OrderEntity();
        order.setStatus(StatusEnum.CREATED);
        order.setUser(user);

        List<OrderItemEntity> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequestDTO itemDTO : dto.getItems()) {
            ProductEntity product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

            if (product.getStock() < itemDTO.getQuantity()) {
                throw new RuntimeException("Estoque insuficiente");
            }

            product.setStock(product.getStock() - itemDTO.getQuantity());

            OrderItemEntity item = new OrderItemEntity();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(product.getPrice());

            total = total.add(
                    product.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()))
            );

            items.add(item);
        }

        order.setItems(items);
        order.setTotal(total);

        orderRepository.save(order);
        return appMapper.toResponse(order);
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(appMapper::toResponse)
                .toList();
    }

    public OrderResponseDTO getOrderById(Long id) {
        return appMapper.toResponse(orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Pedido não localizado")));
    }

    public void cancelOrderById (Long id) {
        OrderEntity entity = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Pedido não encontrado."));
        entity.setStatus(StatusEnum.CANCELLED);
        orderRepository.save(entity);
    }

    public void payOrder(Long id) {
       OrderEntity entity = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Pedido não localizado"));
        entity.setStatus(StatusEnum.PAID);
        orderRepository.save(entity);
    }
}
