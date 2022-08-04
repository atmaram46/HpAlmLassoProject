package com.hpalm.HpAlmLassoProject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DefectDetails {

    @JsonProperty
    @NotBlank
    private String domainName;

    @JsonProperty
    @NotBlank
    private String projectName;

    @JsonProperty
    @NotBlank
    private String id;
}
