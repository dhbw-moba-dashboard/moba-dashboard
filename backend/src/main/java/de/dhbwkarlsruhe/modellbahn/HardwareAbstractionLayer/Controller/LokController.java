package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Controller;

import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Controller.models.request.LokModel;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.PayloadFactory;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes.CommandScheme;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LokController {
    @PutMapping("/lok/{loc_id}/speed")
    public void setLocSpeed(@PathVariable int loc_id,  @RequestBody LokModel.LokSpeed lokModel) {
        String json = lokModel.buildJson(loc_id);
        Payload p = PayloadFactory.createPayloadFromJson(json, CommandScheme.LocomotiveSpeed);
        System.out.println(p.toString());
    }
}
