package com.demoqa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginResponseModel {

    String userId,
            username,
            password,
            token,
            expires,
            created_date;

    @JsonProperty("isActive")
    boolean isActive;

}
