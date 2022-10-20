package com.libraryland.repositories;

import com.libraryland.entities.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    @Query(value = "SELECT p FROM User p WHERE p.firstname LIKE %:filtro% OR p.lastname LIKE %:filtro% OR p.username LIKE %:filtro%")
    List<User> search(@Param("filtro") String filtro);

    @Query(value = "SELECT p FROM User p WHERE p.firstname LIKE %:filtro% OR p.lastname LIKE %:filtro% OR p.username LIKE %:filtro%")
    Page<User> search(@Param("filtro") String filtro, Pageable pageable);

}
