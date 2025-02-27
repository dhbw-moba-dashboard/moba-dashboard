package de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller;

import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.CANMessage;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.TCPSocket;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.controller.models.request.SystemModel;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.payloadtypes.PayloadFactory;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.hardwareabstractionlayer.schemes.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SystemController {

    private final TCPSocket tcpSocket;

    public SystemController(TCPSocket tcpSocket) {
        this.tcpSocket = tcpSocket;
    }
    @PutMapping("/system/go")
    public ResponseEntity<String> go(@RequestBody SystemModel.SystemGo systemGo) {
        CANMessage message = new CANMessage(Priority.BEFEHLE, CommandScheme.SYSTEM_COMMAND, systemGo, false);
        try {

            tcpSocket.send(message);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(PayloadFactory.getJsonSerialString(systemGo), HttpStatus.OK);
    }
    @PutMapping("/system/stop")
    public ResponseEntity<String> stop(@RequestBody SystemModel.SystemStop systemStop) {
        CANMessage message = new CANMessage(Priority.BEFEHLE, CommandScheme.SYSTEM_COMMAND, systemStop, false);
        try {

            tcpSocket.send(message);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(PayloadFactory.getJsonSerialString(systemStop), HttpStatus.OK);
    }
}
