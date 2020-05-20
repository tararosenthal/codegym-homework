package com.codegym.task.task17.task1711;
import java.util.*;


/*
Object Oriented alternative
You are procedurally looping over a string to execute a command as it's parsing a string.

Identify the two main responsibilities/tasks of this program:
1. Parsing the user input string into a command and person(s)
2. Executing the desired command with the supplied person(s)

This means you should have a minimum of two classes: CommandParser and PersonRepository.

CommandParser would have some method parseCommand(String). That should return some object that can be passed
through the rest of the system. You never want to pass raw user input through your program. You want to have
some type of Controller which transforms the user input into input objects -- or rather, you want a Controller
to use a Parser to create those objects, which the Controller then passes to other objects (like the PersonRepo).

So, you've identified an extra object: the input object that the Parser outputs and the Repo accepts as input.
This is called a Data Transfer Object (DTO), a super simple, all-public properties, no methods object that models
user input. So imagine we had a CreatePersonDTO:

interface PersonDTO

class CreatePersonDTO implements PersonDTO
+ name: String
+ birthDate: Date
+ sex: String

Now, make your CommandParser.parseCommand(String) be able to transform the user input into the above, if it's
the flag is -c. Make similar classes for the other three flags. Move your parse*() commands from this class into
the Parser class and leverage them there. If it can't parse something, it throws the ParseException.

Now, the controller (this Solution class) can take the PersonDTO returned by the CommandParser and pass it to the
Repo. I would imagine having some PersonRepository.execute(PersonDTO). That method looks to see what specific
class it is (e.g. CreatePersonDTO) and then calls the appropriate command, such as:
PersonRepository.create(CreatePersonDTO)

Once you have that done, you can refactor your Parser to return an array/list of PersonDTO since they may have
passed in multiple people in the command string.
*/

public class Solution {
    public static final List<Person> allPeople = new ArrayList<>();

    static {
        allPeople.add(Person.createMale("Donald Chump", new Date()));  // id=0
        allPeople.add(Person.createMale("Larry Gates", new Date()));  // id=1
    }

    public static void main(String[] args) {
        try {
            ArrayList<PersonDTO> personDTOs = CommandParser.parseCommand(args);
            for (PersonDTO personDTO : personDTOs) {
                PersonRepository.assign(personDTO);
            }
        } catch (Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));}
        }
    }

