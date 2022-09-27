package com.gym.gymmembership.service;

import com.gym.gymmembership.domain.UserDetails;
import com.gym.gymmembership.dto.UserDetailsDTO;

public interface UserDetailsService {
    UserDetailsDTO fetchUserDetails(UserDetails userDetails);
    UserDetails addUser(UserDetails userDetails);
    void updateUser(UserDetails userDetails);
    void disableUser(UserDetails userDetails);
}
