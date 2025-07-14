package com.example.coursesearch;

import com.example.coursesearch.document.CourseDocument;
import com.example.coursesearch.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
public class CourseSearchIntegrationTest {

    @Container
    static ElasticsearchContainer container = new ElasticsearchContainer("docker.elastic.co/elasticsearch/elasticsearch:7.17.0");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.elasticsearch.uris", container::getHttpHostAddress);
    }

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    void shouldIndexAndFindCourse() {
        CourseDocument course = new CourseDocument();
        course.setId("test-001");
        course.setTitle("Fuzzy Logic");
        course.setDescription("Advanced AI concepts");
        course.setCategory("Tech");
        course.setType("COURSE");
        course.setGradeRange("9th");
        course.setMinAge(14);
        course.setMaxAge(16);
        course.setPrice(100.0);
        course.setNextSessionDate(ZonedDateTime.now());
        course.setSuggest("Fuzzy Logic");

        courseRepository.save(course);

        List<CourseDocument> found = courseRepository.findByTitle("Fuzzy Logic");
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getTitle()).isEqualTo("Fuzzy Logic");
    }
}
