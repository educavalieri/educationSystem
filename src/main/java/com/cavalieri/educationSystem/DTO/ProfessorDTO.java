package com.cavalieri.educationSystem.DTO;

import com.cavalieri.educationSystem.entities.Professor;
import com.cavalieri.educationSystem.entities.Subject;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

public class ProfessorDTO implements Serializable {

    private Long id;
    private String name;
    private SubjectDTO subjectDTO;

    public ProfessorDTO(){

    }

    public ProfessorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProfessorDTO(Long id, String name, SubjectDTO subjectDTO) {
        this.id = id;
        this.name = name;
        this.subjectDTO = subjectDTO;
    }

    public ProfessorDTO(Professor entity) {
        id = entity.getId();
        name = entity.getName();
        subjectDTO = new SubjectDTO(entity.getSubject());
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

    public SubjectDTO getSubjectDTO() {
        return subjectDTO;
    }

    public void setSubjectDTO(SubjectDTO subjectDTO) {
        this.subjectDTO = subjectDTO;
    }
}
