package com.gym.gymmembership.repository;

import com.gym.gymmembership.domain.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {
    Optional<UserDetails> findByUsername(String username);
}
