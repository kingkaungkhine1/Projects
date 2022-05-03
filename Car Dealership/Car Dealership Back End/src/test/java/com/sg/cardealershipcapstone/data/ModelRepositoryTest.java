/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealershipcapstone.data;

import com.sg.cardealershipcapstone.models.Model;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author emmastout
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelRepositoryTest {

    @Autowired
    private ModelRepository repo;
    
    @Test
    public void testSomeMethod() {
        
          List<Model> models = repo.findAll();
        
        assertTrue(models.size() > 0);

    }
    
}
