package com.project.defne.service.client.create;

import lombok.*;

import javax.validation.constraints.*;

/**
 * Created by Ceren A. @ 5/19/2019
 * While listening ${SPOT}
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRQ {
    @NotBlank(message = "Should not be empty")
    @NotEmpty(message = "Should not be empty")
    @NotNull(message = "Should not be empty")
    private String name;
    @NotNull
    @Min(1)
    @Max(10)
    private Integer quota;
}