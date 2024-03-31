package io.guywithglasses.invoicemanagement.service;

import io.guywithglasses.invoicemanagement.domain.User;
import io.guywithglasses.invoicemanagement.dto.UserDTO;

public interface UserService {
    UserDTO createUser(User user);
}
