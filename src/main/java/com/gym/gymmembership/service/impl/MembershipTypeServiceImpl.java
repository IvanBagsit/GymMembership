package com.gym.gymmembership.service.impl;

import com.gym.gymmembership.domain.MembershipType;
import com.gym.gymmembership.dto.MembershipTypeDTO;
import com.gym.gymmembership.repository.MembershipTypeRepository;
import com.gym.gymmembership.service.MembershipTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MembershipTypeServiceImpl implements MembershipTypeService {

    private final MembershipTypeRepository membershipTypeRepository;

    @Override
    public List<MembershipType> fetchAllMembershipPlan() {
        return membershipTypeRepository.findAll();
    }

    @Override
    public MembershipTypeDTO addMembershipPlan(MembershipTypeDTO membershipTypeDTO) {
        Optional<MembershipType> membership = membershipTypeRepository.findByTypeAndFeeAndDuration(
                membershipTypeDTO.getType(),
                membershipTypeDTO.getFee(),
                membershipTypeDTO.getDuration()
        );
        if(membership.isPresent()){
            throw new IllegalArgumentException("Membership Plan already available in the database");
        }
        else {
            MembershipType membershipType = new MembershipType();
            membershipType.setType(membershipTypeDTO.getType());
            membershipType.setFee(membershipTypeDTO.getFee());
            membershipType.setDuration(membershipTypeDTO.getDuration());

            membershipTypeRepository.save(membershipType);
            log.info("Successfully added: {} in the database", membershipTypeDTO.getType());
            return membershipTypeDTO;
        }
    }

    @Override
    public MembershipTypeDTO updateMembershipPlan(MembershipTypeDTO membershipTypeDTO) throws Exception {
        Optional<MembershipType> membership = membershipTypeRepository.findByTypeAndFeeAndDuration(
                membershipTypeDTO.getType(),
                membershipTypeDTO.getFee(),
                membershipTypeDTO.getDuration()
        );
        if (membership.isPresent()) {
            membership.get().setType(membershipTypeDTO.getType());
            membership.get().setFee(membershipTypeDTO.getFee());
            membership.get().setDuration(membershipTypeDTO.getDuration());
            membershipTypeRepository.save(membership.get());
            log.info("Successfully updated: {}", membership.get().getType());
            return membershipTypeDTO;
        } else {
            throw new Exception("Can't find membership plan: " + membershipTypeDTO.getType() + " in the database to be updated");
        }
    }

    @Override
    public MembershipTypeDTO deleteMembershipPlan(MembershipTypeDTO membershipTypeDTO) throws Exception {
        Optional<MembershipType> membership = membershipTypeRepository.findByTypeAndFeeAndDuration(
                membershipTypeDTO.getType(),
                membershipTypeDTO.getFee(),
                membershipTypeDTO.getDuration()
        );
        if(membership.isPresent()) {
            membershipTypeRepository.deleteByType(membershipTypeDTO.getType());
            log.info("Successfully deleted: {}", membershipTypeDTO.getType());
            return membershipTypeDTO;
        }
        else { throw new Exception("Can't find membership plan: " + membershipTypeDTO.getType() + " in the database to be deleted"); }
    }

}
