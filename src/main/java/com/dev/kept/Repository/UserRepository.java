package com.dev.kept.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.kept.Beans.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
