package com.arthiva.repository;

import com.arthiva.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OpportunityRepository extends JpaRepository<Opportunity, String> {
    List<Opportunity> findByCompanyId(String companyId);
    List<Opportunity> findByCompanyIdAndStatus(String companyId, String status);
    @Query("SELECT o.status, COUNT(o), SUM(o.estimatedAmount) FROM Opportunity o WHERE o.company.id = ?1 GROUP BY o.status")
    List<Object[]> getPipelineSummary(String companyId);
    long countByCompanyIdAndStatus(String companyId, String status);
}
