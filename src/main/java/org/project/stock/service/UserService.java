package org.project.stock.service;

import org.project.stock.security.context.UserContext;

public interface UserService {

    String signIn(UserContext userContext);
}
