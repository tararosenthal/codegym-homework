package com.codegym.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

    static ArrayList<PersonDTO> personDTOs = new ArrayList<>();

    public static ArrayList<PersonDTO> parseCommand(String[] input) throws ParseException {
        parseNextPerson(input);
        return personDTOs;
    }

    public static void createPerson(String name, String sex, Date birthDate, String[] input) throws ParseException {
        CreatePersonDTO createPersonDTO = new CreatePersonDTO(name, sex, birthDate);
        personDTOs.add(createPersonDTO);
        parseNextPerson(input);
    }

    public static void updatePerson(int id, String name, String sex, Date birthDate, String[] input) throws ParseException {
        UpdatePersonDTO updatePersonDTO = new UpdatePersonDTO(id, name, sex, birthDate);
        personDTOs.add(updatePersonDTO);
        parseNextPerson(input);
    }

    public static void deletePerson(int id, String[] input) throws ParseException {
        DeletePersonDTO deletePersonDTO = new DeletePersonDTO(id);
        personDTOs.add(deletePersonDTO);
        parseNextPerson(input);
    }

    public static void indexPerson(int id, String[] input) throws ParseException {
        IndexPersonDTO indexPersonDTO = new IndexPersonDTO(id);
        personDTOs.add(indexPersonDTO);
        parseNextPerson(input);
    }

    public static Date parseBirthDate(String birthDate) throws ParseException {
        return new SimpleDateFormat("MM dd yyyy", Locale.ENGLISH).parse(birthDate);
    }

    public static boolean isBirthDate(String string) {
        Pattern pattern = Pattern.compile("\\d\\d\\s\\d\\d\\s\\d\\d\\d\\d");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public static boolean isSex(String string) {
        return string.contentEquals("m")
                || string.contentEquals("f");
    }

    public static boolean isID(String string) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    private static void parseNextPerson(String[] input) throws ParseException {
        String name;
        String sex;
        Date birthDate;
        int id;

        if(input.length <= 1) {return;}

        if(input[0].contentEquals("-c")) {
            name = input[1];
            if(isSex(input[2])) {sex = input[2];}
            else {throw new ParseException(input[2],0);}
            if(isBirthDate(input[3])) {birthDate = parseBirthDate(input[3]);}
            else {throw new ParseException(input[3],0);}

            String[] remaining = remainingArray(input, 3, input[0]);

            createPerson(name, sex, birthDate, remaining);
        }
        else if(input[0].contentEquals("-u")) {
            if(isID(input[1])) {id = Integer.parseInt(input[1]);}
            else {throw new ParseException(input[1],0);}
            name = input[2];
            if(isSex(input[3])) {sex = input[3];}
            else {throw new ParseException(input[3],0);}
            if(isBirthDate(input[4])) {birthDate = parseBirthDate(input[4]);}
            else {throw new ParseException(input[4],0);}

            String[] remaining = remainingArray(input, 4, input[0]);

            updatePerson(id, name, sex, birthDate, remaining);
        }
        else if(input[0].contentEquals("-i") || input[0].contentEquals("-d")) {
            if(isID(input[1])) {id = Integer.parseInt(input[1]);}
            else {throw new ParseException(input[1],0);}

            String[] remaining = remainingArray(input, 1, input[0]);

            if(input[0].contentEquals("-i")) { indexPerson(id, remaining);}
            else {deletePerson(id, remaining);}
        }
        else {throw new ParseException(input[0],0);}
    }

    private static String[] remainingArray(String[] current, int offset, String flag) {
        String[] remaining = new String[current.length - offset];
        remaining[0] = flag;
        if(current.length > ++offset) {
            System.arraycopy(current, offset, remaining, 1, current.length - offset);}

        return remaining;
    }
}