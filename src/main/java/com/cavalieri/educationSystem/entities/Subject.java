package com.cavalieri.educationSystem.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_subject")
public class Subject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "subject")
    private List<Professor> professors = new ArrayList<>();

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students = new ArrayList<>();

    public Subject() {

    }

    public Subject(Long id, String name, List<Professor> professors, List<Student> students) {
        this.id = id;
        this.name = name;
        this.professors = professors;
        this.students = students;
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

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) && Objects.equals(name, subject.name) && Objects.equals(professors, subject.professors) && Objects.equals(students, subject.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, professors, students);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", professors=" + professors +
                ", students=" + students +
                '}';
    }
}
