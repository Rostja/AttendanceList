package com.SDA.SpringExercise.services;

import com.SDA.SpringExercise.models.Attendance;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface AttendanceService {

    List<Attendance> getAllAttenadances();

    List<Attendance> getAttenadancesByName(String employeeName);

    List<Attendance> getAttenadancesByDate(LocalDate date);

    Attendance addAttendance(Attendance attendance);

    Attendance increaseWorkedHours(Long id, int additionalHours);

    Attendance updateAttendance(Long id, Attendance updatedAttendance);

    void deleteAttendance(Long id);

    List<Attendance> getAllPresent();

    Optional<Attendance> getAttendancesById(Long id);

}

