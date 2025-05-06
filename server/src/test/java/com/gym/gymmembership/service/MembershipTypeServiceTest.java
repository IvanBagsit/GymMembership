package com.gym.gymmembership.service;

import com.gym.gymmembership.domain.MembershipType;
import com.gym.gymmembership.dto.MembershipTypeDTO;
import com.gym.gymmembership.repository.MembershipTypeRepository;
import com.gym.gymmembership.service.impl.MembershipTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MembershipTypeServiceTest {
    @Mock
    private MembershipTypeRepository membershipTypeRepository;
    @InjectMocks
    private MembershipTypeServiceImpl membershipTypeServiceImpl;

    private MembershipTypeDTO membershipTypeDTO;
    private MembershipType membershipType;

    private final String type = "Daily";
    private final Integer fee = 50;
    private final String duration = "1";

    @BeforeEach
    void init() {
        membershipTypeDTO = new MembershipTypeDTO(type, fee, duration);
        membershipType = new MembershipType();
        membershipType.setType(type);
        membershipType.setFee(fee);
        membershipType.setDuration(duration);
    }

    @Test
    void testFetchAllMembershipPlan_List() {
        // when
        MembershipType type1 = new MembershipType();
        MembershipType type2 = new MembershipType();
        MembershipType type3 = new MembershipType();
        List<MembershipType> resultList = new ArrayList<>();
        resultList.add(type1);
        resultList.add(type2);
        resultList.add(type3);

        when(membershipTypeRepository.findAll()).thenReturn(resultList);

        // then
        List<MembershipType> result = membershipTypeServiceImpl.fetchAllMembershipPlan();

        // verify
        assertNotNull(result);
        verify(membershipTypeRepository, times(1)).findAll();
    }

    @Test
    void testFetchAllMembershipPlan_Empty() {
        // when
        when(membershipTypeRepository.findAll()).thenReturn(null);

        // then
        List<MembershipType> result = membershipTypeServiceImpl.fetchAllMembershipPlan();

        // verify
        assertNull(result);
        verify(membershipTypeRepository, times(1)).findAll();
    }

    @Test
    void testAddMembershipPlan_Added() {
        // when
        when(membershipTypeRepository.findByTypeAndDuration(anyString(), anyString())).thenReturn(Optional.empty());

        // then
        MembershipTypeDTO result = membershipTypeServiceImpl.addMembershipPlan(membershipTypeDTO);

        // verify
        assertEquals(result.getType(), membershipTypeDTO.getType());
        assertEquals(result.getFee(), membershipTypeDTO.getFee());
        assertEquals(result.getDuration(), membershipTypeDTO.getDuration());
        verify(membershipTypeRepository, times(1)).save(any(MembershipType.class));
        verify(membershipTypeRepository, times(1)).findByTypeAndDuration(anyString(), anyString());
    }

    @Test
    void testAddMembershipPlan_Invalid() {
        // when
        when(membershipTypeRepository.findByTypeAndDuration(type, duration)).thenReturn(Optional.of(membershipType));

        // then and verify
        assertThrows(IllegalArgumentException.class, () -> membershipTypeServiceImpl.addMembershipPlan(membershipTypeDTO));
    }

    @Test
    void testUpdateMembershipPlan_Updated() throws Exception {
        // when
        MembershipTypeDTO membershipTypeDTO = new MembershipTypeDTO(type, 200, duration);
        when(membershipTypeRepository.findByTypeAndDuration(type, duration)).thenReturn(Optional.of(membershipType));

        // then
        MembershipTypeDTO result = membershipTypeServiceImpl.updateMembershipPlan(membershipTypeDTO);

        // verify
        assertEquals(result.getType(), membershipTypeDTO.getType());
        assertEquals(result.getFee(), membershipTypeDTO.getFee());
        assertEquals(result.getDuration(), membershipTypeDTO.getDuration());
        verify(membershipTypeRepository, times(1)).save(any(MembershipType.class));
        verify(membershipTypeRepository, times(1)).findByTypeAndDuration(anyString(), anyString());
    }

    @Test
    void testUpdateMembershipPlan_NotFound() {
        // when
        when(membershipTypeRepository.findByTypeAndDuration(type, duration)).thenReturn(Optional.empty());

        // then and verify
        assertThrows(Exception.class, () -> membershipTypeServiceImpl.updateMembershipPlan(membershipTypeDTO));
    }

    @Test
    void testDeleteMembershipPlan_Deleted() throws Exception {
        // when
        when(membershipTypeRepository.findByTypeAndFeeAndDuration(type, fee, duration)).thenReturn(Optional.of(membershipType));

        // then
        String result = membershipTypeServiceImpl.deleteMembershipPlan(membershipTypeDTO);

        // verify
        assertTrue(result.contains(membershipTypeDTO.getType()));
        assertTrue(result.contains(membershipTypeDTO.getFee().toString()));
        assertTrue(result.contains(membershipTypeDTO.getDuration()));
        verify(membershipTypeRepository, times(1)).deleteByType(type);
        verify(membershipTypeRepository, times(1)).findByTypeAndFeeAndDuration(type, fee, duration);
    }

    @Test
    void testDeleteMembershipPlan_NotFound() {
        // when
        when(membershipTypeRepository.findByTypeAndFeeAndDuration(type, fee, duration)).thenReturn(Optional.empty());

        // then and verify
        assertThrows(Exception.class, () -> membershipTypeServiceImpl.deleteMembershipPlan(membershipTypeDTO));
    }
}
