package com.dev.kept.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.kept.Beans.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
