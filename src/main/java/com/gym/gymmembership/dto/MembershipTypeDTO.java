package com.gym.gymmembership.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipTypeDTO {
    private String type;
    private Integer fee;
    private String duration;
}
