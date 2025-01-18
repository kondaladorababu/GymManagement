package com.app.gymManagement.service;

import com.app.gymManagement.model.Classes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassesService {
    private final List<Classes> classesList = new ArrayList<>(); // In-memory storage

    public ResponseEntity<?> createClass(Classes newClass) {
        if (newClass.getCapacity() < 1) {
            return ResponseEntity.badRequest().body("Capacity must be at least 1.");
        }

        if (newClass.getEndDate().isBefore(newClass.getStartDate())) {
            return ResponseEntity.badRequest().body("End date must be after start date.");
        }

        for (Classes c : classesList) {
            if (c.getName().equalsIgnoreCase(newClass.getName()) && c.getStartDate().equals(newClass.getStartDate())) {
                return ResponseEntity.badRequest().body("Class already exists.");
            }
        }

        classesList.add(newClass);
        return ResponseEntity.ok(newClass);
    }

    public List<Classes> getAllClasses() {
        return classesList;
    }

    public Classes getClassByName(String className) {
        for (Classes c : classesList) {
            if (c.getName().equalsIgnoreCase(className)) {
                return c;
            }
        }
        return null;
    }
}
