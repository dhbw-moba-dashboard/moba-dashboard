package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Controller.models.request;

public class LokModel {
    public record LokSpeed(int Speed){
        public String buildJson(int LocID){
            return String.format(
                    "{\"LocID\":%d," +
                            "\"Speed\":%d}",
                    LocID,Speed
            );
        }
    }
}
