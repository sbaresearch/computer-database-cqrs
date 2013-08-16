package controllers;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import play.api.modules.spring.Spring;
import play.api.mvc.Call;
import play.api.templates.Html;
import play.data.Form;
import play.mvc.Result;
import scala.Function1;
import scala.Function2;

import java.util.UUID;

import static play.data.Form.form;

public class SpecificViewsCrudCommandController extends CrudCommandController
{
    protected static <T> Result create(
        Function1<Form<T>, Html> view,
        Class<T> cmdClass)
    {
        Form<T> form = form(cmdClass);
        return ok(
            view.apply(form)
        );
    }

    protected static <T> Result save(
        Function1<Form<T>, Html> view,
        Class<T> cmdClass,
        T cmd,
        String successMessage,
        Call successRoute)
    {
        Form<T> form = form(cmdClass).bindFromRequest();
        if(form.hasErrors()) {
            return badRequest(view.apply(form));
        }

        try
        {
            CommandGateway gw = Spring.getBeanOfType(CommandGateway.class);
            gw.sendAndWait(cmd);
            flash("success", successMessage);
            return redirect(successRoute);
        }
        catch(CommandExecutionException e)
        {
            flashError(e);
            return badRequest(view.apply(form));
        }
    }

    protected static <T> Result update(
        Function2<UUID, Form<T>, Html> view,
        Class<T> cmdClass,
        T cmd,
        UUID id,
        String successMessage,
        Call successRoute)
    {
        Form<T> form = form(cmdClass).bindFromRequest();
        if(form.hasErrors()) {
            return badRequest(view.apply(id, form));
        }

        try
        {
            CommandGateway gw = Spring.getBeanOfType(CommandGateway.class);
            gw.sendAndWait(cmd);
            flash("success", successMessage);
            return redirect(successRoute);
        }
        catch(CommandExecutionException e)
        {
            flashError(e);
            return badRequest(view.apply(id, form));
        }
    }

    protected static <T> Result edit(
        Function2<UUID, Form<T>, Html> view,
        Class<T> cmdClass,
        T cmd,
        UUID id)
    {
        Form<T> form = form(cmdClass).fill(cmd);
        return ok(
                view.apply(id, form)
        );
    }
}
