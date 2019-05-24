package com.project.defne.service.report.resource.per_time_fail_by_code;

import lombok.*;

/**
 * Created by Ceren A. @ 5/23/2019
 * While listening ${SPOT}
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerTimeFailByCodeRS {
    private Long errorOneCount;
    private Long errorTwoCount;
    private Long errorThreeCount;
    private String result;
}
