package com.project.defne.service.report.resource.per_time_fail_by_code_and_client;

import lombok.*;

/**
 * Created by Ceren A. @ 5/23/2019
 * While listening ${SPOT}
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerTimeFailByCodeAndClientRS {
    private Long errorOneCount;
    private Long errorTwoCount;
    private Long errorThreeCount;
    private Integer clientId;
    private String result;
}
