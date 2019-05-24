package com.project.defne.service.client.delete;

import com.project.defne.resource.client.repository.ClientRepository;
import com.project.defne.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ceren A. @ 5/19/2019
 * While listening ${SPOT}
 */
@Service
public class DeleteClient implements IService<ClientDeleteRQ,ClientDeleteRS> {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public ClientDeleteRS execute(ClientDeleteRQ request) {
        clientRepository.deleteById(request.getClientId());
        return ClientDeleteRS.builder()
                .result("ok")
                .build();
    }
}
