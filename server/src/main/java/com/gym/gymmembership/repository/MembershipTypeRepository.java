package com.gym.gymmembership.repository;

import com.gym.gymmembership.domain.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MembershipTypeRepository extends JpaRepository<MembershipType, Long> {
    Optional<MembershipType> findByTypeAndDuration(String type, String duration);
    Optional<MembershipType> findByTypeAndFeeAndDuration(String type, Integer fee, String duration);
    Optional<MembershipType> findByType(String type);

    @Transactional(readOnly = false)
    void deleteByType(String type);
}
