package com.gym.gymmembership.service;

import com.gym.gymmembership.domain.MembershipType;
import com.gym.gymmembership.domain.UserDetails;
import com.gym.gymmembership.dto.UserDetailsDTO;
import com.gym.gymmembership.repository.MembershipTypeRepository;
import com.gym.gymmembership.repository.UserDetailsRepository;
import com.gym.gymmembership.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest {
    @Mock
    private UserDetailsRepository userDetailsRepository;
    @Mock
    private MembershipTypeRepository membershipTypeRepository;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    private UserDetailsDTO userDetailsDTO;
    private UserDetails userDetails;
    private MembershipType membershipType;

    private final String type = "Daily";
    private final Integer fee = 50;
    private final String duration = "1";

    @BeforeEach
    void init() {
        membershipType = new MembershipType();
        membershipType.setType("Daily");
        membershipType.setDuration("1");
        membershipType.setFee(50);

        userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setFirstName("Ivan");
        userDetailsDTO.setLastName("Bagsit");
        userDetailsDTO.setMembershipType(membershipType);
        userDetailsDTO.setAge(35);
        userDetailsDTO.setTermsAndCondition(true);
        userDetailsDTO.setDisable(false);
        userDetailsDTO.setBirthday(LocalDate.now());
        userDetailsDTO.setJoinDate(LocalDate.now());
        userDetailsDTO.setExpirationDate(LocalDate.now());

        userDetails = new UserDetails();
        userDetails.setFirstName("John");
        userDetails.setLastName("Doe");
        userDetails.setMembershipType(membershipType);
        userDetails.setAge(32);
        userDetails.setTermsAndCondition(true);
        userDetails.setDisable(false);
        userDetails.setBirthday(LocalDate.now());
        userDetails.setJoinDate(LocalDate.now());
        userDetails.setExpirationDate(LocalDate.now());

        membershipType = new MembershipType();
        membershipType.setType(type);
        membershipType.setFee(fee);
        membershipType.setDuration(duration);
    }

    @Test
    void testFetchAllUserDetails_List() {
        // when
        UserDetails user1 = new UserDetails();
        UserDetails user2 = new UserDetails();
        UserDetails user3 = new UserDetails();
        List<UserDetails> resultList = new ArrayList<>();
        resultList.add(user1);
        resultList.add(user2);
        resultList.add(user3);

        when(userDetailsRepository.findAll()).thenReturn(resultList);

        // then
        List<UserDetails> result = userDetailsServiceImpl.fetchAllUserDetails();

        // verify
        assertNotNull(result);
        verify(userDetailsRepository, times(1)).findAll();
    }

    @Test
    void testFetchAllUserDetails_Empty() {
        // when
        when(userDetailsRepository.findAll()).thenReturn(null);

        // then
        List<UserDetails> result = userDetailsServiceImpl.fetchAllUserDetails();

        // verify
        assertNull(result);
        verify(userDetailsRepository, times(1)).findAll();
    }

    @Test
    void testFetchUserDetail_Success() throws Exception {
        // when
        when(userDetailsRepository.findById(userDetails.getId())).thenReturn(Optional.ofNullable(userDetails));

        // then
        UserDetails result = userDetailsServiceImpl.fetchUserDetail(userDetails.getId());

        // verify
        assertNotNull(result);
        assertEquals(result.getFirstName(), userDetails.getFirstName());
        assertEquals(result.getLastName(), userDetails.getLastName());
        assertEquals(result.getAge(), userDetails.getAge());
        assertEquals(result.getMembershipType(), userDetails.getMembershipType());
        assertEquals(result.getTermsAndCondition(), userDetails.getTermsAndCondition());
        assertEquals(result.getDisable(), userDetails.getDisable());
        assertEquals(result.getBirthday(), userDetails.getBirthday());
        assertEquals(result.getJoinDate(), userDetails.getJoinDate());
        assertEquals(result.getExpirationDate(), userDetails.getExpirationDate());
        verify(userDetailsRepository, times(1)).findById(userDetails.getId());
    }

    @Test
    void testFetchUserDetail_NotFound() {
        // when
        when(userDetailsRepository.findById(anyLong())).thenReturn(Optional.empty());

        // then and verify
        assertThrows(Exception.class, () -> userDetailsServiceImpl.fetchUserDetail(userDetails.getId()));
    }

    @Test
    void testAddUser_Added() throws Exception {
        // when
        when(membershipTypeRepository.findByTypeAndFeeAndDuration(type, fee, duration)).thenReturn(Optional.ofNullable(membershipType));

        // then
        UserDetailsDTO result = userDetailsServiceImpl.addUser(userDetailsDTO);

        // verify
        assertNotNull(result);
        assertEquals(result.getFirstName(), userDetailsDTO.getFirstName());
        assertEquals(result.getLastName(), userDetailsDTO.getLastName());
        assertEquals(result.getAge(), userDetailsDTO.getAge());
        verify(membershipTypeRepository, times(1)).findByTypeAndFeeAndDuration(type, fee, duration);
        verify(userDetailsRepository, times(1)).save(any(UserDetails.class));
    }

    @Test
    void testAddUser_Invalid() {
        // when
        when(membershipTypeRepository.findByTypeAndFeeAndDuration(type, fee, duration)).thenReturn(Optional.empty());

        // then and verify
        assertThrows(Exception.class, () -> userDetailsServiceImpl.addUser(userDetailsDTO)) ;
    }

    @Test
    void testUpdateUser_Success() throws Exception {
        // when
        when(userDetailsRepository.findById(userDetails.getId())).thenReturn(Optional.ofNullable(userDetails));
        when(membershipTypeRepository.findByTypeAndFeeAndDuration(type, fee, duration)).thenReturn(Optional.ofNullable(membershipType));

        // then
        UserDetailsDTO result = userDetailsServiceImpl.updateUser(userDetailsDTO);

        // verify
        assertEquals(result.getFirstName(), userDetails.getFirstName());
        assertEquals(result.getLastName(), userDetails.getLastName());
        assertEquals(result.getAge(), userDetails.getAge());
        verify(userDetailsRepository, times(1)).findById(userDetails.getId());
        verify(membershipTypeRepository, times(1)).findByTypeAndFeeAndDuration(type, fee, duration);
        verify(userDetailsRepository, times(1)).save(any(UserDetails.class));
    }

    @Test
    void testUpdateUser_Failed() {
        // when
        when(userDetailsRepository.findById(userDetails.getId())).thenReturn(Optional.empty());
        when(membershipTypeRepository.findByTypeAndFeeAndDuration(type, fee, duration)).thenReturn(Optional.empty());

        // then and verify
        assertThrows(Exception.class, () -> userDetailsServiceImpl.updateUser(userDetailsDTO));
    }

    @Test
    void testDeleteUser_Deleted() throws Exception {
        // when
        when(userDetailsRepository.findById(userDetails.getId())).thenReturn(Optional.ofNullable(userDetails));

        // then
        String result = userDetailsServiceImpl.deleteUser(userDetails.getId());

        // verify
        assertTrue(result.contains("Success"));
        assertNotNull(result);
    }

    @Test
    void testDeleteUser_NotFound() {
        // when
        when(userDetailsRepository.findById(userDetails.getId())).thenReturn(Optional.empty());

        // then and verify
        assertThrows(Exception.class, () -> userDetailsServiceImpl.deleteUser(userDetails.getId()));
    }
}
