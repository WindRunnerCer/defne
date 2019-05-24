package com.project.defne.service.message_request.create;

import com.project.defne.resource.client.Client;
import com.project.defne.resource.message_request.MessageRequest;
import com.project.defne.resource.message_request.repository.MessageRequestRepository;
import com.project.defne.resource.message_status.MESSAGE_STATUS;
import com.project.defne.resource.message_status.MessageStatus;
import com.project.defne.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ceren A. @ 5/19/2019
 * While listening ${SPOT}
 */
@Service
public class CreateMessageRequest implements IService<CreateMessageRequestRQ,CreateMessageRequestRS> {
    @Autowired
    MessageRequestRepository messageRequestRepository;

    @Override
    public CreateMessageRequestRS execute(CreateMessageRequestRQ request) {
        Set<MessageStatus> messageStatusSet = new HashSet<>();
        MessageRequest messageRequest = MessageRequest.builder()
                .messageContent(request.getMessageContent())
                .client(Client.builder()
                        .id(request.getClientId())
                        .build())
                .senderCellphone(request.getSenderCellphone())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .destinationNumber(request.getReceiverCellPhone())
                .tryCount(0)
                .build();
        messageStatusSet.add(MessageStatus.builder()
                .status(MESSAGE_STATUS.NOT_SEND)
                .messageRequest(messageRequest)
                .client(Client.builder()
                        .id(request.getClientId())
                        .build())
                .build());
        messageRequest.setMessageStatus(messageStatusSet);
        MessageRequest messageRequestResponse = messageRequestRepository.save(messageRequest);
        return CreateMessageRequestRS.builder().messageRequestId(messageRequestResponse.getId()).build();
    }
}
