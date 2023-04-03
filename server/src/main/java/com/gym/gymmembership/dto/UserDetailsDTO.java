package com.gym.gymmembership.dto;

import com.gym.gymmembership.domain.AccountType;
import com.gym.gymmembership.domain.MembershipType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {

    @Nullable
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private AccountType accountType;
    private MembershipType membershipType;
    private Boolean termsAndCondition;
    private LocalDate expirationDate;
    private LocalDate lastLogIn;
    private LocalDate lastLogOut;
    private Boolean disable;
    private LocalDate joinDate;

    @Nullable
    private Integer age;

    @Nullable
    private LocalDate birthday;
}
