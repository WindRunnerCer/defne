package com.project.defne.service.message_request.send_message;

import lombok.*;

/**
 * Created by Ceren A. @ 5/20/2019
 * While listening ${SPOT}
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendMessageRQ {
    private String naber;
}
