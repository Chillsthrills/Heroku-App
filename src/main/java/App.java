import models.Hero;
import models.Squads;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args){     //main method for java to run
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/",(request, response) -> {           //Main route to display squads and heroes
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Squads> allSquads = Squads.getAllSquads();    //get allSquads and heroes
            ArrayList<Hero> allHeroes = Hero.getAllHeroes();
            model.put("allSquads",allSquads);       //add squads and heroes to model
            model.put("allHeroes",allHeroes); //k is key used in html/hbs
            return new ModelAndView(model,"index.hbs");     //return model that contains data to show on template
        }, new HandlebarsTemplateEngine());     //render the template

        post("/postNewSquad",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();      //create model to add data
            int maxSize = Integer.parseInt(request.queryParams("maxsize")); //get data from form by html name property
            String name = request.queryParams("name");
            String cause = request.queryParams("cause");
            Squads newSquad = new Squads(name,maxSize,cause);  //create new instance of a class to allow data to be added
            model.put("maxsize",maxSize);
            model.put("name",name);     //add data to model hashmap with key and variable that grabs data from html
            model.put("cause",cause);
            return new ModelAndView(model,"success_squad.hbs");
        }, new HandlebarsTemplateEngine());

        get("/showSquadForHeroes/:id",(request, response) -> {      //get hero by id
            Map<String,Object> model = new HashMap<>();     //create a model to add data
            Squads oneSquad = Squads.findSquad(Integer.parseInt(request.params(":id"))); //find squad by id
            List<Hero> squadHeroes = oneSquad.getAllHeroes(); //get all heroes related to one squad
            model.put("oneSquad",oneSquad);     //add squad and heroes to model hashmap
            model.put("oneHero",squadHeroes);
            return new ModelAndView(model,"heroes.hbs");       //render model and template
        },new HandlebarsTemplateEngine());

        post("/postNewHero",(request, response) -> {        //post new hero
            Map<String,Object> model = new HashMap<String, Object>();       //create hashmap to add data
            String name = request.queryParams("name");      //request data from html
            int age = Integer.parseInt(request.queryParams("age"));
            String power = request.queryParams("power");
            String weakness = request.queryParams("weakness");
            String Id = request.queryParams("squadTeam");   //find id of each squad in html drop-down
            Squads newSquadTeam = Squads.findSquad(Integer.parseInt(Id));   //get each id from drop-down
            Hero newHero = new Hero(name,power,weakness,age); //set data into new class instance
            newSquadTeam.addHeroToSquad(newHero);       //add class instance data to newSquadteam
            return new ModelAndView(model,"added_heroes.hbs");

        },new HandlebarsTemplateEngine());



    }

}
