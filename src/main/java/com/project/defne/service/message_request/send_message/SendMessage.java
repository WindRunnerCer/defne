package com.project.defne.service.message_request.send_message;

import com.project.defne.center.MessageServiceCenter;
import com.project.defne.resource.client.Client;
import com.project.defne.resource.message_request.MessageRequest;
import com.project.defne.resource.message_request.repository.MessageRequestRepository;
import com.project.defne.resource.message_status.MESSAGE_STATUS;
import com.project.defne.resource.message_status.MessageStatus;
import com.project.defne.resource.message_status.repository.MessageStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Ceren A. @ 5/20/2019
 * While listening ${SPOT}
 */
@Service
@ConfigurationProperties(prefix = "message.send")
public class SendMessage {
    @Autowired
    MessageRequestRepository messageRequestRepository;

    @Autowired
    MessageServiceCenter messageServiceCenter;

    @Autowired
    MessageStatusRepository messageStatusRepository;

    @Transactional
    @Scheduled(cron = "${message.send.cron}")
    public SendMessageRS execute() {
        final int[] result = {0};
        final MESSAGE_STATUS[] message_status = new MESSAGE_STATUS[1];
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = localDateTime.truncatedTo(ChronoUnit.MINUTES);
        Optional<List<MessageRequest>> messageRequestsInThisMinute =
                messageRequestRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(localDateTime
                        ,localDateTime.plus(Duration.of(1, ChronoUnit.MINUTES)));
        if(!messageRequestsInThisMinute.isPresent())
            return SendMessageRS.builder().status("Nothing to send").build();

         List<MessageRequest> validMessageRequests = messageRequestsInThisMinute.get().stream()
                 .filter(MessageRequest::hasValidStatus)
                 .collect(Collectors.toList());

        System.out.println(validMessageRequests.toString());
        validMessageRequests.forEach(
                m-> {
                    result[0] = messageServiceCenter.submitMessage(m.getSenderCellphone()
                            , m.getDestinationNumber(), m.getMessageContent());
                    switch (result[0]) {
                        case 0:
                            message_status[0] = MESSAGE_STATUS.SEND;
                            break;
                        case 1:
                            message_status[0] = MESSAGE_STATUS.ERROR_1;
                            break;
                        case 2:
                            message_status[0] = MESSAGE_STATUS.ERROR_2;
                            break;
                        case 3:
                            message_status[0] = MESSAGE_STATUS.ERROR_3;
                    }
                    messageStatusRepository.save(MessageStatus.builder()
                            .messageRequest(m)
                            .client(Client.builder()
                                    .id(m.getClient().getId())
                                    .build())
                            .status(message_status[0])
                            .build());
                    messageRequestRepository.save(MessageRequest.builder()
                            .client(Client.builder()
                                    .id(m.getClient().getId())
                                    .build())
                            .tryCount(m.getTryCount()+1)
                            .id(m.getId())
                            .build()
                    );
                }
        );
        return SendMessageRS.builder().status("ok").build();
    }
}
