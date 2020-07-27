package com.geekbrains.july.market.repositories;



import com.geekbrains.july.market.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findOneByPhone(String phone);
    User findOneUserByPhone(String phone);
    User findOneUserById(Long id);
    List<User> findAllBy();
    boolean existsByPhone(String phone);
}