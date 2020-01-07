package models;
import java.util.ArrayList;

public class Hero {
    private String Heroname;
    private int Heroage;                //class properties
    private String Heropower;
    private String Heroweakness;
    private int Heroid;
    private static ArrayList<Hero> allHeroes = new ArrayList<>();


    public Hero(String name,String power,String weakness,int age){
        this.Heroname = name;
        this.Heroage = age;             //constructor to create new class objects
        this.Heropower = power;
        this.Heroweakness = weakness;
        allHeroes.add(this);
    }

    public String getName(){
        return Heroname;
    }

    public int age(){
        return Heroage;
    }

    public String getPower(){
        return Heropower;
    }          // getters and setters to access private variables

    public String getWeakness(){
        return Heroweakness;
    }

    public int getId() {
        return Heroid;
    }

    public void setId(int id) {
        this.Heroid = id;
    }           //id Setter

    public static ArrayList<Hero> getAllHeroes() {
        return allHeroes;
    }


}



