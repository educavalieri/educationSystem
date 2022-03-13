package com.cavalieri.educationSystem.services;


import com.cavalieri.educationSystem.DTO.StudentDTO;
import com.cavalieri.educationSystem.entities.Student;
import com.cavalieri.educationSystem.factories.StudentFactory;
import com.cavalieri.educationSystem.repositories.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
public class StudentServiceTests {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;


    private Long existId;
    private Long noExistId;
    private Long countTotalStudents;
    private Integer countTotalStudentsList;
    private Student student;
    private StudentDTO studentDTO;
    private List<Student> students = new ArrayList<>();
    private List<StudentDTO> studentDTOS;
    private StudentRepository studentRepository1;

    @BeforeEach
    void setUp() throws Exception {

        existId = 1L;
        noExistId = 1000L;
        countTotalStudents = 2L;
        countTotalStudentsList = 2;
        student = StudentFactory.createStudentFull();
        studentDTOS = new ArrayList<>();
        students.add(new Student(1L,"Leonardo", "Alvares", 5, null));
        students.add(new Student(2L,"Rafael", "Alvares", 5, null));
        studentDTO = StudentFactory.createStudentDTOFull();

        Mockito.when(studentRepository.findById(noExistId)).thenThrow(RuntimeException.class);
        Mockito.when(studentRepository.findAll()).thenReturn(Arrays.asList(
                new Student(1L,"Leonardo", "Alvares", 5, null),
                new Student(2L,"Rafael", "Alvares", 5, null)));
        Mockito.when(studentRepository.findById(existId)).thenReturn(Optional.of(student));
        Mockito.when(studentRepository.save(ArgumentMatchers.any())).thenReturn(student);
        studentRepository1 = studentRepository;

    }

    @Test
    @Disabled
    public void findAllShouldReturnsListWhenCalled(){

        List<StudentDTO> studentList = new ArrayList<>();
        studentService.findAll();



        Assertions.assertNotNull(studentDTOS);
        Mockito.verify(studentRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals("Leonardo", studentList.get(0));

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

    @Test
    public void saveShouldInsertStudent(){

        StudentDTO dto = studentService.insert(studentDTO);

        Assertions.assertNotNull(dto);
        Assertions.assertTrue(dto.getName() == "Eduardo");
        Assertions.assertTrue(dto.getLastName() == "Cavalieri");
        Assertions.assertTrue(dto.getYear() == 42);

    }


}
