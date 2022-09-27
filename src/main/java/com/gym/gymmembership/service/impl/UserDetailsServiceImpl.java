package com.gym.gymmembership.service.impl;

import com.gym.gymmembership.domain.UserDetails;
import com.gym.gymmembership.dto.UserDetailsDTO;
import com.gym.gymmembership.repository.UserDetailsRepository;
import com.gym.gymmembership.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;


    @Override
    public UserDetailsDTO fetchUserDetails(UserDetails userDetails) {
        Optional<UserDetails> user = userDetailsRepository.findById(userDetails.getId());
        if(user.isPresent()) {
            UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
            userDetailsDTO.setUsername(user.get().getUsername());
            userDetailsDTO.setFirstName(user.get().getFirstName());
            userDetailsDTO.setLastName(user.get().getLastName());
            userDetailsDTO.setLastLogIn(user.get().getLastLogIn());
            userDetailsDTO.setLastLogOut(user.get().getLastLogOut());
            userDetailsDTO.setExpirationDate(user.get().getExpirationDate());
            userDetailsDTO.setMembershipType(user.get().getMembershipType().getType());
            userDetailsDTO.setAccountType(user.get().getAccountType().getAdmin() ? "ADMIN" : "USER");

            return userDetailsDTO;
        }
        else { throw new NullPointerException("Can't find user: " + user.get().getUsername() + " in the database to be retrieved"); }
    }

    @Override
    public UserDetails addUser(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);
        log.info("Successfully added User: {} in the database", userDetails.getUsername());
        return userDetails;
    }

    @Override
    public void updateUser(UserDetails userDetails) {
        Optional<UserDetails> user = userDetailsRepository.findById(userDetails.getId());
        if(user.isPresent()) {
            userDetailsRepository.save(userDetails);
            log.info("Successfully updated User: {}", userDetails);
        }
        else { log.error("Can't find user: {} in the database to be updated", userDetails.getUsername()); }
    }

    @Override
    public void disableUser(UserDetails userDetails) {
        Optional<UserDetails> user = userDetailsRepository.findById(userDetails.getId());
        if(user.isPresent()) {
            userDetails.setDisable(true);
            userDetailsRepository.save(userDetails);
            log.info("Successfully disabled user: {}",userDetails.getUsername());
        }
        else { log.error("Can't find user: {} in the database to be disabled", userDetails.getUsername()); }
    }
}
