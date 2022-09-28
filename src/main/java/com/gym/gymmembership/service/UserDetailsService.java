package com.gym.gymmembership.service;

import com.gym.gymmembership.domain.UserDetails;
import com.gym.gymmembership.dto.UserDetailsDTO;

import java.util.List;

public interface UserDetailsService {
    List<UserDetails> fetchAllUserDetails();
    UserDetailsDTO addUser(UserDetailsDTO userDetailsDTO) throws Exception;
    UserDetailsDTO updateUser(UserDetailsDTO userDetailsDTO) throws Exception;
}
