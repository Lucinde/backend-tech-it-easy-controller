package com.novi.TechItEasy.Controllers;

import com.novi.TechItEasy.Exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {
    /*
    een GET-request voor alle televisies
    een GET-request voor 1 televisie
    een POST-request voor 1 televisie
    een PUT-request voor 1 televisie
    een DELETE-request voor 1 televisie
    */

    List<String> televisionDataBase = new ArrayList<>();

    public TelevisionController() {
        televisionDataBase.add("Samsung");
        televisionDataBase.add("Philips");
        televisionDataBase.add("Sony");
    }

    // Springboot weet of je post/get/put/delete gebruikt dus de mapping-links kunnen hetzelfde zijn
    @GetMapping
    public ResponseEntity<String> getTelevisions() {
        StringBuilder tvStrings = new StringBuilder(" ");
        for (String tv : televisionDataBase) {
            tvStrings.append(tv).append("\n");
        }
        return ResponseEntity.ok("Televisions: \n" + tvStrings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTv(@PathVariable int id) {
        // foutmelding voor een missend ID
        if(id < 0 || id > televisionDataBase.size()) {
            throw new RecordNotFoundException("ID cannot be found");
        }
        // standaard return
        String brand = televisionDataBase.get(id);
        return ResponseEntity.ok("Television: " + id + "\nbrand: " + brand);
    }


    @PostMapping
    public ResponseEntity<Object> addTelevision(@RequestParam String brand) {
        televisionDataBase.add(brand);
        return ResponseEntity.ok("TV added: " + brand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTv(@PathVariable int id, String brand) {
        televisionDataBase.set(id, brand);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("TV updated: " + id + " = " + brand);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTV(@PathVariable int id) {
        String brand = televisionDataBase.get(id);
        televisionDataBase.remove(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("TV removed: " + id + " " + brand);
    }

}
