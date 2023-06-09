package com.novi.TechItEasy.repositories;

import com.novi.TechItEasy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
