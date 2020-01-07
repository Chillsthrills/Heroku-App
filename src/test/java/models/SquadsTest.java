package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquadsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void NewSquadObjectGetsCorrectlyCreated_true() throws Exception{
        Squads squad = setUpNewSquad();
        assertEquals(true,squad instanceof Squads);     //checks if object is instance of squad
    }

    @Test
    public void squad_squadInstantiatesWithName_true() throws Exception{
        Squads squad = setUpNewSquad();
        assertEquals("avengers",squad.getName());      //getName returns instance of class
    }

    @Test
    public void Squad_InstantiatewithId_1() throws Exception{
        Squads squad = setUpNewSquad();
        assertEquals(3,squad.getId());      //if get id returns squad
    }


    public Squads setUpNewSquad(){
        return new Squads("avengers",8,"freedom");
    }       //helper function to create objects
}