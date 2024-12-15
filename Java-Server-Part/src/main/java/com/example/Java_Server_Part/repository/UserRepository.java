package com.example.Java_Server_Part.repository;

import com.example.Java_Server_Part.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
    @Query(value = "SELECT * FROM public.\"user\" WHERE status = 'search' LIMIT 1", nativeQuery = true)
    Optional<UserModel> findFirstUserByStatusSearch();

}
