package com.cavalieri.educationSystem.repositories;

import com.cavalieri.educationSystem.entities.Student;
import com.cavalieri.educationSystem.factories.StudentFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    private Long existId;
    private Long noExistId;
    private Long countTotalStudents;
    private Integer countTotalStudentsList;

    @BeforeEach
    void setUp() throws Exception {

        existId = 1L;
        noExistId = 1000L;
        countTotalStudents = 2L;
        countTotalStudentsList = 2;
    }

    @Test
    public void shouldReturnsTotalStudentsWhenFindAll(){
        List<Student> student = studentRepository.findAll();

        Assertions.assertEquals(student.size(), countTotalStudentsList);
    }

    @Test
    public void shouldReturnsStudentWhenExistsId(){
        Student student = studentRepository.findById(existId).get();

        Assertions.assertNotNull(student);
        Assertions.assertEquals(student.getId(), existId);
    }

    @Test
    public void shouldReturnsExceptionWhenNoExistsId(){
        Optional<Student> student = studentRepository.findById(noExistId);

        Assertions.assertTrue(student.isEmpty());
    }

    @Test
    public void saveShouldReturnsEntityWhenNotNullStudent(){

        Student student = StudentFactory.createStudentFull();

        student = studentRepository.save(student);

        Assertions.assertTrue(student.getName() == "Eduardo");
    }

    @Test
    public void deleteShouldWhenIdExists(){

        studentRepository.deleteById(existId);

        Optional<Student> result = studentRepository.findById(existId);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdNotExists(){

        Assertions.assertThrows(EmptyResultDataAccessException.class, () ->{
            studentRepository.deleteById(noExistId);
        });
    }






}
