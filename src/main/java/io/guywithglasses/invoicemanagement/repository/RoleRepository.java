package io.guywithglasses.invoicemanagement.repository;

import io.guywithglasses.invoicemanagement.domain.Role;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface RoleRepository<T extends Role> {
    /* CRUD Operation */
    T create( T data);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    /* Complex Operation */
    void addRoleToUser(Long userId, String roleName);
    Role getRoleByUserId(Long userId);
    Role getRoleByUserEmail(String email);
    void updateUserRole(Long userId, String roleName);
}
