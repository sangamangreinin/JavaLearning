package com.inin.domain.model;

import java.io.Serializable;

/**
 * Created by virendradubey on 28/3/16.
 */
public class Person implements Serializable{

    /**
     * gender constants
     */
    public static final String MALE = "male";
    public static final String FEMALE = "male";
    private static final long serialVersionUID = 1;
    /**
     * name of the person
     */
    private String name;
    /**
     * age of the person
     */
    private int age;
    /**
     * gender of the person
     */
    private char gender;

    protected Person(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (!name.equals(person.name)) return false;
        return gender == person.gender;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        result = 31 * result + String.valueOf(gender).hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
