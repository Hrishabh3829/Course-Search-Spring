package com.example.coursesearch.service;

import com.example.coursesearch.document.CourseDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
//@RequiredArgsConstructor
public class CourseSearchService {
    public List<String> suggest(String q) {
        Criteria criteria = new Criteria("suggest").startsWith(q);
        CriteriaQuery query = new CriteriaQuery(criteria);
        query.setMaxResults(10);

        SearchHits<CourseDocument> hits = elasticsearchOperations.search(query, CourseDocument.class);

        return hits.stream()
                .map(hit -> hit.getContent().getTitle())
                .distinct()
                .toList();
    }

    private final ElasticsearchOperations elasticsearchOperations;
    @Autowired
    public CourseSearchService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public Map<String, Object> search(String q, String category, String type,
                                      Integer minAge, Integer maxAge,
                                      Double minPrice, Double maxPrice,
                                      String startDate, String sort,
                                      int page, int size) {

        Criteria criteria = new Criteria();

        if (q != null && !q.isEmpty()) {
            criteria = criteria.and(new Criteria("title").matches(q)).or(new Criteria("description").matches(q));
        }

        if (category != null) criteria = criteria.and(new Criteria("category").is(category));
        if (type != null) criteria = criteria.and(new Criteria("type").is(type));
        if (minAge != null || maxAge != null) {
            Criteria age = new Criteria("minAge");
            if (minAge != null) age = age.greaterThanEqual(minAge);
            if (maxAge != null) age = age.lessThanEqual(maxAge);
            criteria = criteria.and(age);
        }
        if (minPrice != null || maxPrice != null) {
            Criteria price = new Criteria("price");
            if (minPrice != null) price = price.greaterThanEqual(minPrice);
            if (maxPrice != null) price = price.lessThanEqual(maxPrice);
            criteria = criteria.and(price);
        }
        if (startDate != null) {
            try {
                ZonedDateTime zdt = ZonedDateTime.parse(startDate);
                criteria = criteria.and(new Criteria("nextSessionDate").greaterThanEqual(zdt));
            } catch (DateTimeParseException ignored) {}
        }

        Pageable pageable = PageRequest.of(page, size);
        CriteriaQuery query = new CriteriaQuery(criteria, pageable);

        SearchHits<CourseDocument> searchHits = elasticsearchOperations.search(query, CourseDocument.class);

        List<CourseDocument> courses = searchHits.stream().map(hit -> hit.getContent()).toList();

        Map<String, Object> response = new HashMap<>();
        response.put("total", searchHits.getTotalHits());
        response.put("courses", courses);

        return response;
    }
}
