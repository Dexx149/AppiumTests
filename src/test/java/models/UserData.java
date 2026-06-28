package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserData(
        @JsonProperty("login") String login,
        @JsonProperty("password") String password
) {
    public static UserData validUser() {
        return new UserData("login2", "password2");
    }

    public static UserData invalidUser() {
        return new UserData("wrong_login", "wrong_pass");
    }

}

