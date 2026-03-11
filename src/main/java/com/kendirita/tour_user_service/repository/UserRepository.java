package com.kendirita.tour_user_service.repository;

import com.kendirita.tour_user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    boolean existsByEmail(String email);
    User searchByEmail(String email);
    Optional<User> findByEmail(String email);
}
