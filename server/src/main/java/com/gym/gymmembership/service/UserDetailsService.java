package com.gym.gymmembership.service;

import com.gym.gymmembership.domain.UserDetails;
import com.gym.gymmembership.dto.MembershipTypeDTO;
import com.gym.gymmembership.dto.SearchDTO;
import com.gym.gymmembership.dto.UserDetailsDTO;

import java.util.List;
import java.util.Optional;

public interface UserDetailsService {
    List<UserDetails> fetchAllUserDetails();
    UserDetails fetchUserDetail(Long id) throws Exception;
    UserDetailsDTO addUser(UserDetailsDTO userDetailsDTO) throws Exception;
    UserDetailsDTO updateUser(UserDetailsDTO userDetailsDTO) throws Exception;
    String deleteUser(Long id) throws Exception;
    List<UserDetails> filterUserDetails(SearchDTO searchDTO) throws Exception;
}
