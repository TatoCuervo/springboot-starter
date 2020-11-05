package com.tatocuervo.springbootstarter.user.repository;

import com.tatocuervo.springbootstarter.common.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
