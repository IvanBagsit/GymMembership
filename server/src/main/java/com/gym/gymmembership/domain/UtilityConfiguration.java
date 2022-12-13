package com.gym.gymmembership.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "UTILITY_CONFIGURATION")
public class UtilityConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CONFIG")
    private String config;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MODULE")
    private String module;
}
