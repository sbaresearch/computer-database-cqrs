package controllers;

import cqrs.commands.CreateComputerCommand;
import cqrs.commands.DeleteComputerCommand;
import cqrs.commands.UpdateComputerCommand;
import cqrs.identifiers.CompanyId;
import cqrs.identifiers.ComputerId;
import cqrs.readmodels.ComputerDetails;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Result;

import java.util.UUID;

public class ComputerController extends SpecificViewsCrudCommandController
{
    @Transactional(readOnly=true)
    public static Result list(int page, String sortBy, String order, String filter) {
        return list(
            views.html.computer.list.f(),
            ComputerDetails.class,
            page, sortBy, order, filter);
    }

    @Transactional(readOnly=true)
    public static Result edit(UUID id) {
        ComputerDetails computer = JPA.em().find(ComputerDetails.class, id);

        UpdateComputerCommand cmd = new UpdateComputerCommand();
        cmd.setComputerId(new ComputerId(computer.id));
        cmd.setName(computer.name);
        cmd.setIntroduced(computer.introduced);
        cmd.setDiscontinued(computer.discontinued);
        cmd.setCompanyId(new CompanyId(computer.companyId));

        return edit(
            views.html.computer.editForm.f(),
            UpdateComputerCommand.class,
            cmd,
            id
        );
    }

    @Transactional
    public static Result update(UUID id) {
        UpdateComputerCommand cmd = getRequestCommand(UpdateComputerCommand.class);
        cmd.setComputerId(new ComputerId(id));

        return update(
            views.html.computer.editForm.f(),
            UpdateComputerCommand.class,
            cmd,
            id,
            "Computer " + cmd.getName() + " has been updated",
            routes.ComputerController.list(0, "name", "asc", "")
        );
    }

    @Transactional(readOnly=true)
    public static Result create() {
        return create(
            views.html.computer.createForm.f(),
            CreateComputerCommand.class
        );
    }

    @Transactional
    public static Result save() {
        CreateComputerCommand cmd = getRequestCommand(CreateComputerCommand.class);
        cmd.setComputerId(new ComputerId());

        return save(
            views.html.computer.createForm.f(),
            CreateComputerCommand.class,
            cmd,
            "Computer " + cmd.getName() + " has been created",
            routes.ComputerController.list(0, "name", "asc", "")
    );
    }

    @Transactional
    public static Result delete(UUID id) {
        DeleteComputerCommand cmd = new DeleteComputerCommand(new ComputerId(id));

        return delete(
            cmd,
            "Computer has been deleted",
            routes.ComputerController.list(0, "name", "asc", ""),
            routes.ComputerController.edit(id)
        );
    }
}
