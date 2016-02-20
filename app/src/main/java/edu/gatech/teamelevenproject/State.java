package edu.gatech.teamelevenproject;

import java.io.Serializable;

/**
 * Created by robertwaters on 2/13/16.
 */
public class State implements Serializable {
    private String name;
    private String a2Code;
    private String a3Code;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getA2Code() {
        return a2Code;
    }

    public void setA2Code(String a2Code) {
        this.a2Code = a2Code;
    }

    public String getA3Code() {
        return a3Code;
    }

    public void setA3Code(String a3Code) {
        this.a3Code = a3Code;
    }

    public String toString() {
        return "Name: " + name + " A2 Code: " + a2Code + " A3 Code: " + a3Code;
    }

}
