package com.codegym.task.task17.task1711;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class PersonRepository {
    public static void assign(PersonDTO personDTO) {
        if(personDTO instanceof CreatePersonDTO) {create((CreatePersonDTO)personDTO);}
        else if(personDTO instanceof UpdatePersonDTO) {update((UpdatePersonDTO)personDTO);}
        else if(personDTO instanceof DeletePersonDTO) {delete((DeletePersonDTO)personDTO);}
        else if(personDTO instanceof IndexPersonDTO) {index((IndexPersonDTO)personDTO);}
    }

    public static void create(CreatePersonDTO createpersonDTO) {
        if(createpersonDTO.sex == Sex.MALE) {
        Solution.allPeople.add(Person.createMale(createpersonDTO.name, createpersonDTO.birthDate));}
        else {
            Solution.allPeople.add(Person.createFemale(createpersonDTO.name, createpersonDTO.birthDate));}

        System.out.println(Solution.allPeople.size() - 1);  //print id number of new item
    }

    public static void update(UpdatePersonDTO updatePersonDTO) {
        Solution.allPeople.get(updatePersonDTO.id).setName(updatePersonDTO.name);
        Solution.allPeople.get(updatePersonDTO.id).setSex(updatePersonDTO.sex);
        Solution.allPeople.get(updatePersonDTO.id).setBirthDate(updatePersonDTO.birthDate);
    }

    public static void delete(DeletePersonDTO deletePersonDTO) {
        Solution.allPeople.get(deletePersonDTO.id).setName(null);
        Solution.allPeople.get(deletePersonDTO.id).setSex(null);
        Solution.allPeople.get(deletePersonDTO.id).setBirthDate(null);
    }

    public static void index(IndexPersonDTO indexPersonDTO) {
        String name = Solution.allPeople.get(indexPersonDTO.id).getName();
        Sex enumSex = Solution.allPeople.get(indexPersonDTO.id).getSex();
        String sex;
        {
            if (enumSex.equals(Sex.MALE))
                sex = "m";
            else
                sex = "f";
        }
        String birthDate = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH)
                .format(Solution.allPeople.get(indexPersonDTO.id).getBirthDate());
        System.out.println(String.format("%1s%2s %3s", name, sex, birthDate));
    }
}
