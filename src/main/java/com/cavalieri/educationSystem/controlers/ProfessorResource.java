package com.cavalieri.educationSystem.controlers;

import com.cavalieri.educationSystem.DTO.ProfessorDTO;
import com.cavalieri.educationSystem.DTO.ProfessorSaveDTO;
import com.cavalieri.educationSystem.DTO.ProfessorTestDTO;
import com.cavalieri.educationSystem.services.ProfessorService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/professors")
public class ProfessorResource {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> findAll(){
        List<ProfessorDTO> dto = professorService.findAll();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/findAllTest")
    public ResponseEntity<List<ProfessorTestDTO>> findAllTest(){
        List<ProfessorTestDTO> dto = professorService.findAllTest();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfessorDTO> findById(@PathVariable("id") Long id){
        ProfessorDTO dto = professorService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ProfessorDTO> save(@RequestBody ProfessorSaveDTO dto){
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setName(dto.getName());
        professorDTO = professorService.save(professorDTO);
        return ResponseEntity.ok().body(professorDTO);
    }

    @DeleteMapping(value = "/delete{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        professorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
