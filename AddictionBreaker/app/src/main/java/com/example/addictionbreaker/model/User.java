package com.example.addictionbreaker.model;

public class User {
    private String name, addiction;
    private int age, consumption, costOfAddiction, day, month, year;

    public User(){

    }
    public User(String name, String addiction, int age, int consumption, int costOfAddiction){
        this.name = name;
        this.addiction = addiction;
        this.age = age;
        this.consumption = consumption;
        this.costOfAddiction = costOfAddiction;
    }
    public User(String name, String addiction, int age){
        this.name = name;
        this. addiction = addiction;
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddiction(){
        return this.addiction;
    }

    public void setAddiction(String addiction){
        this.addiction = addiction;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getConsumption(){
        return this.consumption;
    }

    public void setConsumption(int consumption){
        this.consumption = consumption;
    }

    public int getCostOfAddiction(){
        return this.costOfAddiction;
    }

    public void setCostOfAddiction(int costOfAddiction){
        this.costOfAddiction = costOfAddiction;
    }


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void getTimePassed(){


    }
}
