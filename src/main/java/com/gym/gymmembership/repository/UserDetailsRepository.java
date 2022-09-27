package com.gym.gymmembership.repository;

import com.gym.gymmembership.domain.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {
}
