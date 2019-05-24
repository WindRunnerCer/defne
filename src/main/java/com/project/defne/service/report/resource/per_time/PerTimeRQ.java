package com.project.defne.service.report.resource.per_time;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Ceren A. @ 5/23/2019
 * While listening ${SPOT}
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerTimeRQ {
    @NotNull
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
