package controllers;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.axonframework.commandhandling.gateway.CommandGateway;
import play.api.modules.spring.Spring;
import play.api.mvc.Call;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.Result;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static play.data.Form.form;

public abstract class GenericViewsCrudCommandController extends CrudCommandController
{
    private static <T> String[] commandKeys(Class<T> cmdClass)
    {
        List<String> keys = new ArrayList<String>();

        Field[] fields = cmdClass.getDeclaredFields();
        for(Field field : fields)
        {
            if(!field.isAnnotationPresent(TargetAggregateIdentifier.class))
            {
                keys.add(field.getName());
            }
        }

        return keys.toArray(new String[] {});
    }

    protected static <T> Result create(Class<T> cmdClass, Call saveRoute)
    {
        Form<T> form = form(cmdClass);
        return ok(
            views.html.command.createForm.render(
                form,
                cmdClass,
                commandKeys(cmdClass),
                saveRoute
            )
        );
    }

    public static <T> Result edit(Class<T> cmdClass, T cmd, Call updateRoute, Call deleteRoute)
    {
        Form<T> form = form(cmdClass).fill(cmd);
        return ok(
            views.html.command.editForm.render(
                form,
                cmdClass,
                commandKeys(cmdClass),
                updateRoute,
                deleteRoute
            )
        );
    }

    protected static <T> Result delete(Class<T> cmdClass, T cmd, Call indexRoute, Call editRoute)
    {
        return delete(
            cmd,
            Messages.get("command." + cmdClass.getSimpleName() + ".success"),
            indexRoute,
            editRoute
        );
    }

    public static <T> Result save(Class<T> cmdClass, T cmd, Call indexRoute, Call saveRoute)
    {
        String[] keys = commandKeys(cmdClass);

        Form<T> form = form(cmdClass).bindFromRequest();
        if(form.hasErrors()) {
            return badRequest(views.html.command.createForm.render(form, cmdClass, keys, saveRoute));
        }

        try
        {
            CommandGateway gw = Spring.getBeanOfType(CommandGateway.class);
            gw.sendAndWait(cmd);
            flash("success", Messages.get("command."+cmdClass.getSimpleName()+".success"));
            return redirect(indexRoute);
        }
        catch(CommandExecutionException e)
        {
            flashError(e);
            return badRequest(views.html.command.createForm.render(form, cmdClass, keys, saveRoute));
        }
    }

    public static <T> Result update(Class<T> cmdClass, T cmd, Call indexRoute, Call updateRoute, Call deleteRoute)
    {
        Form<T> form = form(cmdClass).bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(views.html.command.editForm.render(
                form,
                cmdClass,
                commandKeys(cmdClass),
                updateRoute,
                deleteRoute));
        }

        try
        {
            CommandGateway gw = Spring.getBeanOfType(CommandGateway.class);
            gw.sendAndWait(cmd);
            flash("success", Messages.get("command."+cmdClass.getSimpleName()+".success"));
            return redirect(indexRoute);
        }
        catch (CommandExecutionException e)
        {
            flashError(e);
            return badRequest(views.html.command.editForm.render(
                form,
                cmdClass,
                commandKeys(cmdClass),
                updateRoute,
                deleteRoute));
        }
    }
}
