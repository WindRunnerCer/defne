package com.project.defne.service.client.delete;

import lombok.*;

/**
 * Created by Ceren A. @ 5/19/2019
 * While listening ${SPOT}
 */
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDeleteRQ {
    private Integer clientId;
}
