/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealershipcapstone.services;

import com.sg.cardealershipcapstone.data.MakeRepository;
import com.sg.cardealershipcapstone.data.ModelRepository;
import com.sg.cardealershipcapstone.data.VehicleRepository;
import com.sg.cardealershipcapstone.models.Make;
import com.sg.cardealershipcapstone.models.Model;
import com.sg.cardealershipcapstone.models.Vehicle;
import com.sg.cardealershipcapstone.models.VehicleSearchCriteria;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kaung
 */
@Service
public class VehicleService {

    @Autowired
    VehicleRepository repo;
    
    @Autowired
    MakeRepository makeRepo;
    
     @Autowired
    ModelRepository modelRepo;

    public Result<List<Vehicle>> getAllAvailableVehicles(VehicleSearchCriteria criteria) {
        Result<List<Vehicle>> result = new Result<>();
        //False corresponds to 'Sold = false' in our SQL query, because we 
        //only want cares that are available for purchase
        result.setPayload(repo.getAllAvailableVehiclesSearch("False", criteria.getMakeModelYear(), criteria.getMinYear(),
                criteria.getMaxYear(), criteria.getMinSalePrice(),
                criteria.getMaxSalePrice()));
        return result;
    }

    public Result<List<Vehicle>> getNewVehicles(VehicleSearchCriteria criteria) {
        Result<List<Vehicle>> result = new Result<>();
        result.setPayload(repo.getNewOrUsedVehicleSearch("New", criteria.getMakeModelYear(), criteria.getMinYear(),
                criteria.getMaxYear(), criteria.getMinSalePrice(),
                criteria.getMaxSalePrice(), "False"));
        return result;
    }

    public Result<List<Vehicle>> getUsedVehicles(VehicleSearchCriteria criteria) {
        Result<List<Vehicle>> result = new Result<>();
        result.setPayload(repo.getNewOrUsedVehicleSearch("Used", criteria.getMakeModelYear(), criteria.getMinYear(),
                criteria.getMaxYear(), criteria.getMinSalePrice(),
                criteria.getMaxSalePrice(), "False"));
        return result;
    }

    public Result<List<Vehicle>> getFeaturedVehicles() {
        Result<List<Vehicle>> result = new Result<>();
        result.setPayload(repo.getFeaturedVehicles());
        return result;
    }

    public Result<Vehicle> saveVehicle(Vehicle v) {
        v = grabMakeAndModel(v);
        Result<Vehicle> result = new Result<>();
        if (result.isSuccess()) {
            v = repo.save(v);
            result.setPayload(v);
        }
        return result;
    }

    private Vehicle grabMakeAndModel(Vehicle v) {
        Optional<Make> ma = makeRepo.findById(v.getMake().getMakeId());
        if (ma.isPresent()) {
            Make make = ma.get();
            v.setMake(make);
        }
        
        Optional<Model> mo = modelRepo.findById(v.getModel().getModelId());
        if (mo.isPresent()) {
            Model model = mo.get();
            v.setModel(model);
        }
        
        return v;   
    }
    
    public Result deleteById(int vehicleId) {
        repo.deleteById(vehicleId);
        return new Result<>();
    }

    public Result<Vehicle> getVehicleById(int vehicleId) {
        Result<Vehicle> result = new Result<>();
        Optional<Vehicle> v = repo.findById(vehicleId);
        result.setPayload(repo.findById(vehicleId).orElse(null));
        return result;
    }

    // private Result<Vehicle> validate(Vehicle v) {
    //     Result<Vehicle> result = new Result<>();
    //     LocalDate today = LocalDate.now();
    //     int nextYear = today.getYear() + 1;
    //     //Add other validation logic here
        
    //     //Year must be between 2000 and next year
    //     if(v.getYear() > nextYear) {
    //         result.addMessage("Year must be between 2000 and next year.");
    //     }
        
    //     //If type is new, mileage must be btwn 1-1000
    //     if((v.getType().equals("New") && v.getMileage() < 1) 
    //             || (v.getType().equals("New") && v.getMileage() > 1000) ) {
    //         result.addMessage("Mileage for new cars must be between 1 and 1000.");
    //     }
        
    //     //If type is used, mileage must be over 1000
    //     if(v.getType().equals("Used") && v.getMileage() < 1000) {
    //         result.addMessage("Mileage for used cars must be greater than 1000.");
    //     }
        
    //     //MSRP must be positive
    //     if(v.getMsrp().compareTo(BigDecimal.ZERO) == -1) {
    //         result.addMessage("MSRP must be a positive number.");
    //     }
        
    //     //Sale price must be positive
    //     if(v.getSalePrice().compareTo(BigDecimal.ZERO) == -1) {
    //         result.addMessage("Sale price must be a positive number.");
    //     }
        
    //     //Sale price can't be bigger than MSRP
    //     if(v.getSalePrice().compareTo(v.getMsrp()) == 1) {
    //         result.addMessage("Sale price cannot be greater than MSRP.");
    //     }
        

    //     //Get all validation errors from model annotations
    //     Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    //     Set<ConstraintViolation<Vehicle>> errs = validator.validate(v);
    //     for (ConstraintViolation<Vehicle> err : errs) {
    //         result.addMessage(err.getMessage());
    //     }

    //     return result;
    // }
}


