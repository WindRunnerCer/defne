package com.project.defne.service.client.create;

import com.project.defne.resource.client.Client;
import com.project.defne.resource.client.repository.ClientRepository;
import com.project.defne.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ceren A. @ 5/19/2019
 * While listening ${SPOT}
 */
@Service
public class CreateClient implements IService<ClientRQ,ClientRS> {
    @Autowired
    ClientRepository clientRepository;
    /*@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

    @Override
    public ClientRS execute(ClientRQ request) {
        Client client = Client.builder()
                .name(request.getName())
                .quota(request.getQuota())
                //.password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build();
        Client clientResponse = clientRepository.save(client);
        return ClientRS.builder()
                .clientId(clientResponse.getId())
                .name(clientResponse.getName())
                .quota(clientResponse.getQuota())
                .build();
    }
}
