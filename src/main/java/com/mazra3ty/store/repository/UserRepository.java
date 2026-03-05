package com.mazra3ty.store.repository;

import com.mazra3ty.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndIsActiveTrue(String email);

    Optional<User> findByIdAndIsActiveTrue(Long userId);

    List<User> findAllByIsActiveTrue();
}
