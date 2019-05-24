package com.project.defne.service.message_request.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Ceren A. @ 5/19/2019
 * While listening ${SPOT}
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMessageRequestRQ {
    @NotBlank(message = "Should not be empty")
    @NotEmpty(message = "Should not be empty")
    @NotNull(message = "Should not be empty")
    private String senderCellphone;
    @NotBlank(message = "Should not be empty")
    @NotEmpty(message = "Should not be empty")
    @NotNull(message = "Should not be empty")
    @Length(max = 1024,message = "Message should be shorter")
    private String messageContent;
    @NotBlank(message = "Should not be empty")
    @NotEmpty(message = "Should not be empty")
    @NotNull(message = "Should not be empty")
    private String receiverCellPhone;
    @Future(message = "Should be a next date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @Future(message = "Should be a next date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    private Integer clientId;
}
