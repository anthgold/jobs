import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("jobs", request.session().attribute("jobs")); // puts the currently non-existent jobs here, so we may grab it at line 24, after a "go back" in jobs.vtl
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/jobs", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      ArrayList<Jobs> jobs = request.session().attribute("jobs"); // instantiates an ArrayList of type Jobs called jobs, which then tries to get the initially non-existent attribute jobs
      if (jobs == null) { // starts at null, so it automatically is triggered the first time
        jobs = new ArrayList<Jobs>();
        request.session().attribute("jobs", jobs);
      }

      String workPlace = request.queryParams("workPlace"); // this is the "name" from the form input id in index.vtl
      String title = request.queryParams("title");
      String duties = request.queryParams("duties");
      String time = request.queryParams("time");
      Jobs newJob = new Jobs(workPlace, title, duties, time);
      jobs.add(newJob); // adds any new instance of class Jobs to the ArrayList 

      model.put("template", "templates/jobs.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine() );

  }
}
