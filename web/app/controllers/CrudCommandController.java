package controllers;

import models.Page;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import play.api.modules.spring.Spring;
import play.api.mvc.Call;
import play.api.templates.Html;
import play.mvc.Result;
import repositories.CrudRepositoryHelper;
import scala.Function4;

public class CrudCommandController extends CommandController
{
    protected static <T> Result list(
        Function4<Page<T>, String, String, String, Html> view,
        Class<T> readmodelClass,
        int page, String sortBy, String order, String filter)
    {
        return ok(
            view.apply(
                CrudRepositoryHelper.page(readmodelClass, page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }

    protected static <T> Result delete(T cmd, String successMessage, Call successRoute, Call errorRoute)
    {
        try
        {
            CommandGateway gw = Spring.getBeanOfType(CommandGateway.class);
            gw.sendAndWait(cmd);
            flash("success", successMessage);
            return redirect(successRoute);
        }
        catch(CommandExecutionException e)
        {
            flash("error", e.getCause().getMessage());
            return redirect(errorRoute);
        }
    }
}
