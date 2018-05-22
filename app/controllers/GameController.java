package controllers;

import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameController extends Controller
{
    private List<String> crewNames;
    private FormFactory formFactory;
    private int avatar;

    @Inject
    public GameController(FormFactory formFactory)
    {
        this.formFactory = formFactory;
        crewNames = new ArrayList<>();
        addCrewNames();
    }

    private void addCrewNames()
    {
        crewNames.add("John");
        crewNames.add("Jacob");
        crewNames.add("James");
        crewNames.add("Josiah");
        crewNames.add("Jonah");
        crewNames.add("Jordan");
        crewNames.add("Jebediah");
        crewNames.add("Jasper");
        crewNames.add("Joey");
        crewNames.add("Jimmy");

        Collections.shuffle(crewNames);
    }

    public Result getWelcome()
    {
        return ok(views.html.welcome.render());
    }

    public Result postStart()
    {
        crewNames.clear();
        addCrewNames();

        DynamicForm form = formFactory.form().bindFromRequest();
        if (form.get("playerName") != null)
        {
            String playerName = form.get("playerName");
            session().put("playerName", playerName);
            avatar = Integer.parseInt(form.get("avatar"));
        }
        session().put("crewCount", "10");

        if (session().get("playerName").equals("Joseph"))
        {
            return ok(views.html.succession.render(session().get("playerName"),Integer.parseInt(session().get("crewCount")),crewNames,avatar));
        }
        else
        {
            return ok(views.html.start.render(crewNames,avatar));
        }
    }

    public Result postEastFromEngland()
    {
        int crewCount = Integer.parseInt(session().get("crewCount"));
        crewCount--;
        String crewMember = crewNames.get(0);
        crewNames.remove(0);
        if (crewCount < 5)
        {
            String playerName = session().get("playerName");
            return ok(views.html.crewcountend.render(playerName,crewNames,avatar));
        }
        session().put("crewCount", "" + (crewCount));
        return ok(views.html.eastfromengland.render(crewCount,crewMember,crewNames,avatar));
    }

    public Result postNorthFromEngland()
    {
        Random rand = new Random();
        int randNum = rand.nextInt(4);
        String playerName = session().get("playerName");
        int crewCount = Integer.parseInt(session().get("crewCount"));
        if (randNum == 0)
        {
            return ok(views.html.santa.render(playerName,crewCount,crewNames,avatar));
        }
        else
        {
            return ok(views.html.northfromengland.render(playerName,crewCount,crewNames,avatar));
        }
    }

    public Result postNorthStep()
    {
        int crewCount = Integer.parseInt(session().get("crewCount"));
        return ok(views.html.northstepfromengland.render(crewCount,crewNames,avatar));
    }

    public Result postWestFromEngland()
    {
        int crewCount = Integer.parseInt(session().get("crewCount"));
        crewCount--;
        String crewMember = crewNames.get(0);
        crewNames.remove(0);session().put("crewCount", "" + (crewCount));
        if (crewCount < 5)
        {
            String playerName = session().get("playerName");
            return ok(views.html.crewcountend.render(playerName,crewNames,avatar));
        }
        return ok(views.html.westfromengland.render(crewCount,crewMember,crewNames,avatar));
    }

    public Result postEastEnd()
    {
        String playerName = session().get("playerName");
        int crewCount = Integer.parseInt(session().get("crewCount"));
        return ok(views.html.eastend.render(playerName,crewCount,crewNames,avatar));
    }

    public Result postWestEnd()
    {
        String playerName = session().get("playerName");
        int crewCount = Integer.parseInt(session().get("crewCount"));
        Random rand = new Random();
        int lead = rand.nextInt(5);
        if (lead == 0)
        {
            return ok(views.html.leadend.render(playerName,crewCount,crewNames,avatar));
        }
        else
        {
            return ok(views.html.westend.render(playerName,crewCount,crewNames,avatar));
        }
    }

    public Result postHomePort()
    {
        int crewCount = Integer.parseInt(session().get("crewCount"));
        return ok(views.html.homeport.render(crewCount,crewNames,avatar));
    }

    public Result getKittens()
    {
        return ok(views.html.kittens.render());
    }
}
