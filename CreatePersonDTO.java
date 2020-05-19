package com.codegym.task.task17.task1711;

import java.util.Date;

public class CreatePersonDTO implements PersonDTO{
    public String name;
    public Sex sex;
    public Date birthDate;

    public CreatePersonDTO() {}

    public CreatePersonDTO(String name, String sex, Date birthDate) {
        this.name = name;
        if (sex.contentEquals("m")) { this.sex = Sex.MALE; }
        else { this.sex = Sex.FEMALE; }
        this.birthDate = birthDate;
    }
}
