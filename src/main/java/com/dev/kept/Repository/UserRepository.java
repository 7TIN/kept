package com.dev.kept.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.kept.Beans.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
