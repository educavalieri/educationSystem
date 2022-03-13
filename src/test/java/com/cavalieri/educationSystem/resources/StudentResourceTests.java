package com.cavalieri.educationSystem.resources;

import com.cavalieri.educationSystem.DTO.StudentDTO;
import com.cavalieri.educationSystem.TokenUtil;
import com.cavalieri.educationSystem.entities.Student;
import com.cavalieri.educationSystem.factories.StudentFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class StudentResourceTests {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String username;
    private String password;
    private Long existId;
    private Long noExistId;
    private Long countTotalStudents;
    private Integer countTotalStudentsList;
    private Student student;
    private StudentDTO studentDTO;
    private List<Student> students = new ArrayList<>();
    private List<StudentDTO> studentDTOS;

    @BeforeEach
    void setup() throws Exception {

        username = "ana@gmail.com";
        password = "123456";
        existId = 1L;
        noExistId = 1000L;
        countTotalStudents = 2L;
        countTotalStudentsList = 2;
        student = StudentFactory.createStudentFull();
        studentDTOS = new ArrayList<>();
        students.add(new Student(1L,"Leonardo", "Alvares", 5, null));
        students.add(new Student(2L,"Rafael", "Alvares", 5, null));
        studentDTO = StudentFactory.createStudentDTOFull();


    }



    @Test
    public void findByIdShouldReturnUnauthorizedWhenNoTokenGiven() throws Exception {

        ResultActions result =
                mockMvc.perform(get("/movies/{id}", existId)
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isUnauthorized());
    }

    @Test
    public void findAllShouldReturnsStudentsDto() throws Exception {

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

        String jsonBody = objectMapper.writeValueAsString(studentDTOS);

        ResultActions result = mockMvc.perform(get("/students")
                .header("Authorization", "Bearer " + accessToken)
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                );
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$[0].name").value("Leonardo"));
        result.andExpect(jsonPath("$[1].name").value("Rafael"));
    }

    @Test
    public void findByIdShouldReturnsStudentDto() throws Exception{

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

        ResultActions result =
                mockMvc.perform(get("/students/{id}", existId)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.name").value("Leonardo"));
        result.andExpect(jsonPath("$.lastName").value("Cavalieri"));

    }

    @Test
    public void findByIdShouldReturnsExceptionWhnIdNotExists() throws Exception{

        String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

        ResultActions result =
                mockMvc.perform(get("/students/{id}", noExistId)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }



}
