package com.diegoduarte.order_management_api.business;

import com.diegoduarte.order_management_api.business.dto.user.UserRequestDTO;
import com.diegoduarte.order_management_api.business.dto.user.UserResponseDTO;
import com.diegoduarte.order_management_api.business.mapper.AppMapper;
import com.diegoduarte.order_management_api.infrastructure.entity.UserEntity;
import com.diegoduarte.order_management_api.infrastructure.exceptions.ConflictException;
import com.diegoduarte.order_management_api.infrastructure.exceptions.ResourceNotFoundException;
import com.diegoduarte.order_management_api.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AppMapper appMapper;

    private void validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ConflictException("Email já cadastrado");
        }
    }

    public UserResponseDTO createUser (UserRequestDTO userRequestDTO) {
        validateEmail(userRequestDTO.getEmail());
        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        UserEntity user = appMapper.toEntity(userRequestDTO);
        return appMapper.toResponse(userRepository.save(user));
    }

    public UserResponseDTO getUserByEmail (String email) {
        return appMapper.toResponse(userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Usuário não encontrado")));
    }

    public List<UserResponseDTO> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(appMapper::toResponse)
                .collect(Collectors.toList());
    }
}
