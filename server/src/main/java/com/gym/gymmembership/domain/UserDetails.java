package com.gym.gymmembership.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "USERNAME", nullable = false, unique = true)
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
    private LocalDate birthday;

    @Nullable
    @Column(name = "LAST_LOGIN")
    private LocalDate lastLogIn;

    @Nullable
    @Column(name = "LAST_LOGOUT")
    private LocalDate lastLogOut;

    @Nullable
    @Column(name = "EXPIRATION_DATE")
    private LocalDate expirationDate;

    @CreationTimestamp
    @Column(name = "JOIN_DATE")
    private LocalDate joinDate;

    @Column(name = "TERMS_AND_CONDITION")
    private Boolean termsAndCondition;

    @Nullable
    @Column(name = "DISABLE")
    private Boolean disable;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "MEMBERSHIPTYPE_ID_FK", referencedColumnName = "ID")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private MembershipType membershipType;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "ACCOUNTTYPE_ID_FK", referencedColumnName = "ID")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private AccountType accountType;

}
