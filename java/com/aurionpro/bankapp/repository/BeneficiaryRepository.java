package com.aurionpro.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.bankapp.entity.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long>{

}
