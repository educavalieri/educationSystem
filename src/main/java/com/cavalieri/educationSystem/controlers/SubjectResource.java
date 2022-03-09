package com.cavalieri.educationSystem.controlers;

import com.cavalieri.educationSystem.DTO.ProfessorDTO;
import com.cavalieri.educationSystem.DTO.SubjectDTO;
import com.cavalieri.educationSystem.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "subjects")
public class SubjectResource {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> findAll(){
        List<SubjectDTO> dto = subjectService.findAll();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/findSub{id}")
    public ResponseEntity<List<ProfessorDTO>> findBySubject(@PathVariable("id") Long id){
        List<ProfessorDTO> dto = subjectService.findAllBySubject(id);
        return ResponseEntity.ok().body(dto);
    }
}

