package ru.leontev.shop.service.serviceRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.shop.dto.request.RegistrationRequestDto;
import ru.leontev.shop.dto.response.RegistrationResponseDto;
import ru.leontev.shop.mapper.request.UserRegistrationRequestMapper;
import ru.leontev.shop.mapper.response.UserRegistrationResponseMapper;
import ru.leontev.shop.model.UserEntity;
import ru.leontev.shop.repository.UserRepository;
import java.util.Set;

/**
 * UserRegistrationServiceRequest обрабатывает регистрацию нового пользователя.
 * Проверяет, что имя пользователя уникально, шифрует пароль, устанавливает роль по умолчанию и сохраняет пользователя.
 */
@Service
public class UserRegistrationServiceRequest {

    private final UserRepository userRepository; // Репозиторий для пользователей
    private final PasswordEncoder passwordEncoder; // Используется для шифрования пароля
    private final UserRegistrationRequestMapper requestMapper; // Преобразует DTO в UserEntity
    private final UserRegistrationResponseMapper responseMapper; // Преобразует UserEntity в DTO для ответа

    public UserRegistrationServiceRequest(UserRepository userRepository,
                                          PasswordEncoder passwordEncoder,
                                          UserRegistrationRequestMapper requestMapper,
                                          UserRegistrationResponseMapper responseMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @Transactional
    public RegistrationResponseDto registerUser(RegistrationRequestDto dto) {
        // Проверка: существует ли уже пользователь с таким именем
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken");
        }
        // Преобразуем DTO в сущность
        UserEntity user = requestMapper.toEntity(dto);
        // Шифруем пароль
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Устанавливаем роль по умолчанию
        user.setRoles(Set.of("ROLE_USER"));
        // Сохраняем пользователя
        UserEntity savedUser = userRepository.save(user);
        return responseMapper.toDto(savedUser);
    }
}
