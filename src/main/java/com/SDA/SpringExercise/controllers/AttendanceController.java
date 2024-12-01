package com.SDA.SpringExercise.controllers;

import com.SDA.SpringExercise.models.Attendance;
import com.SDA.SpringExercise.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // Zobrazenie hlavnej stránky so zoznamom
    @GetMapping
    public String listAttendances(Model model) {
        model.addAttribute("attendances", attendanceService.getAllAttenadances());
        return "list";
    }

    // Formulár pre novú dochádzku
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        return "form";
    }

    // Uloženie novej dochádzky
    @PostMapping("/new")
    public String addAttendance(@ModelAttribute Attendance attendance) {
        attendanceService.addAttendance(attendance);
        return "redirect:/attendance";
    }

    // Zobrazenie formulára pre úpravu
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("attendance", attendanceService.getAttendancesById(id).orElseThrow());
        return "form";
    }

    // Uloženie úprav
    @PostMapping("/edit/{id}")
    public String updateAttendance(@PathVariable Long id, @ModelAttribute Attendance attendance) {
        attendanceService.updateAttendance(id, attendance);
        return "redirect:/attendance";
    }

    // Vymazanie záznamu
    @GetMapping("/delete/{id}")
    public String deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return "redirect:/attendance";
    }

    // Zvýšenie odpracovaných hodín
    @PostMapping("/increase-hours/{id}")
    public String increaseHours(@PathVariable Long id, @RequestParam int additionalHours) {
        attendanceService.increaseWorkedHours(id, additionalHours);
        return "redirect:/attendance";
    }

    // Vyhľadávanie podľa mena
    @GetMapping("/search")
    public String searchByName(@RequestParam("employeeName") String employeeName, Model model) {
        model.addAttribute("attendances", attendanceService.getAttenadancesByName(employeeName));
        return "list";
    }

    // Vyhľadávanie podľa dátumu
    @GetMapping("/search-date")
    public String searchByDate(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                               Model model) {
        model.addAttribute("attendances", attendanceService.getAttenadancesByDate(date));
        return "list";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }

    // Zobrazenie len prítomných
    @GetMapping("/present")
    public String showPresent(Model model) {
        model.addAttribute("attendances", attendanceService.getAllPresent());
        return "list";
    }

    @ControllerAdvice
    public static class GlobalExceptionHandler {
        @ExceptionHandler(RuntimeException.class)
        public String handleRuntimeException(RuntimeException ex, Model model) {
            model.addAttribute("error", ex.getMessage());
            return "error";
        }
    }
}