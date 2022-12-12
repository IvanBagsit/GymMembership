package com.gym.gymmembership.repository;

import com.gym.gymmembership.domain.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {
    Optional<UserDetails> findByUsername(String username);

}
