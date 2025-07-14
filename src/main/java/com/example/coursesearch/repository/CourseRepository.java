package com.example.coursesearch.repository;

import com.example.coursesearch.document.CourseDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends ElasticsearchRepository<CourseDocument, String> {
    List<CourseDocument> findByTitle(String title);
}
