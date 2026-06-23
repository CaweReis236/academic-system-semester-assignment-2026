package org.example;

import java.util.ArrayList;
import java.util.List;

public class AcademicClass {

    private String code;
    private String name;

    private List<Assessment> assessments;

    public AcademicClass(String code, String name) {
        this.code = code;
        this.name = name;
        this.assessments = new ArrayList<>();
    }

    public void addAssessment(Assessment assessment) {
        if (assessment != null) {
            assessments.add(assessment);
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }
}
