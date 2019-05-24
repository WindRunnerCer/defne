package com.project.defne.service.report.resource.per_time;

import lombok.*;

/**
 * Created by Ceren A. @ 5/23/2019
 * While listening ${SPOT}
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerTimeRS {
    private String result;
    private Long requestCount;
}
