package com.cavalieri.educationSystem.controlers;

import com.cavalieri.educationSystem.DTO.StudentDTO;
import com.cavalieri.educationSystem.services.StudentService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
public class StudentResource {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentDTO>>findAll(){
        List<StudentDTO> dto = studentService.findAll();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable("id") Long id){
        StudentDTO dto = studentService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<StudentDTO> save(@RequestBody StudentDTO dto){
        dto = studentService.insert(dto);
        return ResponseEntity.created(null).body(dto);
    }

    @PostMapping(value = "/update{id}")
    public ResponseEntity<StudentDTO> update(@RequestBody StudentDTO dto,
                                             @PathVariable("id") Long id){
        dto = studentService.update(dto, id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/delete{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
