package com.cavalieri.educationSystem.DTO;

import com.cavalieri.educationSystem.entities.Student;
import com.cavalieri.educationSystem.entities.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentDTO implements Serializable {

    private String name;
    private String lastName;
    private Integer year;
    private List<SubjectDTO> subjects = new ArrayList<>();

    public StudentDTO(){

    }

    public StudentDTO(String name, String lastName, Integer year, List<SubjectDTO> subjects) {
        this.name = name;
        this.lastName = lastName;
        this.year = year;
        this.subjects = subjects;
    }

    public StudentDTO(Student entity) {
        name = entity.getName();
        lastName = entity.getLastName();
        year = entity.getYear();
    }

    public StudentDTO(Student entity, List<Subject> subjects) {
        this(entity);
        subjects.forEach( x -> this.subjects.add( new SubjectDTO(x)));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(year, that.year) && Objects.equals(subjects, that.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, year, subjects);
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", year=" + year +
                ", subjects=" + subjects +
                '}';
    }
}



