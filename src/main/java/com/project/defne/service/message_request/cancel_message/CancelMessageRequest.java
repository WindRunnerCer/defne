package com.project.defne.service.message_request.cancel_message;

import com.project.defne.resource.client.Client;
import com.project.defne.resource.message_request.MessageRequest;
import com.project.defne.resource.message_request.repository.MessageRequestRepository;
import com.project.defne.resource.message_status.MESSAGE_STATUS;
import com.project.defne.resource.message_status.MessageStatus;
import com.project.defne.resource.message_status.repository.MessageStatusRepository;
import com.project.defne.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Ceren A. @ 5/21/2019
 * While listening ${SPOT}
 */
@Service
public class CancelMessageRequest implements IService<CancelMessageRequestRQ,CancelMessageRequestRS> {
    @Autowired
    MessageRequestRepository messageRequestRepository;

    @Autowired
    MessageStatusRepository messageStatusRepository;

    @Override
    public CancelMessageRequestRS execute(CancelMessageRequestRQ request) {
        Optional<MessageRequest> messageRequest = messageRequestRepository.findById(request.getMessageId());
        if (!messageRequest.isPresent())
            return CancelMessageRequestRS.builder().result("Message does not exist").build();
        if (messageRequest.get().getEndDate().isAfter(LocalDateTime.now())) {
           if(messageRequest.get().getMessageStatus().stream().anyMatch(ms -> ms.getStatus().equals(MESSAGE_STATUS.CANCELLED)))
               return CancelMessageRequestRS.builder().result("Already Cancelled").build();
            if(messageRequest.get().getMessageStatus().stream().anyMatch(ms -> ms.getStatus().equals(MESSAGE_STATUS.SEND)))
                return CancelMessageRequestRS.builder().result("Already Send").build();
           MessageStatus cancelledMessage =  MessageStatus.builder()
                    .status(MESSAGE_STATUS.CANCELLED)
                    .messageRequest(messageRequest.get())
                    .client(Client.builder()
                            .id(1)
                            .build())
                    .build();
            messageStatusRepository.save(cancelledMessage);
            }
        return CancelMessageRequestRS.builder().result("ok").build();
    }
}
