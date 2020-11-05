package io.hullaert.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrintController {

    @Autowired
    public PrintController() {

    }

    @PostMapping(value = "/tickets/print", consumes = "application/json", produces = "application/json")
    public void printTickets(@RequestBody String[] ticketIds) {
        System.out.println(ticketIds[1]);
    }
}
