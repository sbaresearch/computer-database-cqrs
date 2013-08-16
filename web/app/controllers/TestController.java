package controllers;

import cqrs.commands.ProduceTestErrorCommand;
import cqrs.commands.ProduceTestSuccessCommand;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import play.api.modules.spring.Spring;
import play.mvc.Result;

public class TestController extends CommandController
{
    public static Result testSuccess()
    {
        ProduceTestSuccessCommand cmd = new ProduceTestSuccessCommand();

        try
        {
            CommandGateway gw = Spring.getBeanOfType(CommandGateway.class);
            gw.sendAndWait(cmd);
            flash("success", "Everything went fine");
        }
        catch(CommandExecutionException e)
        {
            flashError(e);
        }

        return Application.GO_HOME;
    }

    public static Result testError()
    {
        ProduceTestErrorCommand cmd = new ProduceTestErrorCommand();

        try
        {
            CommandGateway gw = Spring.getBeanOfType(CommandGateway.class);
            gw.sendAndWait(cmd);
            flash("success", "Everything went fine");
        }
        catch(CommandExecutionException e)
        {
            flashError(e);
        }

        return Application.GO_HOME;
    }
}
