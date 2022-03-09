package com.cavalieri.educationSystem.DTO;

import com.cavalieri.educationSystem.entities.Professor;

import java.io.Serializable;

public class ProfessorTestDTO implements Serializable {

    private Long id;
    private String name;
    private Long subject_id;
    private String subject_name;

    public ProfessorTestDTO() {
    }

    public ProfessorTestDTO(Long id, String name, Long subject_id, String subject_name) {
        this.id = id;
        this.name = name;
        this.subject_id = subject_id;
        this.subject_name = subject_name;
    }

    public ProfessorTestDTO(Professor entity) {
        id = entity.getId();
        name = entity.getName();
        subject_id = entity.getSubject().getId();
        subject_name = entity.getSubject().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
}
