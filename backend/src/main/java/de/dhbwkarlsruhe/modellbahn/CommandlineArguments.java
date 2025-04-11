package de.dhbwkarlsruhe.modellbahn;

import lombok.Getter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CommandlineArguments
{
    private final String ipAddress;

    public CommandlineArguments(ApplicationArguments arguments)
    {
        boolean ip = arguments.containsOption("ip");
        if (ip)
        {
            this.ipAddress = arguments.getNonOptionArgs().getFirst();
        }
        else
        {
            this.ipAddress = "0.0.0.0";
        }
    }
}
