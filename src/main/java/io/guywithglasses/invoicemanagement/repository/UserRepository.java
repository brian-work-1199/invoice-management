package io.guywithglasses.invoicemanagement.repository;

import io.guywithglasses.invoicemanagement.domain.User;

import java.util.Collection;

public interface UserRepository<T extends User> {
    /* CRUD Operation */
    T create( T data);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);

    /* Complex Operation */
}
