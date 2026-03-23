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
        UserEntity user = appMapper.toEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return appMapper.toResponse(userRepository.save(user));
    }

    public UserResponseDTO getUserById (Long id) {
        return appMapper.toResponse(userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Usuário não encontrado")));
    }

    public UserResponseDTO findByEmail (String email) {
        return appMapper.toResponse(userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Usuário não encontrado")));
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(appMapper::toResponse)
                .toList();
    }
}
