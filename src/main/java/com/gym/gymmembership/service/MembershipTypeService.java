package com.gym.gymmembership.service;

import com.gym.gymmembership.domain.MembershipType;

public interface MembershipTypeService {
    MembershipType addMembershipPlan(MembershipType membershipType);
    void updateMembershipPlan(MembershipType membershipType);
    void deleteMembershipPlan(MembershipType membershipType);
}
