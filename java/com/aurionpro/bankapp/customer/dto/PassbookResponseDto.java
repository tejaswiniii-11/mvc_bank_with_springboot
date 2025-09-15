package com.aurionpro.bankapp.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassbookResponseDto {

    private String customerName;
    private List<PassbookEntryDto> transactions;
}
