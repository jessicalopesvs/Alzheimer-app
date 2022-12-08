package com.nci.webapp.AlzApp.repository;

import com.nci.webapp.AlzApp.model.Report;
import com.nci.webapp.AlzApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {

    @Query("select r from Report r join r.user u where u.username = :username")
    List<Report> findAllByUser (@Param("username") String username);

    @Query("SELECT r FROM Report r ORDER BY function('date', r.date) ASC")
    List<Report> findAllReportsOrderByDateAsc();

    @Query("select r from Report r where r.date <= :date")
    Report findReportByDate(
            @Param("date") Date date);

}
