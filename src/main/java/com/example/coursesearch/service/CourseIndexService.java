package com.example.coursesearch.service;

import com.example.coursesearch.document.CourseDocument;
import com.example.coursesearch.repository.CourseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.List;

@Component
public class CourseIndexService {

    @Autowired
    private CourseRepository courseRepository;

    @PostConstruct
    public void init() {
        try {
            // Read JSON from file
            InputStream inputStream = new ClassPathResource("sample-courses.json").getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            // Deserialize into list of courses
            List<CourseDocument> courses = mapper.readValue(inputStream, new TypeReference<>() {});

            // Set autocomplete suggest field
            for (CourseDocument course : courses) {
                course.setSuggest(course.getTitle());
            }


            // Save all to Elasticsearch
            courseRepository.saveAll(courses);
            System.out.println("👍Courses indexed into Elasticsearch: " + courses.size());

        } catch (Exception e) {
            System.err.println("👎Failed to index sample data: " + e.getMessage());
        }
    }
}
