/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.sg.cardealershipcapstone.controllers;

import com.sg.cardealershipcapstone.models.Vehicle;
import com.sg.cardealershipcapstone.models.VehicleSearchCriteria;
import com.sg.cardealershipcapstone.services.Result;
import com.sg.cardealershipcapstone.services.VehicleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kaung
 */
@CrossOrigin
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

   @Autowired
   private VehicleService service;

   @PostMapping("/all")
   public List<Vehicle> getAllAvailableVehicles(@RequestBody VehicleSearchCriteria criteria) {
       return service.getAllAvailableVehicles(criteria).getPayload();
   }

   @PostMapping("/new")
   public List<Vehicle> getNewVehicles(@RequestBody VehicleSearchCriteria criteria) {
       return service.getNewVehicles(criteria).getPayload();
   }

   @PostMapping("/used")
   public List<Vehicle> getUsedVehicles(@RequestBody VehicleSearchCriteria criteria) {
       return service.getUsedVehicles(criteria).getPayload();
   }

   @GetMapping("/featured")
   public List<Vehicle> getFeaturedehicles() {
       return service.getFeaturedVehicles().getPayload();
   }

   @GetMapping("/{vehicleId}")
   public Vehicle getVehicleById(@PathVariable int vehicleId) {
       return service.getVehicleById(vehicleId).getPayload();
   }
   
   @PostMapping
   public ResponseEntity<?> addVehicle(@RequestBody Vehicle v) {
       Result<Vehicle> result = service.saveVehicle(v);
       if (result.isSuccess()) {
           return ResponseEntity.ok(v);
       } else {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(result.getMessages());
       }
   }

   @PutMapping("/{vehicleId}")
   public ResponseEntity<Void> editVehicle(@PathVariable int vehicleId, @RequestBody Vehicle vehicle) {

       if (vehicle.getVehicleId() <= 0 || vehicleId <= 0 || vehicleId != vehicle.getVehicleId()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }

       Result<Vehicle> result = service.saveVehicle(vehicle);
       if (result.isSuccess()) {
           return ResponseEntity.ok().build();
       } else {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }
   
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable int vehicleId) {
       Result result = service.deleteById(vehicleId);
       if (result.isSuccess()) {
           return ResponseEntity.ok().build();
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
   }

}
