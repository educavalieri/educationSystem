package com.cavalieri.educationSystem.factories;

import com.cavalieri.educationSystem.DTO.StudentDTO;
import com.cavalieri.educationSystem.entities.Student;
import com.cavalieri.educationSystem.entities.Subject;

import java.util.ArrayList;

public class StudentFactory {


    public static Student createStudentNull(){
        Student student = new Student();
        return student;
    }

    public static Student createStudentFull(){
        Student student = new Student(null, "Eduardo", "Cavalieri", 42, new ArrayList<Subject>());
        return student;
    }

    public static StudentDTO createStudentDTOFull() {

        Student student = createStudentFull();
        StudentDTO studentDTO = new StudentDTO(student);
        return studentDTO;
    }
}
