package com.example.labsevenrecyclerviews;

/**
 * Model class: holds the data of ONE course.
 */
public class Course {

    private String code;
    private String title;
    private String instructor;
    private String email;
    private String description;
    private int creditHours;

    public Course(String code, String title, String instructor, String email,
                  String description, int creditHours) {
        this.code = code;
        this.title = title;
        this.instructor = instructor;
        this.email = email;
        this.description = description;
        this.creditHours = creditHours;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public int getCreditHours() {
        return creditHours;
    }
}
