package com.sstinternaltools.sstinternal_tools.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sstinternaltools.sstinternal_tools.user.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>
{
    Optional<User> findByUsername(String username);
}
