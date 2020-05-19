package com.codegym.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* I realized that the way I did this does not allow me to return multiple objects using the parseCommand method.
Also my brain is fried, so I'm gonna get back to this tommorow...

Actually, I fixed something stupid, but my brain is still fried, hahaha
*/

public class CommandParser {

    public static PersonDTO parseCommand(PersonDTO personDTO, String[] input) throws ParseException {
        if (personDTO == null) { parseNextPerson(input);}
        return personDTO;
    }

    public static void createPerson(String name, String sex, Date birthDate, String[] input) throws ParseException {
        CreatePersonDTO createPersonDTO = new CreatePersonDTO(name, sex, birthDate);
        parseCommand(createPersonDTO, input);
    }

    public static void updatePerson(int id, String name, String sex, Date birthDate, String[] input) throws ParseException {
        UpdatePersonDTO updatePersonDTO = new UpdatePersonDTO(id, name, sex, birthDate);
        parseCommand(updatePersonDTO, input);
    }

    public static void deletePerson(int id, String[] input) throws ParseException {
        DeletePersonDTO deletePersonDTO = new DeletePersonDTO(id);
        parseCommand(deletePersonDTO, input);
    }

    public static void indexPerson(int id, String[] input) throws ParseException {
        IndexPersonDTO indexPersonDTO = new IndexPersonDTO(id);
        parseCommand(indexPersonDTO, input);
    }

    public static Date parseBirthDate(String birthDate) throws ParseException {
        return new SimpleDateFormat("MM dd yyyy", Locale.ENGLISH).parse(birthDate);
    }

    public static boolean isBirthDate(String string) {
        Pattern pattern = Pattern.compile("\\d+\\s\\d+\\s\\d+");
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

            String[] remaining = new String[input.length - 3];
            remaining[0] = "-c";
            if(input.length > 4) {
            System.arraycopy(input, 4, remaining, 1, input.length - 3);}
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

            String[] remaining = new String[input.length - 4];
            remaining[0] = "-u";
            if(input.length > 5) {
                System.arraycopy(input, 5, remaining, 1, input.length - 4);}
            updatePerson(id, name, sex, birthDate, remaining);
        }
        else if(input[0].contentEquals("-i") || input[0].contentEquals("-d")) {
            if(isID(input[1])) {id = Integer.parseInt(input[1]);}
            else {throw new ParseException(input[1],0);}

            String[] remaining = new String[input.length - 1];
            remaining[0] = "-i";
            if(input.length > 2) {
                System.arraycopy(input, 2, remaining, 1, input.length - 1);}
            if(input[0].contentEquals("-i")) { indexPerson(id, remaining);}
            else {deletePerson(id, remaining);}
        }
        else {throw new ParseException(input[0],0);}
    }
}
