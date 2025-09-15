package com.aurionpro.bankapp.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatusUpdateRequestDto {
	
	  private String status; // "ACTIVE" or "REJECTED"

}
