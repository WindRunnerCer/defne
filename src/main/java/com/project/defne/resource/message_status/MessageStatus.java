package com.project.defne.resource.message_status;

import com.project.defne.resource.client.Client;
import com.project.defne.resource.message_request.MessageRequest;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

/**
 * Created by Ceren A. @ 5/18/2019
 * While listening ${SPOT}
 */
@Entity
@Table(name = "message_status",schema = "PUBLIC")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private MESSAGE_STATUS status;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id",referencedColumnName="id")
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "message_request_id", referencedColumnName="id")
    private MessageRequest messageRequest;
}
