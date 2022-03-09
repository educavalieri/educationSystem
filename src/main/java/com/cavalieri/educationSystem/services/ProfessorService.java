package com.cavalieri.educationSystem.services;

import com.cavalieri.educationSystem.DTO.ProfessorDTO;
import com.cavalieri.educationSystem.DTO.ProfessorTestDTO;
import com.cavalieri.educationSystem.entities.Professor;
import com.cavalieri.educationSystem.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional
    public List<ProfessorDTO> findAll() {
        List<Professor> entity = professorRepository.findAll();
        List<ProfessorDTO> dto = entity.stream().map( x -> new ProfessorDTO(x)).collect(Collectors.toList());
        return dto;
    }

    @Transactional
    public List<ProfessorTestDTO> findAllTest() {
        List<Professor> entity = professorRepository.findAll();
        List<ProfessorTestDTO> dto = entity.stream().map( x -> new ProfessorTestDTO(x)).collect(Collectors.toList());
        return dto;
    }

    @Transactional
    public ProfessorDTO findById(Long id){
        Professor entity = professorRepository.findById(id).orElseThrow( () -> new RuntimeException("entity not found"));
        ProfessorDTO dto = new ProfessorDTO(entity);
        return dto;
    }

    @Transactional
    public ProfessorDTO save(ProfessorDTO dto){
        Professor entity = new Professor();
        entity = copyToDTO(entity, dto);
        professorRepository.save(entity);
        return new ProfessorDTO(entity);
    }

    @Transactional
    public ProfessorDTO update(ProfessorDTO dto, Long id){
        Professor entity = professorRepository.findById(id).orElseThrow( () -> new RuntimeException("entity not found"));
        entity = copyToDTO(entity, dto);
        professorRepository.save(entity);
        return new ProfessorDTO(entity);
    }

    @Transactional
    public void delete(Long id){
        try {
            professorRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("entity not found");
        }

    }


    public Professor copyToDTO(Professor entity, ProfessorDTO dto) {
        entity.setName(dto.getName());
//        entity.setSubject(dto.getSubject());
        return entity;
    }
}
