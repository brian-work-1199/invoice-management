package io.guywithglasses.invoicemanagement.service.implimentation;

import io.guywithglasses.invoicemanagement.domain.User;
import io.guywithglasses.invoicemanagement.dto.UserDTO;
import io.guywithglasses.invoicemanagement.dto.dtomapper.UserDTOMapper;
import io.guywithglasses.invoicemanagement.repository.UserRepository;
import io.guywithglasses.invoicemanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository<User> userRepository;
    @Override
    public UserDTO createUser(User user) {
        return UserDTOMapper.fromUser(userRepository.create(user));
    }
}
