package com.project.defne.controller;

import com.project.defne.service.client.create.ClientRQ;
import com.project.defne.service.client.create.ClientRS;
import com.project.defne.service.client.create.CreateClient;
import com.project.defne.service.client.delete.ClientDeleteRQ;
import com.project.defne.service.client.delete.ClientDeleteRS;
import com.project.defne.service.client.delete.DeleteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Ceren A. @ 5/19/2019
 * While listening ${SPOT}
 */
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    CreateClient createClient;

    @Autowired
    DeleteClient deleteClient;

    @PostMapping
    public ClientRS createClient(@RequestBody @Valid ClientRQ clientRQ){
        return createClient.execute(clientRQ);
    }

    @DeleteMapping("/{clientId}")
    public ClientDeleteRS deleteClient(@PathVariable Integer clientId){
        ClientDeleteRQ clientDeleteRQ = ClientDeleteRQ.builder()
                .clientId(clientId)
                .build();
        return deleteClient.execute(clientDeleteRQ);
    }
}
