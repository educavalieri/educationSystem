package com.cavalieri.educationSystem.DTO;

import com.cavalieri.educationSystem.entities.Professor;
import com.cavalieri.educationSystem.entities.Student;
import com.cavalieri.educationSystem.entities.Subject;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SubjectDTO implements Serializable {

    private String name;
    private List<ProfessorDTO> professors = new ArrayList<>();
    private List<StudentDTO> students = new ArrayList<>();

    public SubjectDTO() {

    }

    public SubjectDTO(String name, List<ProfessorDTO> professors, List<StudentDTO> students) {
        this.name = name;
        this.professors = professors;
        this.students = students;
    }

    public SubjectDTO(Subject entity){
        name = entity.getName();
    }

    public SubjectDTO(Subject entity, List<Professor> professors, List<Student> students) {
        this(entity);
        professors.forEach( x -> this.professors.add(new ProfessorDTO(x)));
        students.forEach( x -> this.students.add(new StudentDTO(x)));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProfessorDTO> getProfessors() {
        return professors;
    }

    public void setProfessors(List<ProfessorDTO> professors) {
        this.professors = professors;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectDTO that = (SubjectDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(professors, that.professors) && Objects.equals(students, that.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, professors, students);
    }

    @Override
    public String toString() {
        return "SubjectDTO{" +
                "name='" + name + '\'' +
                ", professors=" + professors +
                ", students=" + students +
                '}';
    }
}
