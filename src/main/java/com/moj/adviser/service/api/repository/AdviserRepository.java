package com.moj.adviser.service.api.repository;

import com.moj.adviser.service.api.domain.Adviser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdviserRepository extends JpaRepository<Adviser, Long> {

    @Query("SELECT u FROM Adviser u, Category c WHERE u.Id = c.adviserId and u.orgType = :orgType and c.name = :categoryName")
    List<Adviser> getAllAdviserWithType(String orgType, String categoryName);
}
