package com.project.defne.resource.message_request.repository;

import com.project.defne.resource.client.Client;
import com.project.defne.resource.message_request.MessageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ceren A. @ 5/18/2019
 * While listening ${SPOT}
 */
@Repository
public interface MessageRequestRepository extends JpaRepository<MessageRequest, Integer> {
    Optional<List<MessageRequest>> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual
            (LocalDateTime startDate, LocalDateTime endDate);

    Optional<List<MessageRequest>> findByRequestDateBetween(LocalDateTime startDate,LocalDateTime endDate);
    Optional<List<MessageRequest>> findByRequestDateBetweenAndClient
            (LocalDateTime startDate, LocalDateTime endDate, Client client);
}
