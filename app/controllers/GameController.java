package controllers;

import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class GameController extends Controller
{
    private FormFactory formFactory;

    @Inject
    public GameController(FormFactory formFactory)
    {
        this.formFactory = formFactory;
    }

    public Result getWelcome()
    {
        return ok(views.html.welcome.render());
    }

    public Result postStart()
    {
        DynamicForm form = formFactory.form().bindFromRequest();
        String playerName = form.get("playerName");
        session().put("playerName", playerName);

        return ok(views.html.start.render());
    }

    public Result postEastFromEngland()
    {
        return ok(views.html.eastfromengland.render());
    }

    public Result postNorthFromEngland()
    {
        String playerName = session().get("playerName");
        return ok(views.html.northfromengland.render(playerName));
    }

    public Result postNorthStep()
    {
        return ok(views.html.northstepfromengland.render());
    }

    public Result postWestFromEngland()
    {
        return ok(views.html.westfromengland.render());
    }

    public Result postEastEnd()
    {
        String playerName = session().get("playerName");
        return ok(views.html.eastend.render(playerName));
    }

    public Result postWestEnd()
    {
        String playerName = session().get("playerName");
        return ok(views.html.westend.render(playerName));
    }

    public Result postHomePort()
    {
        return ok(views.html.homeport.render());
    }

    public Result getKittens()
    {
        return ok(views.html.kittens.render());
    }
}
