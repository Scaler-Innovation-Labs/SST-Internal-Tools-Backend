package com.sstinternaltools.sstinternal_tools.user.repository;

import com.sstinternaltools.sstinternal_tools.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByUsernameContaining(String keyword);
}
