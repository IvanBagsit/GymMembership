package com.gym.gymmembership.service;

import com.gym.gymmembership.domain.UserDetails;
import com.gym.gymmembership.dto.UserDetailsDTO;

import java.util.List;

public interface UserDetailsService {
    List<UserDetails> fetchAllUserDetails();
    UserDetails addUser(UserDetailsDTO userDetailsDTO) throws Exception;
    void updateUser(UserDetails userDetails);
    void disableUser(UserDetails userDetails);
}
