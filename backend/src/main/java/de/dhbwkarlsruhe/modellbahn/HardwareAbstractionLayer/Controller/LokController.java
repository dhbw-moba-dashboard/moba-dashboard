package de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Controller;

import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Controller.models.request.LocModel;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.Payload;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.PayloadTypes.PayloadFactory;
import de.dhbwkarlsruhe.modellbahn.HardwareAbstractionLayer.Schemes.CommandScheme;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LokController {
	@PutMapping("/loc/{loc_id}/speed")
	public void setLocSpeed(@PathVariable int loc_id, @RequestBody LocModel.LocSpeed lokModel) {
        String json = lokModel.buildJson(loc_id);
        Payload p = PayloadFactory.createPayloadFromJson(json, CommandScheme.LocomotiveSpeed);
        System.out.println(p.toString());
	}

	@PutMapping("/loc/{loc_id}/direction")
	public void setLocDirection(@PathVariable int loc_id, @RequestBody LocModel.LocDirection lokModel) {
		String json = lokModel.buildJson(loc_id);
		System.out.println(json);
		Payload p = PayloadFactory.createPayloadFromJson(json, CommandScheme.LocomotiveDirection);
		System.out.println(p.toString());
    }
}
