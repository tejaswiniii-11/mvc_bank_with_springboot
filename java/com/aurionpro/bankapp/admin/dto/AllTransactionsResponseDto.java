package com.aurionpro.bankapp.admin.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllTransactionsResponseDto {
    private List<TransactionDetailsDto> transactions;
    private String message;
}
