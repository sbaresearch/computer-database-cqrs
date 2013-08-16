package controllers;

import org.axonframework.commandhandling.CommandExecutionException;
import play.data.Form;
import play.mvc.Controller;

import static play.data.Form.form;

abstract class CommandController extends Controller
{
    protected static <T> T getRequestCommand(Class<T> cmdClass)
    {
        // TODO: Completely replace this by delegates
        // http://stackoverflow.com/questions/1340231/equivalent-of-c-sharp-anonymous-delegates-in-java

        Form<T> form = form(cmdClass).bindFromRequest();
        play.libs.F.Option<T> cmd = form.value();
        T newInstance = null;
        try {
            newInstance = cmdClass.newInstance();
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
        return cmd.getOrElse(newInstance);
    }

    protected static void flashError(CommandExecutionException e)
    {
        if(play.Play.isProd() && false)
        {
            flash("error", e.getCause().getMessage());
            // TODO: Logging
        }
        else
        {
            String position = e.getCause().getStackTrace()[0].toString();
            flash("error", e.getCause().getMessage() + " at " + position);
        }
    }
}
