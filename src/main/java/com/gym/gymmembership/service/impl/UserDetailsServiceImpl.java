package com.gym.gymmembership.service.impl;

import com.gym.gymmembership.domain.AccountType;
import com.gym.gymmembership.domain.MembershipType;
import com.gym.gymmembership.domain.UserDetails;
import com.gym.gymmembership.dto.UserDetailsDTO;
import com.gym.gymmembership.repository.AccountTypeRepository;
import com.gym.gymmembership.repository.MembershipTypeRepository;
import com.gym.gymmembership.repository.UserDetailsRepository;
import com.gym.gymmembership.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final MembershipTypeRepository membershipTypeRepository;


    @Override
    public List<UserDetails> fetchAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    @Override
    public UserDetails addUser(UserDetailsDTO userDetailsDTO) throws Exception {

        UserDetails userDetails = new UserDetails();
        Optional<AccountType> role = accountTypeRepository.findByRole(userDetailsDTO.getAccountType());
        Optional<MembershipType> membershipType = membershipTypeRepository.findByTypeAndFeeAndDuration(
                userDetailsDTO.getType(),
                userDetailsDTO.getFee(),
                userDetailsDTO.getDuration()
        );

        if (role.isPresent() && membershipType.isPresent()) {
            userDetails.setUsername(userDetailsDTO.getUsername());
            userDetails.setPassword(userDetailsDTO.getPassword());
            userDetails.setFirstName(userDetailsDTO.getFirstName());
            userDetails.setLastName(userDetailsDTO.getLastName());
            userDetails.setAccountType(role.get());
            userDetails.setMembershipType(membershipType.get());
            userDetails.setDisable(false);
            userDetails.setBirthday(userDetailsDTO.getBirthday());
            userDetails.setAge(userDetailsDTO.getAge());
            userDetails.setExpirationDate(userDetailsDTO.getExpirationDate());
            userDetails.setLastLogIn(null);
            userDetails.setLastLogOut(null);
            userDetails.setTermsAndCondition(userDetailsDTO.getTermsAndCondition());

            userDetailsRepository.save(userDetails);
            log.info("Successfully added User: {} in the database", userDetails.getUsername());
            return userDetails;
        }
        else {
            throw new Exception("Failed to save User: " + userDetailsDTO.getUsername());
        }

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
