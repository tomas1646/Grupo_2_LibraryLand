package com.libraryland.repositories;

import com.libraryland.entities.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    @Query(value = "SELECT p FROM User p WHERE p.firstName LIKE %:filtro% OR p.lastName LIKE %:filtro% OR p.username LIKE %:filtro%")
    List<User> search(@Param("filtro") String filtro);

    @Query(value = "SELECT p FROM User p WHERE p.firstName LIKE %:filtro% OR p.lastName LIKE %:filtro% OR p.username LIKE %:filtro%")
    Page<User> search(@Param("filtro") String filtro, Pageable pageable);

    Optional<User> findByUsername(String username);
}
