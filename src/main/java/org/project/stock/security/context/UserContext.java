package org.project.stock.security.context;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UserContext {

    @NotNull
    private final String login;

    @NotNull
    private final String password;

    public UserContext(@JsonProperty("login") String login,
                       @JsonProperty("password") String password) {
        this.login = login;
        this.password = password;
    }
}
