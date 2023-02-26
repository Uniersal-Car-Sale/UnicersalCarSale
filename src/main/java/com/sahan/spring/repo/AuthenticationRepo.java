package com.sahan.spring.repo;

import com.sahan.spring.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepo extends JpaRepository<UserDetail, String> {

    Optional<UserDetail> findAllByUserName (String username);
}
