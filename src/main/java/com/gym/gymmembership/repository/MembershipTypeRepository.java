package com.gym.gymmembership.repository;

import com.gym.gymmembership.domain.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipTypeRepository extends JpaRepository<MembershipType, Long> {
    Optional<MembershipType> findByTypeAndFeeAndDuration(String type, Integer fee, String duration);
}
