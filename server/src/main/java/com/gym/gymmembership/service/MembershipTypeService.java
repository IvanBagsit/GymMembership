package com.gym.gymmembership.service;

import com.gym.gymmembership.domain.MembershipType;
import com.gym.gymmembership.dto.MembershipTypeDTO;

import java.util.List;

public interface MembershipTypeService {
    List<MembershipType> fetchAllMembershipPlan();
    MembershipTypeDTO addMembershipPlan(MembershipTypeDTO membershipTypeDTO);
    MembershipTypeDTO updateMembershipPlan(MembershipTypeDTO membershipTypeDTO) throws Exception;
    String deleteMembershipPlan(MembershipTypeDTO membershipTypeDTO) throws Exception;
}
