package com.hpalm.HpAlmLassoProject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public final class UserAuthDetails {

    @JsonProperty
    @NotBlank
    private String user;

    @JsonProperty
    @NotBlank
    private String password;
}
