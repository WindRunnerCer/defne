package com.project.defne.controller;

import com.project.defne.service.message_request.cancel_message.CancelMessageRequest;
import com.project.defne.service.message_request.cancel_message.CancelMessageRequestRQ;
import com.project.defne.service.message_request.cancel_message.CancelMessageRequestRS;
import com.project.defne.service.message_request.create.CreateMessageRequest;
import com.project.defne.service.message_request.create.CreateMessageRequestRQ;
import com.project.defne.service.message_request.create.CreateMessageRequestRS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Ceren A. @ 5/19/2019
 * While listening ${SPOT}
 */
@RestController
@RequestMapping("/messageRequest")
public class MessageRequestController {
    @Autowired
    CreateMessageRequest createMessageRequest;

    @Autowired
    CancelMessageRequest cancelMessageRequest;

    @PostMapping
    public CreateMessageRequestRS createMessage(@RequestBody @Valid CreateMessageRequestRQ createMessageRequestRQ){
        return createMessageRequest.execute(createMessageRequestRQ);
    }

    @DeleteMapping("/{messageId}")
    public CancelMessageRequestRS cancelMessage(@PathVariable Integer messageId){
        return cancelMessageRequest.execute(CancelMessageRequestRQ.builder().messageId(messageId).build());
    }

}
