package com.gym.gymmembership.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {
    private String username;
    private String firstName;
    private String lastName;
    private Date lastLogIn;
    private Date lastLogOut;
    private Date expirationDate;
    private String accountType;
    private String membershipType;
}
