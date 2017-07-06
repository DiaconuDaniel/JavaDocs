package com.java_8_training.problems.streams;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class FiltersAndMapExercise {


    public static  List<Integer> findEvenNumbers() {
        Stream<Integer> input = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> evenNumbers = new ArrayList<>();

        evenNumbers = input.filter(n -> n % 2 == 0).collect(toList());
        //TODO #1 find only the even numbers

        return evenNumbers;

    }

    public static List<String> findPersonByName(String name){
        Stream<String> input = Stream.of("Jack", "James", "Henry", "John", "Ben", "Martin", "Tori");

        List<String> personName = new ArrayList<>();

        //TODO #2 find element by given name
    personName = input.filter(p -> p.equals(name)).collect(toList());

        return personName;
    }


    public static List<Integer> filterNull(){
        Stream<Integer> input = Stream.of(0,null, 1, 2, 3, 4, 5, null, 6, 7, 8, 9, 10, null);

        List<Integer> evenNumbers = new ArrayList<>();
        //TODO #3 find all elements that are not null;

        evenNumbers = input.filter(en -> en!= null).collect(toList());

        return evenNumbers;
    }

    public static List<Person> findPersonByAgeAndName(List<Person> personList, String name, Integer age){
        List<Person> persons = new ArrayList<>();
        //TODO #4 find person by given name and age

        persons = personList.stream().filter(per -> per.getName().equals(name) && per.getAge() == age).collect(toList());

        return persons;
    }

    public static List<Integer> doubleNumbers() {
        Stream<Integer> input = Stream.of(0, 1, 2, 3, 4, 5);
        //TODO #5
        // HINT: use map
        List<Integer> doubleNumbers = new ArrayList<>();

        doubleNumbers = input.map(a -> a * 2).collect(toList());

        return doubleNumbers;
    }

    public static List<String> getOnlyNames(List<Person> personList){
        List<String> collect = new ArrayList<>();
        //TODO #6
        // HINT: use map

        collect = personList.stream().map(n -> n.getName()).collect(toList());

        return  collect;
    }

    public static List<Integer> flatteningLists() {
        Stream<List<Integer>> input = Stream.of(asList(1, 2), asList(3, 4));
        List<Integer> together = new ArrayList<>();

        //TODO #7  flatten the lists into one list

        together = input.flatMap(f -> f.stream()).collect(toList());


        return together;
    }


    public static int product(){
        Stream<Integer> input = Stream.of(1, 2, 3, 4, 5);


        int product = 1;
        //TODO #8

            product = input.reduce(1 ,(a,b) -> a*b);

        return product;
    }


    public static Optional<Person> findFirstAllMalePersons(List<Person> personList){

        Optional<Person> personOptional = Optional.empty();
        //TODO #9

        personOptional = personList.stream().filter(Person::isMale).findFirst();

        return personOptional;
    }

    public static boolean areAllPersonsBelowAge(List<Person> personList, Integer age){

        boolean overAge = false;
        //TODO #10 use allMAtch

        overAge =  personList.stream().allMatch(person -> person.getAge() < age);

        return overAge;
    }


    public static int findTheMinimumNumber(){
        Stream<Integer> input =  Stream.of(5, 2, 200, 33, 150, 0);
        //TODO #11
        // HINT: use reduce



        int min =0;
        min =  input.reduce(Integer.MIN_VALUE,(a,b) -> Integer.min(a,b));
        return min;
    }

    public static int findTheYoungestPerson(List<Person> personList){

        //TODO #12
        // HINT: use map and reduce
        int min = 0;

        min = personList.stream()
                .map(n -> n.getAge())
                .reduce(Integer.MIN_VALUE,(a,b) -> Integer.min(a,b));

        return min;
    }

    public static double findAgeAverage(List<Person> personList){
        double sum = 0;
        //TODO #13

        personList.stream()
                .map(n -> n.getAge())
                .reduce(0 ,(a,b) -> a+b);


        return  sum/personList.size();
    }

    public static List<Nationality> findNationalityByName(List<Person> personList){

        List<Nationality> persons = new ArrayList<>();

        //TODO #14
        // HINT use startsWith() for finding the names that starts with J, and distinct() to remove duplicates
        //persons = personList.stream().filter(p -> p.getName().startsWith("J"));

        persons = personList.stream()
                .filter(n -> n.getName().startsWith("J"))
                .map(r -> r.getNationality())
                .distinct()
                .collect(toList());

        return  persons;
    }


    public static Optional<Integer> findSquareDivisibleBy3(){

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);

        //TODO #15
        Optional<Integer> firstSquareDivisibleByThree = Optional.empty();

        firstSquareDivisibleByThree = someNumbers.stream()
                                                 .filter(n -> Math.sqrt(n) * Math.sqrt(n) == n && n % 3 == 0)
                                                .findFirst();



        return firstSquareDivisibleByThree;
    }

    public static int sumStringLength(){
        List<String> strings = Arrays.asList("Hello","everyone","Java8", "is" , "here");
        int sum = 0;
        //TODO #16

//        sum = strings.stream()
//                .reduce(1 ,(a,b) -> a*b);

        return sum;
    }

    public static  int sumSalary(List<Person> personList){
        int sum =0;
        //TODO #17


        return sum;
    }
    public static void main(String[] args){
        List<Nationality> nationalities = Arrays.asList(
                new Nationality("UK"),
                new Nationality("US"),
                new Nationality("France"),
                new Nationality("Romania")

        );
        List<Person> persons = Arrays.asList(
                new Person("Jack", 30, true, nationalities.get(0), 2783),
                new Person("James", 20, true, nationalities.get(0), 4000),
                new Person("Ana", 20, true, nationalities.get(1), 3900),
                new Person("Henry", 40, true, nationalities.get(2),5999),
                new Person("John", 34, true, nationalities.get(3), 1400),
                new Person("Diana", 38, true, nationalities.get(1),7899),
                new Person("Ben", 23, true, nationalities.get(3), 9000),
                new Person("Martin", 20, true, nationalities.get(2), 7677),
                new Person("Jacob", 20, true, nationalities.get(2), 5677),
                new Person("Clara", 19, true, nationalities.get(0), 4900),
                new Person("Jessica", 54, false, nationalities.get(2), 2333),
                new Person("Tori", 67, true, nationalities.get(1), 4500)
        );

        System.out.println("Exercise 1: " + findEvenNumbers());
        System.out.println("Exercise 2: " + findPersonByName("Jack"));
        System.out.println("Exercise 3: " + filterNull());
        System.out.println("Exercise 4: " +   findPersonByAgeAndName(persons,"James", 20));
        System.out.println("Exercise 5: " +   doubleNumbers());
        System.out.println("Exercise 6: " + getOnlyNames(persons));
        System.out.println("Exercise 7: " + flatteningLists());

        System.out.println("Exercise 8: " + product());
        System.out.println("Exercise 9: " + findFirstAllMalePersons(persons));

        System.out.println("Exercise 10: " + areAllPersonsBelowAge(persons, 70));
        System.out.println("Exercise 11: " + findTheMinimumNumber());
        System.out.println("Exercise 12: " + findTheYoungestPerson(persons));
        System.out.println("Exercise 13: " + findAgeAverage(persons));
        System.out.println("Exercise 14: " + findNationalityByName(persons));
        System.out.println("Exercise 15: " + findSquareDivisibleBy3());
        System.out.println("Exercise 16: " + sumStringLength());
        System.out.println("Exercise 17: " + sumSalary(persons));

    }
}
