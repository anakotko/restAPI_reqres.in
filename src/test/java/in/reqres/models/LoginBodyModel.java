package in.reqres.models;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoginBodyModel {

    String email, password;
}
