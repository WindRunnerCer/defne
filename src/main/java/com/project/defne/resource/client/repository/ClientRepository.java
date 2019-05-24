package com.project.defne.resource.client.repository;

import com.project.defne.resource.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ceren A. @ 5/18/2019
 * While listening ${SPOT}
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
