package com.project.defne.service.report.resource.per_time_per_client;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Created by Ceren A. @ 5/24/2019
 * While listening ${SPOT}
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PerTimePerClientRQ {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @NotNull(message = "Should not be empty")
    @Min(1)
    private Long clientId;
}
