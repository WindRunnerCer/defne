package com.project.defne.resource.message_request;

import com.project.defne.resource.client.Client;
import com.project.defne.resource.message_status.MESSAGE_STATUS;
import com.project.defne.resource.message_status.MessageStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by Ceren A. @ 5/18/2019
 * While listening ${SPOT}
 */
@Entity
@Data
@Table(name = "message_request",schema = "PUBLIC")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(updatable=false)
    private String senderCellphone;
    @Column(updatable=false)
    private String destinationNumber;
    @Column(updatable=false)
    private String messageContent;
    @Column(updatable=false)
    private LocalDateTime startDate;
    @Column(updatable=false)
    private LocalDateTime endDate;
    @CreatedDate
    @Column(updatable=false)
    private LocalDateTime requestDate;
    private Integer tryCount;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id",referencedColumnName="id")
    private Client client;
    @OneToMany(mappedBy = "messageRequest", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<MessageStatus> messageStatus;


    //This function filters messages which should not be try to send again.
    public boolean hasValidStatus() {
        if(this.getMessageStatus().stream().anyMatch(s->s.getStatus().equals(MESSAGE_STATUS.SEND))||
                this.getMessageStatus().stream().anyMatch(s->s.getStatus().equals(MESSAGE_STATUS.CANCELLED)))
                return false;
        if (this.getMessageStatus().stream().anyMatch(s -> s.getMessageRequest().getTryCount() == 3))
            return false;
        //TODO:client sınırını aştıysa da yollamamak lazım
        else
            return true;
    }
}