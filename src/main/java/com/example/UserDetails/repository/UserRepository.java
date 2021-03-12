package com.example.UserDetails.repository;

import com.example.UserDetails.dto.UserResponseDto;
import com.example.UserDetails.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "select * from userquora u where u.username=?1", nativeQuery = true)
    User findByUserName(String username);

    @Query(value = "select email from userquora u where ?1=any(u.category)", nativeQuery = true)
    List<String> findByCategory(String category);
}
