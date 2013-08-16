package cqrs.commandhandlers;

import cqrs.commands.ProduceTestErrorCommand;
import cqrs.commands.ProduceTestSuccessCommand;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.springframework.stereotype.Component;

@Component
public class ProduceTestsCommandHandler
{
    @CommandHandler
    public void handle(ProduceTestErrorCommand cmd) throws Exception
    {
        throw new Exception("Sample exception inside a command handler");
    }

    @CommandHandler
    public void handle(ProduceTestSuccessCommand cmd)
    {
    }
}
