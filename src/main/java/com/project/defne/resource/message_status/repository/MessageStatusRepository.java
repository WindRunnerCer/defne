package com.project.defne.resource.message_status.repository;

import com.project.defne.resource.message_status.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ceren A. @ 5/18/2019
 * While listening ${SPOT}
 */
@Repository
public interface MessageStatusRepository extends JpaRepository<MessageStatus, Integer> {
}
