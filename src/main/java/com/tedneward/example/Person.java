package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private boolean propertyChangeFired = false;

  private static int count = 0;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    count++;
    ssn = "";
  }

  public int getAge() {
    return age;
  }
  
  public String getName() {
    return name;
  }
  
  public double getSalary() {
    return salary;
  }
  
  public String getSSN() {
    return ssn;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;

    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }

  public void setAge(int age) {
    if (age < 0) {
        throw new IllegalArgumentException("age must be positive");
    }

    int old = age;
    this.age = age;

    this.pcs.firePropertyChange("age", old, age);
    propertyChangeFired = true;
  }

  public void setName(String name) {
    if (name == null) {
        throw new IllegalArgumentException("Please pass a valid name");
    }

    String old = name;
    this.name = name;

    this.pcs.firePropertyChange("name", old, name);
    propertyChangeFired = true;
  }

  public void setSalary(double money) {
    double old = salary;
    this.salary = money;

    this.pcs.firePropertyChange("salary", old, money);
    propertyChangeFired = true;
  }

  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  public boolean equals(Object other) {
    if (other instanceof Person) {
        Person p = (Person) other;
        return  (this.name.equals(p.name) && this.age == p.age);
    } else { // not a Person object
        return false;
    } 
  }

  public int compareTo(Person p) {
    //return ((-1.0) * (this.getSalary() - p.getSalary())); 
    int result = (int) (this.getSalary() - p.getSalary());
    result = -result;
    return result;
  }
/*
  public boolean equals(Person other) {
    return (this.name.equals(p.name) && this.age == p.age);
  }
  */

  // format: "[Person name:Fird Birfle age:20 salary:195750.22]"
  public String toString() {
    return "[Person name:" + getName() + " age:" + getAge() + " salary:" + getSalary() + "]";
  }

  public static int count() {
    return count;
  }

  public static ArrayList<Person> getNewardFamily() {
    ArrayList<Person> list = new ArrayList<Person> ();
    Person p1 = new Person("Ted", 41, 250000);
    Person p2 = new Person("Charlotte", 43, 150000);
    Person p3 = new Person("Michael", 22, 10000);
    Person p4 = new Person("Matthew", 15, 0);
    list.add(p1);
    list.add(p2);
    list.add(p3);
    list.add(p4);
    return list;
  }

  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
      return p1.getAge() - p2.getAge();
    }
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
