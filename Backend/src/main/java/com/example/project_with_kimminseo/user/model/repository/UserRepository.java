package com.example.project_with_kimminseo.user.model.repository;

import com.example.project_with_kimminseo.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByName(String name);
}
