package org.project.stock.repository;

import org.project.stock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByLogin(String login);

    User findByLogin(String login);
}
