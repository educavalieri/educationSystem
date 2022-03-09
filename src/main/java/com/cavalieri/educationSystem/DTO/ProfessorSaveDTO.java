package com.cavalieri.educationSystem.DTO;

import java.io.Serializable;
import java.util.Objects;

public class ProfessorSaveDTO implements Serializable {

    private String name;

    public ProfessorSaveDTO(){
    }

    public ProfessorSaveDTO(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorSaveDTO that = (ProfessorSaveDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ProfessorSaveDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
