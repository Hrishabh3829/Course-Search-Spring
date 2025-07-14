package com.example.coursesearch.controller;

import com.example.coursesearch.document.CourseDocument;
import com.example.coursesearch.service.CourseSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class CourseSearchController {

    private final CourseSearchService courseSearchService;

    @Autowired
    public CourseSearchController(CourseSearchService courseSearchService) {
        this.courseSearchService = courseSearchService;
    }

    @GetMapping
    public Map<String, Object> searchCourses(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String startDate,
            @RequestParam(defaultValue = "upcoming") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return courseSearchService.search(q, category, type, minAge, maxAge, minPrice, maxPrice, startDate, sort, page, size);
    }
}
