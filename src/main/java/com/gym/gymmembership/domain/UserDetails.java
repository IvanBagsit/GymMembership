package com.gym.gymmembership.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "USER")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Nullable
    @Column(name = "AGE")
    private Integer age;

    @Nullable
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "LAST_LOGIN")
    private Date lastLogIn;

    @Column(name = "LAST_LOGOUT")
    private Date lastLogOut;

    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;

    @CreationTimestamp
    @Column(name = "JOIN_DATE")
    private Date joinDate;

    @Column(name = "TERMS_AND_CONDITION")
    private Boolean termsAndCondition;

    @Column(name = "DISABLE")
    private Boolean disable;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBERSHIPTYPE_ID_FK")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private MembershipType membershipType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNTTYPE_ID_FK")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private AccountType accountType;

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

}
