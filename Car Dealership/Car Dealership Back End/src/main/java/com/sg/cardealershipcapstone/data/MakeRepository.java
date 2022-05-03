/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealershipcapstone.data;

import com.sg.cardealershipcapstone.models.Make;
import com.sg.cardealershipcapstone.models.Vehicle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author kaung
 */
@Repository
public interface MakeRepository extends JpaRepository<Make, Integer> {
    
    
    @Query(value = "SELECT * FROM Make WHERE Make = ?1", nativeQuery = true)
    public List<Vehicle> findMakebyMakeName(String makeName);
    
}
