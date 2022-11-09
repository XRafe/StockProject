package org.project.stock.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.project.stock.entity.User;
import org.project.stock.repository.UserRepository;
import org.project.stock.security.JwtAction;
import org.project.stock.security.context.UserContext;
import org.project.stock.service.UserService;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JpaUserService implements UserService {

    private final UserRepository userRepository;
    private final JwtAction jwtAction;

    @Transactional
    @SneakyThrows
    @Override
    public String signIn(UserContext userContext) {
        if (!userRepository.existsByLogin(userContext.getLogin())) {
            throw new AccessException("Пользователь не найден");
        }
        User user = userRepository.findByLogin(userContext.getLogin());
        if (!user.getPassword().equals(userContext.getPassword())) {
            throw new AccessException("Пароль не верный!");
        }
        return jwtAction.generateToken(userContext);
    }
}
