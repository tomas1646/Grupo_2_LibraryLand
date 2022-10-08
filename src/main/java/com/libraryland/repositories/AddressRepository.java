package com.libraryland.repositories;

import com.libraryland.entities.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends BaseRepository<Address,Long> {

}
