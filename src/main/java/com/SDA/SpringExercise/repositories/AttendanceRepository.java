package com.SDA.SpringExercise.repositories;

import com.SDA.SpringExercise.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    List<Attendance> findByEmployeeNameContainingIgnoreCase(String employeeName);

    List<Attendance> findAttendancesByDate(LocalDate date);

    @Query("Select a FROM Attendance a WHERE a.isPresent = true")
    List<Attendance> findAllPresentAttendances();

}
