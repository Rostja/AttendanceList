package com.SDA.SpringExercise.services;

import com.SDA.SpringExercise.models.Attendance;

import com.SDA.SpringExercise.repositories.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceServiceImpl(AttendanceRepository attendanceRepository){
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public List<Attendance> getAllAttenadances() {
        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> getAttenadancesByName(String employeeName) {
        return attendanceRepository.findByEmployeeNameContainingIgnoreCase(employeeName);
    }

    @Override
    public List<Attendance> getAttenadancesByDate(LocalDate date) {
        return attendanceRepository.findAttendancesByDate(date);
    }

    @Override
    public Attendance addAttendance(Attendance attendance) {
        try {
            return attendanceRepository.save(attendance);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save attendance: " + e.getMessage());
        }
    }

    @Override
    public Attendance increaseWorkedHours(Long id, int additionalHours) {
        return attendanceRepository.findById(id)
                .map(attendance -> {
                    attendance.setWorkedHours(attendance.getWorkedHours() + additionalHours);
                    return attendanceRepository.save(attendance);
                })
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + id));
    }

    @Override
    public Attendance updateAttendance(Long id, Attendance updatedAttendance) {
        if (!attendanceRepository.existsById(id)) {
            throw new RuntimeException("Attendance not found with id: " + id);
        }
        updatedAttendance.setId(id);
        return attendanceRepository.save(updatedAttendance);
    }


    @Override
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);

    }

    @Override
    public List<Attendance> getAllPresent() {
        return attendanceRepository.findAllPresentAttendances();
    }

    @Override
    public Optional<Attendance> getAttendancesById(Long id) {
        return attendanceRepository.findById(id);
    }
}
