package com.cavalieri.educationSystem.services;

import com.cavalieri.educationSystem.DTO.StudentDTO;
import com.cavalieri.educationSystem.DTO.SubjectDTO;
import com.cavalieri.educationSystem.entities.Student;
import com.cavalieri.educationSystem.entities.Subject;
import com.cavalieri.educationSystem.repositories.StudentRepository;
import com.cavalieri.educationSystem.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public List<StudentDTO> findAll(){
        List<Student> entity = studentRepository.findAll();
        List<StudentDTO> dto = entity.stream().map(x -> new StudentDTO(x, x.getSubjects())).collect(Collectors.toList());
        List<SubjectDTO> subjectDTO = new ArrayList<>();
        return dto;
    }

    @Transactional
    public StudentDTO findById(Long id){
        Student entity = studentRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Entity not found"));
        StudentDTO dto = new StudentDTO(entity);
        return dto;
    }

    @Transactional
    public StudentDTO insert(StudentDTO dto){
        Student student = new Student();
        student = copyDtoToEntity(student , dto);
        student = studentRepository.save(student);
        StudentDTO newDto = new StudentDTO(student);
        return newDto;

    }

    @Transactional
    public StudentDTO update(StudentDTO dto, Long id){
        Student student = studentRepository.findById(id).orElseThrow( () -> new RuntimeException("Entity not found"));
        student = copyDtoToEntity(student, dto);
        student = studentRepository.save(student);
        StudentDTO newDto = new StudentDTO(student);
        return newDto;

    }

    @Transactional
    public void delete(Long id){
        Student student = studentRepository.findById(id).orElseThrow( () -> new RuntimeException("Entity not found"));
        studentRepository.delete(student);

    }

    public Student copyDtoToEntity(Student student, StudentDTO dto){
        student.setName(dto.getName());
        student.setLastName(dto.getLastName());
        student.setYear(dto.getYear());
        return student;
    }



}
