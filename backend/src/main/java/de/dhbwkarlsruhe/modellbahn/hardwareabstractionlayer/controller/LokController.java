package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller;

import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller.models.request.LocModel;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.Payload;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.PayloadFactory;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.CommandScheme;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LokController {
	@PutMapping("/loc/{locID}/speed")
	public void setLocSpeed(@PathVariable int locID, @RequestBody LocModel.LocSpeed lokModel) {
		String json = lokModel.buildJson(locID);
		Payload p = PayloadFactory.createPayloadFromJson(json, CommandScheme.LOCOMOTIVE_SPEED);
        System.out.println(p.toString());
	}

	@PutMapping("/loc/{locID}/direction")
	public void setLocDirection(@PathVariable int locID, @RequestBody LocModel.LocDirection lokModel) {
		String json = lokModel.buildJson(locID);
		Payload p = PayloadFactory.createPayloadFromJson(json, CommandScheme.LOCOMOTIVE_DIRECTION);
		System.out.println(p.toString());
    }
}
