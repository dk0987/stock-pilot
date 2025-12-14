package com.sp.partners.repository;

import com.sp.partners.model.Partners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnersRepository extends JpaRepository<Partners, Long> {
}
