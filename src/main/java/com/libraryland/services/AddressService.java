package com.libraryland.services;

import com.libraryland.entities.Address;
import com.libraryland.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements BaseService<Address>{

    private AddressRepository addressRepository;


    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public List<Address> findAll() throws Exception {
        try{
            List<Address> entities = addressRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Address findById(Long id) throws Exception {
        try{
            Optional<Address> entityOptional = addressRepository.findById(id);
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Address save(Address entity) throws Exception {
        try{
            entity = addressRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Address update(Long id, Address entity) throws Exception {
        try{
            Optional<Address> entityOptional = addressRepository.findById(id);
            Address address = entityOptional.get();
            address = addressRepository.save(address);
            return address;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try{
            if(addressRepository.existsById(id)){
                addressRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
