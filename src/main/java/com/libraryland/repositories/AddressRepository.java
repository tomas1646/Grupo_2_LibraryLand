package com.libraryland.repositories;

import com.libraryland.entities.Address;
import com.libraryland.entities.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends BaseRepository<Address,Long> {
    //Not Implemented yet
    @Query(value = "SELECT TOP 0 NULL", nativeQuery = true)
    List<Address> search(String filtro);

    //Not Implemented yet
    @Query(value = "SELECT TOP 0 NULL", nativeQuery = true)
    Page<Address> search(String filtro, Pageable pageable);
}
