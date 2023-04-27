package com.gym.gymmembership.service.impl;

import com.gym.gymmembership.domain.MembershipType;
import com.gym.gymmembership.domain.UserDetails;
import com.gym.gymmembership.dto.SearchDTO;
import com.gym.gymmembership.dto.UserDetailsDTO;
import com.gym.gymmembership.repository.MembershipTypeRepository;
import com.gym.gymmembership.repository.UserDetailsRepository;
import com.gym.gymmembership.service.UserDetailsService;
import com.gym.gymmembership.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final MembershipTypeRepository membershipTypeRepository;

    @Override
    public List<UserDetails> fetchAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    @Override
    public UserDetails fetchUserDetail(Long id) throws Exception {
        Optional<UserDetails> userDetails = userDetailsRepository.findById(id);
        if(Optional.ofNullable(userDetails).isPresent()){
            log.info("User Details found : {}", userDetails);
            return userDetails.get();
        } else {
            log.info("Can't find user with id of {}", id);
            throw new Exception("Error searching for the user with id : " + id);
        }
    }

    @Override
    public UserDetailsDTO addUser(UserDetailsDTO userDetailsDTO) throws Exception {
        log.info("Starting addUser() - {}", userDetailsDTO);
        UserDetails userDetails = new UserDetails();
        Optional<MembershipType> membershipType = membershipTypeRepository.findByTypeAndFeeAndDuration(
                userDetailsDTO.getMembershipType().getType(),
                userDetailsDTO.getMembershipType().getFee(),
                userDetailsDTO.getMembershipType().getDuration()
        );

        if (Optional.ofNullable(membershipType).isPresent()) {
            log.info("found membership type: {}",membershipType);
            userDetails.setFirstName(userDetailsDTO.getFirstName());
            userDetails.setLastName(userDetailsDTO.getLastName());
            userDetails.setMembershipType(membershipType.get());
            userDetails.setDisable(false);
            userDetails.setBirthday(userDetailsDTO.getBirthday());
            userDetails.setAge(getAge(userDetailsDTO.getBirthday(), LocalDate.now()));
            userDetails.setExpirationDate(
                    CommonUtil.currentDate().plusDays(Long.valueOf(userDetailsDTO.getMembershipType().getDuration()))
            );
            userDetails.setTermsAndCondition(userDetailsDTO.getTermsAndCondition());
            userDetails.setJoinDate(CommonUtil.convertToLocalDate(LocalDateTime.now()));

            userDetailsRepository.save(userDetails);
            log.info("Successfully added User: {} in the database", userDetailsDTO.getFirstName());
            return userDetailsDTO;
        }
        else {
            log.info("Failed to save user : {}", userDetailsDTO);
            throw new Exception("Failed to save User: " + userDetailsDTO.getFirstName());
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

        if(Optional.ofNullable(user).isPresent() && Optional.ofNullable(membershipType).isPresent()) {
            log.info("found user to be updated: {} - {}", user, membershipType);
            user.get().setFirstName(userDetailsDTO.getFirstName());
            user.get().setLastName(userDetailsDTO.getLastName());
            user.get().setMembershipType(userDetailsDTO.getMembershipType());
            user.get().setDisable(userDetailsDTO.getDisable());
            user.get().setAge(userDetailsDTO.getAge());
            user.get().setBirthday(userDetailsDTO.getBirthday());
            user.get().setExpirationDate(userDetailsDTO.getExpirationDate());
            user.get().setJoinDate(userDetailsDTO.getJoinDate());
            user.get().setTermsAndCondition(userDetailsDTO.getTermsAndCondition());
            userDetailsRepository.save(user.get());
            log.info("Successfully updated User: {}", user.get().getFirstName());
            return userDetailsDTO;
        }
        else {
            log.info("Failed to update user : {}", userDetailsDTO);
            throw new Exception("Failed to update User: " + userDetailsDTO.getFirstName()); }
    }

    @Override
    public String deleteUser(Long id) throws Exception {
        Optional<UserDetails> user = userDetailsRepository.findById(id);
        if(user.isPresent()) {
            userDetailsRepository.deleteById(id);
            log.info("Success deletion of user");
            return "Success deletion of user";
        } else {
            log.info("Deletion failed. User is not existing");
            throw new Exception("Can't find user");
        }
    }

    @Override
    public List<UserDetails> filterUserDetails(SearchDTO searchDTO) throws Exception {
        Optional<MembershipType> membershipType = membershipTypeRepository.findByType(searchDTO.getMembershipTypes());
        if(membershipType.isPresent()) {
            if(searchDTO.getDateFrom() == null && searchDTO.getDateTo() != null) {
                return userDetailsRepository.filterUserDetailsWithDateTo(
                        searchDTO.getDateTo(),
                        membershipType.get().getId(),
                        searchDTO.getSearchInput()
                        );
            } else if (searchDTO.getDateFrom() != null && searchDTO.getDateTo() == null) {
                return userDetailsRepository.filterUserDetailsWithDateFrom(
                        searchDTO.getDateFrom(),
                        membershipType.get().getId(),
                        searchDTO.getSearchInput()
                );
            } else if (searchDTO.getDateFrom() == null && searchDTO.getDateTo() == null){
                return userDetailsRepository.filterUserDetailsWithoutDate(
                        membershipType.get().getId(),
                        searchDTO.getSearchInput()
                );
            } else {
                return userDetailsRepository.filterUserDetailsWithAllParameters(
                        searchDTO.getDateFrom(),
                        searchDTO.getDateTo(),
                        membershipType.get().getId(),
                        searchDTO.getSearchInput()
                );
            }
        } else {
            if(searchDTO.getDateFrom() == null && searchDTO.getDateTo() != null) {
                return userDetailsRepository.filterUserDetailsWithDateToWithoutMembershipType(
                        searchDTO.getDateTo(),
                        searchDTO.getSearchInput()
                );
            } else if (searchDTO.getDateFrom() != null && searchDTO.getDateTo() == null) {
                return userDetailsRepository.filterUserDetailsWithDateFromWithoutMembershipType(
                        searchDTO.getDateFrom(),
                        searchDTO.getSearchInput()
                );
            } else if (searchDTO.getDateFrom() == null && searchDTO.getDateTo() == null){
                return userDetailsRepository.filterUserDetailsWithoutDateAndMembershipType(
                        searchDTO.getSearchInput()
                );
            } else {
                return userDetailsRepository.filterUserDetailsWithAllParametersExceptMembershipType(
                        searchDTO.getDateFrom(),
                        searchDTO.getDateTo(),
                        searchDTO.getSearchInput()
                );
            }
        }
    }

    public int getAge(LocalDate birthday, LocalDate currentDate){
        if ((birthday != null) && (currentDate != null)) {
            return Period.between(birthday, currentDate).getYears();
        } else {
            return 0;
        }
    }

}
