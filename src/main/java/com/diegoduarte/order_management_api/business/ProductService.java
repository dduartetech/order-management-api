package com.diegoduarte.order_management_api.business;

import com.diegoduarte.order_management_api.business.dto.product.ProductRequestDTO;
import com.diegoduarte.order_management_api.business.dto.product.ProductResponseDTO;
import com.diegoduarte.order_management_api.business.mapper.AppMapper;
import com.diegoduarte.order_management_api.infrastructure.entity.ProductEntity;
import com.diegoduarte.order_management_api.infrastructure.exceptions.ResourceNotFoundException;
import com.diegoduarte.order_management_api.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final AppMapper appMapper;

    public ProductResponseDTO createProduct (ProductRequestDTO productRequestDTO) {
        ProductEntity product = appMapper.toEntity(productRequestDTO);
        return appMapper.toResponse(productRepository.save(product));
    }

    public List<ProductResponseDTO> getAllProducts () {
        return productRepository.findAll()
                .stream()
                .map(appMapper::toResponse)
                .toList();
    }

    public ProductResponseDTO getProductById (Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow(() ->
        new ResourceNotFoundException("Produto não localizado"));

        return appMapper.toResponse(product);
    }

    public ProductResponseDTO updateProductById(Long id, ProductRequestDTO productRequestDTO) {

        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não localizado"));

        appMapper.updateProductFromDto(productRequestDTO, product);

        return appMapper.toResponse(productRepository.save(product));
    }

    public void deleteProductById(Long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não localizado"));

        productRepository.delete(product);
    }

}
