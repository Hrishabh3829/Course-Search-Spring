package com.example.coursesearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.ZonedDateTime;

@Document(indexName = "courses")
public class CourseDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword)
    private String type;

    @Field(type = FieldType.Keyword)
    private String gradeRange;

    @Field(type = FieldType.Integer)
    private int minAge;

    @Field(type = FieldType.Integer)
    private int maxAge;

    @Field(type = FieldType.Double)
    private double price;

    @Field(type = FieldType.Date)
    private ZonedDateTime nextSessionDate;

    // Autocomplete field (as plain text for compatibility)
    @Field(type = FieldType.Text)
    private String suggest;


    public CourseDocument() {}

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getGradeRange() { return gradeRange; }
    public void setGradeRange(String gradeRange) { this.gradeRange = gradeRange; }

    public int getMinAge() { return minAge; }
    public void setMinAge(int minAge) { this.minAge = minAge; }

    public int getMaxAge() { return maxAge; }
    public void setMaxAge(int maxAge) { this.maxAge = maxAge; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public ZonedDateTime getNextSessionDate() { return nextSessionDate; }
    public void setNextSessionDate(ZonedDateTime nextSessionDate) { this.nextSessionDate = nextSessionDate; }

    public String getSuggest() { return suggest; }
    public void setSuggest(String suggest) { this.suggest = suggest; }
}
