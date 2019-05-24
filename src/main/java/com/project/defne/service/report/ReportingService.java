package com.project.defne.service.report;

import com.project.defne.resource.client.Client;
import com.project.defne.resource.message_request.MessageRequest;
import com.project.defne.resource.message_request.repository.MessageRequestRepository;
import com.project.defne.resource.message_status.MESSAGE_STATUS;
import com.project.defne.resource.message_status.repository.MessageStatusRepository;
import com.project.defne.service.report.resource.per_time.PerTimeRQ;
import com.project.defne.service.report.resource.per_time.PerTimeRS;
import com.project.defne.service.report.resource.per_time_fail_by_code.PerTimeFailByCodeRS;
import com.project.defne.service.report.resource.per_time_fail_by_code.PerTimeSuccessFailRS;
import com.project.defne.service.report.resource.per_time_fail_by_code_and_client.PerTimeFailByCodeAndClientRS;
import com.project.defne.service.report.resource.per_time_per_client.PerTimePerClientRQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ceren A. @ 5/23/2019
 * While listening ${SPOT}
 */
@Service
public class ReportingService{
    @Autowired
    MessageRequestRepository messageRequestRepository;

    @Autowired
    MessageStatusRepository messageStatusRepository;

    public PerTimeRS getPerTimeRequest(PerTimeRQ perTimeRQ){
        Optional<List<MessageRequest>> messageRequestList = messageRequestRepository.findByRequestDateBetween(
                perTimeRQ.getStartDate(),perTimeRQ.getEndDate());
        if(!messageRequestList.isPresent())
           return PerTimeRS.builder().result("No request in this interval").build();
        return PerTimeRS.builder()
                .requestCount(messageRequestList.get().stream().count())
                .result("ok")
                .build();
    }

    public PerTimeSuccessFailRS getPerTimeSuccessFail(PerTimeRQ perTimeRQ) {
        Optional<List<MessageRequest>> messageRequestList =  messageRequestRepository.findByRequestDateBetween(
                perTimeRQ.getStartDate(), perTimeRQ.getEndDate());
        if(!messageRequestList.isPresent())
            return PerTimeSuccessFailRS.builder().result("No request in this interval").build();
        PerTimeSuccessFailRS perTimeSuccessFailRS = PerTimeSuccessFailRS.builder().successCount(
                messageRequestList.get().stream().filter(m->m.getMessageStatus().equals(MESSAGE_STATUS.SEND)).count())
                .build();
        Long totalRequest = getPerTimeRequest(perTimeRQ).getRequestCount();
        perTimeSuccessFailRS.setFailCount(totalRequest-perTimeSuccessFailRS.getSuccessCount());
        perTimeSuccessFailRS.setResult("ok");
        return perTimeSuccessFailRS;
    }
    public PerTimeFailByCodeRS getPerTimeRequestFailByCode(PerTimeRQ perTimeRQ){
        final Long[] errorCountOne = {0L};
        final Long[] errorCountTwo = {0L};
        final Long[] errorCountThree = {0L};
        Optional<List<MessageRequest>> messageRequestList = messageRequestRepository.findByRequestDateBetween(
                perTimeRQ.getStartDate(), perTimeRQ.getEndDate());
        if(!messageRequestList.isPresent())
            return PerTimeFailByCodeRS.builder().result("No request in this interval").build();
        messageRequestList.get().forEach(m-> errorCountOne[0] = m.getMessageStatus().stream().filter(s->s.getStatus().equals(MESSAGE_STATUS.ERROR_1)).count());
        messageRequestList.get().forEach(m-> errorCountTwo[0] = m.getMessageStatus().stream().filter(s->s.getStatus().equals(MESSAGE_STATUS.ERROR_2)).count());
        messageRequestList.get().forEach(m-> errorCountThree[0] = m.getMessageStatus().stream().filter(s->s.getStatus().equals(MESSAGE_STATUS.ERROR_3)).count());
        PerTimeFailByCodeRS perTimeFailByCodeRS = PerTimeFailByCodeRS.builder()
                .errorOneCount(errorCountOne[0])
                .errorThreeCount(errorCountThree[0])
                .errorTwoCount(errorCountTwo[0])
                .build();
        perTimeFailByCodeRS.setResult("ok");
        return perTimeFailByCodeRS;
    }

    public PerTimeFailByCodeAndClientRS getPerTimeRequestFailByCodeAndClient(PerTimePerClientRQ perTimeRQ){
        final Long[] errorCountOne = {0L};
        final Long[] errorCountTwo = {0L};
        final Long[] errorCountThree = {0L};
        Optional<List<MessageRequest>> messageRequestList = messageRequestRepository.findByRequestDateBetweenAndClient(
                perTimeRQ.getStartDate(), perTimeRQ.getEndDate()
                ,Client.builder()
                        .id(perTimeRQ.getClientId().intValue())
                        .build() );
        if(!messageRequestList.isPresent()) {
            PerTimeFailByCodeAndClientRS perTimeFailByCodeAndClientRS =
                    PerTimeFailByCodeAndClientRS.builder().result("No request in this interval").build();
            perTimeFailByCodeAndClientRS.setClientId(perTimeRQ.getClientId().intValue());
            return perTimeFailByCodeAndClientRS;
        }
        messageRequestList.get().forEach(m-> errorCountOne[0] = m.getMessageStatus().stream().filter(s->s.getStatus().equals(MESSAGE_STATUS.ERROR_1)).count());
        messageRequestList.get().forEach(m-> errorCountTwo[0] = m.getMessageStatus().stream().filter(s->s.getStatus().equals(MESSAGE_STATUS.ERROR_2)).count());
        messageRequestList.get().forEach(m-> errorCountThree[0] = m.getMessageStatus().stream().filter(s->s.getStatus().equals(MESSAGE_STATUS.ERROR_3)).count());
        PerTimeFailByCodeAndClientRS perTimeFailByCodeAndClientRS = PerTimeFailByCodeAndClientRS.builder()
                .errorOneCount(errorCountOne[0])
                .errorThreeCount(errorCountThree[0])
                .errorTwoCount(errorCountTwo[0])
                .build();
        perTimeFailByCodeAndClientRS.setResult("ok");
        perTimeFailByCodeAndClientRS.setClientId(perTimeRQ.getClientId().intValue());
        return perTimeFailByCodeAndClientRS;
    }
}
