package controllers;

import cqrs.commands.CreateCompanyCommand;
import cqrs.commands.DeleteCompanyCommand;
import cqrs.commands.UpdateCompanyCommand;
import cqrs.identifiers.CompanyId;
import cqrs.readmodels.CompanyDetails;
import play.api.mvc.Call;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Result;

import java.util.UUID;

public class CompanyController extends GenericViewsCrudCommandController
{
    public static Call getIndexRoute()
    {
        return routes.CompanyController.list(0, "name", "asc", "");
    }

    public static Call getEditRoute(UUID id)
    {
        return routes.CompanyController.edit(id);
    }

    public static Call getSaveRoute()
    {
        return routes.CompanyController.save();
    }

    public static Call getUpdateRoute(UUID id)
    {
        return routes.CompanyController.update(id);
    }

    public static Call getDeleteRoute(UUID id)
    {
        return routes.CompanyController.delete(id);
    }

    // ----------------------------------------------------------------------------------------------------------------

    @Transactional(readOnly=true)
    public static Result list(int page, String sortBy, String order, String filter) {
        return list(
            views.html.company.list.f(),
            CompanyDetails.class,
            page, sortBy, order, filter);
    }

    @Transactional(readOnly=true)
    public static Result edit(UUID id) {
        CompanyDetails company = JPA.em().find(CompanyDetails.class, id);

        UpdateCompanyCommand cmd = new UpdateCompanyCommand();
        cmd.setCompanyId(new CompanyId(company.id));
        cmd.setName(company.name);

        return edit(
            UpdateCompanyCommand.class,
            cmd,
            getUpdateRoute(id),
            getDeleteRoute(id)
        );
    }

    @Transactional
    public static Result update(UUID id) {
        UpdateCompanyCommand cmd = getRequestCommand(UpdateCompanyCommand.class);
        cmd.setCompanyId(new CompanyId(id));

        return update(
            UpdateCompanyCommand.class,
            cmd,
            getIndexRoute(),
            getUpdateRoute(id),
            getDeleteRoute(id)
        );
    }

    @Transactional(readOnly=true)
    public static Result create() {
        return create(
            CreateCompanyCommand.class,
            getSaveRoute()
        );
    }

    @Transactional
    public static Result save() {
        CreateCompanyCommand cmd = getRequestCommand(CreateCompanyCommand.class);
        cmd.setCompanyId(new CompanyId());

        return save(
            CreateCompanyCommand.class,
            cmd,
            getIndexRoute(),
            getSaveRoute()
        );
    }

    @Transactional
    public static Result delete(UUID id) {
        DeleteCompanyCommand cmd = new DeleteCompanyCommand(new CompanyId(id));

        return delete(
            DeleteCompanyCommand.class,
            cmd,
            getIndexRoute(),
            getEditRoute(id)
        );
    }
}
