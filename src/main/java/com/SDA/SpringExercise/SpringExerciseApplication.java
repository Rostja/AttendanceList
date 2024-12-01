package com.SDA.SpringExercise;

import com.SDA.SpringExercise.models.Attendance;
import com.SDA.SpringExercise.repositories.AttendanceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringExerciseApplication implements CommandLineRunner {
	private final AttendanceRepository attendanceRepository;

    public SpringExerciseApplication(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(SpringExerciseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		List<Attendance> attendances = new ArrayList<>(List.of(
				new Attendance("Peter", 10, true, LocalDate.now())
		));
		// Add a new employee's attendance
		Attendance newAttendance = new Attendance("John", 8, true, LocalDate.now());
		attendances.add(newAttendance);

		// Save all attendances
		attendanceRepository.saveAll(attendances);

		// Print confirmation
		System.out.println("New attendance added: " + newAttendance.getEmployeeName());
	}
}
