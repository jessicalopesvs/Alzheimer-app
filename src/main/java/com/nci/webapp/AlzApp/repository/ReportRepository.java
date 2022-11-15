package com.nci.webapp.AlzApp.repository;

import com.nci.webapp.AlzApp.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {

}
