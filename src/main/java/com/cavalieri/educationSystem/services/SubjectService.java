package com.cavalieri.educationSystem.services;

import com.cavalieri.educationSystem.DTO.ProfessorDTO;
import com.cavalieri.educationSystem.DTO.SubjectDTO;
import com.cavalieri.educationSystem.entities.Subject;
import com.cavalieri.educationSystem.repositories.ProfessorRepository;
import com.cavalieri.educationSystem.repositories.StudentRepository;
import com.cavalieri.educationSystem.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional
    public List<SubjectDTO> findAll(){
        List<Subject> entity = subjectRepository.findAll();
        List<SubjectDTO> dto = entity.stream().map( x -> new SubjectDTO(x, x.getProfessors(), x.getStudents())).collect(Collectors.toList());
        return dto;
    }

    @Transactional
    public List<ProfessorDTO> findAllBySubject(Long id){
        Subject subject = subjectRepository.findById(id).get();
        List<ProfessorDTO> dto = professorRepository.findByIdSubject(subject);
        return dto;
    }


}
