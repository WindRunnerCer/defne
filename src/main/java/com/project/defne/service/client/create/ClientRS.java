package com.project.defne.service.client.create;

import lombok.*;

/**
 * Created by Ceren A. @ 5/19/2019
 * While listening ${SPOT}
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRS {
    private Integer clientId;
    private String name;
    private Integer quota;
}
