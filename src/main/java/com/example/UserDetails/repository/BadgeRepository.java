package com.example.UserDetails.repository;

import com.example.UserDetails.entity.Badge;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BadgeRepository extends CrudRepository<Badge, Long> {

    Optional<Badge> findByUsername(String username);
}
