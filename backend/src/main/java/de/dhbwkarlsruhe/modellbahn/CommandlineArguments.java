package de.dhbwkarlsruhe.modellbahn;

import lombok.Getter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CommandlineArguments {
    private final String ipAddress;
    public CommandlineArguments(ApplicationArguments arguments){
        boolean ip= arguments.containsOption("ip");
        if (ip){
            this.ipAddress=arguments.getNonOptionArgs().get(0);
            System.out.println("IP-Adresse: "+arguments.getNonOptionArgs().get(0));
        }
        else{
            this.ipAddress="0.0.0.0";
            System.out.println("Keine IP-Adresse angegeben");
        }
    }
}
