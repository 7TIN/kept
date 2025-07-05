package com.dev.kept.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.kept.Beans.Company;
import com.dev.kept.dto.companyDto.CompanyWithCountDto;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByNameIgnoreCase(String name);
    List<Company> findByNameContainingIgnoreCaseOrderByNameAsc(String q);

    @Query("""
    SELECT new com.dev.kept.dto.companyDto.CompanyWithCountDto(
        c.id, c.name, COUNT(e)
    )
    FROM Company c
    LEFT JOIN c.experiences e
    GROUP BY c.id, c.name
    ORDER BY c.name
    """)

    List<CompanyWithCountDto> findAllWithExperienceCount();
}
