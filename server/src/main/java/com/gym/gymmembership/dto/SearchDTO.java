package com.gym.gymmembership.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {
    @Nullable
    private LocalDate dateFrom;
    @Nullable
    private LocalDate dateTo;
    private String membershipTypes;
    private String searchInput;
}
