package com.gym.gymmembership.dto;

import com.gym.gymmembership.domain.AccountType;
import com.gym.gymmembership.domain.MembershipType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.lang.reflect.Member;
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
    private String type;
    private Integer fee;
    private String duration;
    private Date expirationDate;
    private Boolean disable;

    @Nullable
    private Integer age;

    @Nullable
    private Date birthday;
}
