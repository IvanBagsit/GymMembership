package com.gym.gymmembership.service.impl;

import com.gym.gymmembership.domain.MembershipType;
import com.gym.gymmembership.repository.MembershipTypeRepository;
import com.gym.gymmembership.service.MembershipTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MembershipTypeServiceImpl implements MembershipTypeService {

    private final MembershipTypeRepository membershipTypeRepository;

    @Override
    public MembershipType addMembershipPlan(MembershipType membershipType) {
        membershipTypeRepository.save(membershipType);
        log.info("Successfully added: {} in the database", membershipType);
        return membershipType;
    }

    @Override
    public void updateMembershipPlan(MembershipType membershipType) {
        Optional<MembershipType> membership = membershipTypeRepository.findById(membershipType.getId());
        if(membership.isPresent()) {
            membershipTypeRepository.save(membershipType);
            log.info("Successfully updated: {}", membershipType);
        }
        else { log.error("Can't find membership: {} in the database to be updated", membershipType); }
    }

    @Override
    public void deleteMembershipPlan(MembershipType membershipType) {
        Optional<MembershipType> membership = membershipTypeRepository.findById(membershipType.getId());
        if(membership.isPresent()) {
            membershipTypeRepository.deleteById(membershipType.getId());
            log.info("Successfully deleted: {}", membershipType);
        }
        else { log.error("Can't find membership: {} in the database to be deleted", membershipType); }
    }


}
