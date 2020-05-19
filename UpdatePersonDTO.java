package com.codegym.task.task17.task1711;

import java.util.Date;

public class UpdatePersonDTO implements PersonDTO{
    public int id;
    public String name;
    public Sex sex;
    public Date birthDate;

    public UpdatePersonDTO() {}

    public UpdatePersonDTO(int id, String name, String sex, Date birthDate) {
        this.id = id;
        this.name = name;
        if (sex.contentEquals("m")) { this.sex = Sex.MALE; }
        else { this.sex = Sex.FEMALE; }
        this.birthDate = birthDate;
    }
}
