package io.guywithglasses.invoicemanagement.dto.dtomapper;

import io.guywithglasses.invoicemanagement.domain.User;
import io.guywithglasses.invoicemanagement.dto.UserDTO;
import org.springframework.beans.BeanUtils;

public class UserDTOMapper {
    public static UserDTO fromUser(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    public static User toUser(UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }
}
