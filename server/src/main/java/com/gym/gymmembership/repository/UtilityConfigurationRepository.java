package com.gym.gymmembership.repository;

import com.gym.gymmembership.domain.UtilityConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilityConfigurationRepository extends JpaRepository<UtilityConfiguration, Long> {
    Optional<UtilityConfiguration> findFirstByName(String name);
}
