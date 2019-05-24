package com.project.defne.service.message_request.cancel_message;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Ceren A. @ 5/21/2019
 * While listening ${SPOT}
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CancelMessageRequestRQ {
    @NotNull
    @Min(1)
    private Integer messageId;
}
