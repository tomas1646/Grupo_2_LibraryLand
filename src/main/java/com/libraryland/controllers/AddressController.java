package com.libraryland.controllers;

import com.libraryland.entities.Address;
import com.libraryland.services.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="api/v1/address")
public class AddressController {
    private AddressService addressService;
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(addressService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Ha ocurrido un error\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(addressService.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Ha ocurrido un error\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Address entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(addressService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Ha ocurrido un error\"}");
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Address entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(addressService.update(id, entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Ha ocurrido un error\"}");
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(addressService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Ha ocurrido un error\"}");
        }
    }
}
