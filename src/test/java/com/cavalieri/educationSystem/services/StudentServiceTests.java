package com.cavalieri.educationSystem.services;


import com.cavalieri.educationSystem.DTO.StudentDTO;
import com.cavalieri.educationSystem.entities.Student;
import com.cavalieri.educationSystem.factories.StudentFactory;
import com.cavalieri.educationSystem.repositories.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class StudentServiceTests {

    private Long existId;
    private Long noExistId;
    private Long countTotalStudents;
    private Integer countTotalStudentsList;
    private Student student;
    private StudentDTO studentDTO;
    private List<Student> students;
    private List<StudentDTO> studentDTOS;

    @BeforeEach
    void setUp() throws Exception {

        existId = 1L;
        noExistId = 1000L;
        countTotalStudents = 2L;
        countTotalStudentsList = 2;
        student = StudentFactory.createStudentFull();
        students = new ArrayList<>();
        studentDTOS = new ArrayList<>();
        studentDTO = StudentFactory.createStudentDTOFull();

        Mockito.when(studentRepository.findById(noExistId)).thenThrow(RuntimeException.class);
        Mockito.when(studentRepository.findAll((Sort) ArgumentMatchers.any())).thenReturn(students);
        Mockito.when(studentRepository.findById(existId)).thenReturn(Optional.of(student));
        Mockito.when(studentRepository.save(ArgumentMatchers.any())).thenReturn(student);

    }


    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Test
    public void findAllShouldReturnsListWhenCalled(){

        List<StudentDTO> studentsDto = studentService.findAll();


        Assertions.assertNotNull(studentsDto);
        Mockito.verify(studentRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void findByIdShouldReturnsExistsId(){

        StudentDTO studentDTO = studentService.findById(existId);

        Assertions.assertNotNull(studentDTO);
        Mockito.verify(studentRepository, Mockito.times(1)).findById(existId);
        Assertions.assertTrue(studentDTO.getName() == "Eduardo");

    }

    @Test
    public void findByIdShouldReturnsExceptionWhenNoExistsId(){

        Assertions.assertThrows(RuntimeException.class, () -> {
            studentService.findById(noExistId);
        });
    }


}
