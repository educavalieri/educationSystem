package com.cavalieri.educationSystem.repositories;

import com.cavalieri.educationSystem.DTO.ProfessorDTO;
import com.cavalieri.educationSystem.entities.Professor;
import com.cavalieri.educationSystem.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("SELECT DISTINCT obj from Professor obj INNER JOIN obj.subject subj WHERE " +
            " (:subject IS NULL OR :subject IN subj )" )
    List<ProfessorDTO> findByIdSubject(Subject subject);

}
