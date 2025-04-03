package de.dhbwkarlsruhe.modellbahn.controller;

import de.dhbwkarlsruhe.modellbahn.Models.CANMessage;
import de.dhbwkarlsruhe.modellbahn.Socket;
import de.dhbwkarlsruhe.modellbahn.Models.SystemModel;
import de.dhbwkarlsruhe.modellbahn.Models.ModelFactory;
import de.dhbwkarlsruhe.modellbahn.schemes.CommandScheme;
import de.dhbwkarlsruhe.modellbahn.schemes.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SystemController {

    private final Socket socket;

    public SystemController(Socket socket) {
        this.socket = socket;
    }
    @PutMapping("/system/go")
    public ResponseEntity<String> go(@RequestBody SystemModel.SystemGo systemGo) {
        CANMessage message = new CANMessage(Priority.BEFEHLE, CommandScheme.SYSTEM_COMMAND, systemGo, false);
        try {

            socket.send(message);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ModelFactory.getJsonSerialString(systemGo), HttpStatus.OK);
    }
    @PutMapping("/system/stop")
    public ResponseEntity<String> stop(@RequestBody SystemModel.SystemStop systemStop) {
        CANMessage message = new CANMessage(Priority.BEFEHLE, CommandScheme.SYSTEM_COMMAND, systemStop, false);
        try {

            socket.send(message);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ModelFactory.getJsonSerialString(systemStop), HttpStatus.OK);
    }
}
