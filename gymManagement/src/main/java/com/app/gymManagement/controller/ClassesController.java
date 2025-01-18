package com.app.gymManagement.controller;

import com.app.gymManagement.model.Classes;
import com.app.gymManagement.service.ClassesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassesController {
    private final ClassesService classesService;

    public ClassesController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @PostMapping
    public ResponseEntity<?> createClass(@RequestBody Classes newClass) {
        return classesService.createClass(newClass);
    }

    @GetMapping
    public List<Classes> getAllClasses() {
        return classesService.getAllClasses();
    }
}
