package com.gym.gymmembership.dto;

import com.gym.gymmembership.domain.MembershipType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {

    @Nullable
    private Long id;
    private String firstName;
    private String lastName;
    private MembershipType membershipType;
    private Boolean termsAndCondition;
    private LocalDate expirationDate;
    private Boolean disable;
    private LocalDate joinDate;

    @Nullable
    private Integer age;

    @Nullable
    private LocalDate birthday;
}
