package com.example.addictionbreaker;

public class User {
    private String name, addiction;
    private int age, consumption, costOfAddiction;

    public User(){

    }
    public User(String name, String addiction, int age, int consumption, int costOfAddiction){
        this.name = name;
        this. addiction = addiction;
        this.age = age;
        this.consumption = consumption;
        this.costOfAddiction = costOfAddiction;
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

}
