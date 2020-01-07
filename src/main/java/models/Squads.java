package models;


import java.util.ArrayList;
import java.util.List;

public class Squads {

    private int maxsize;
    private String name;
    private String cause;
    private int id;

    private static ArrayList<Squads> allSquads = new ArrayList<>();
    private List<Hero> heroesInSquad;           //Used to create a relationship between hero and squad

    public Squads(String name,int maxsize,String cause){
        this.name = name;
        this.maxsize = maxsize;
        this.cause = cause;
        allSquads.add(this);
        this.id = allSquads.size();
        this.heroesInSquad = new ArrayList<Hero>();

    }



    public int getMaxsize(){
        return maxsize;

    }
    public String getName(){
        return name;
    }

    public String getCause(){
        return cause;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;

    }

    public static Squads findSquad(int id){
        return allSquads.get(id-1);
    }

    public void addHeroToSquad(Hero newHero){
        heroesInSquad.add(newHero);
    }       //add new hero to squad

    public List<Hero> getAllHeroes(){
        return heroesInSquad;
    }

    public static ArrayList<Squads> getAllSquads() {
        return allSquads;
    }


}