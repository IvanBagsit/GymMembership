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

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    public UserDetails fetchUserDetail(Long id) throws Exception {
        Optional<UserDetails> userDetails = userDetailsRepository.findById(id);
        if(userDetails.isPresent()){
            log.info("User Details found : {}", userDetails);
            return userDetails.get();
        } else {
            log.info("Can't find user with id of {}", id);
            throw new Exception("Error searching for the user with id : " + id);
        }
    }

    @Override
    public UserDetailsDTO addUser(UserDetailsDTO userDetailsDTO) throws Exception {
        UserDetails userDetails = new UserDetails();
        Optional<AccountType> role = accountTypeRepository.findByRole(userDetailsDTO.getAccountType().getRole());
        Optional<MembershipType> membershipType = membershipTypeRepository.findByTypeAndFeeAndDuration(
                userDetailsDTO.getMembershipType().getType(),
                userDetailsDTO.getMembershipType().getFee(),
                userDetailsDTO.getMembershipType().getDuration()
        );

        if (role.isPresent() && membershipType.isPresent()) {
            log.info("found role and membership type: {} - {}", role, membershipType);
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
            userDetails.setJoinDate(Date.from(LocalDateTime.now().atZone(ZoneId.of("Asia/Manila")).toInstant()));

            userDetailsRepository.save(userDetails);
            log.info("Successfully added User: {} in the database", userDetailsDTO.getUsername());
            return userDetailsDTO;
        }
        else {
            log.info("Failed to save user : {}", userDetailsDTO);
            throw new Exception("Failed to save User: " + userDetailsDTO.getUsername());
        }

    }

    @Override
    public UserDetailsDTO updateUser(UserDetailsDTO userDetailsDTO) throws Exception {
        Optional<UserDetails> user = userDetailsRepository.findById(userDetailsDTO.getId());
        Optional<MembershipType> membershipType = membershipTypeRepository.findByTypeAndFeeAndDuration(
                userDetailsDTO.getMembershipType().getType(),
                userDetailsDTO.getMembershipType().getFee(),
                userDetailsDTO.getMembershipType().getDuration()
        );

        if(user.isPresent() && membershipType.isPresent()) {
            log.info("found user to be updated: {} - {}", user, membershipType);
            user.get().setUsername(userDetailsDTO.getUsername());
            user.get().setPassword(userDetailsDTO.getPassword());
            user.get().setFirstName(userDetailsDTO.getFirstName());
            user.get().setLastName(userDetailsDTO.getLastName());
            user.get().setMembershipType(membershipType.get());
            user.get().setDisable(userDetailsDTO.getDisable());
            userDetailsRepository.save(user.get());
            log.info("Successfully updated User: {}", user.get().getUsername());
            return userDetailsDTO;
        }
        else {
            log.info("Failed to update user : {}", userDetailsDTO);
            throw new Exception("Failed to update User: " + userDetailsDTO.getUsername()); }
    }

}
