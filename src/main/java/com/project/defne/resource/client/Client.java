package com.project.defne.resource.client;

import com.project.defne.resource.message_request.MessageRequest;
import com.project.defne.resource.message_status.MessageStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Ceren A. @ 5/18/2019
 * While listening ${SPOT}
 */
@Entity
@Table(name = "client", schema = "PUBLIC")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer quota;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<MessageStatus> messageStatus;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<MessageRequest> messageRequest;
    //private String password;
}

