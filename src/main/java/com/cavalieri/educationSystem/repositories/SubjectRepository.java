package com.cavalieri.educationSystem.repositories;

import com.cavalieri.educationSystem.DTO.ProfessorDTO;
import com.cavalieri.educationSystem.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {


}
