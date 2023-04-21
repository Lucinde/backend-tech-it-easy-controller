package com.novi.TechItEasy.Controllers;

import com.novi.TechItEasy.Exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.html.HTMLTableCaptionElement;

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

    // Springboot weet of je post/get/put/delete gebruikt dus de mapping-links kunnen hetzelfde zijn
    @GetMapping
    public ResponseEntity<String> getTelevisions() {
        return ResponseEntity.ok("Televisions");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTv(@PathVariable int id) {
        // test voor een missend ID
        if(id == 10) {
            throw new RecordNotFoundException("ID cannot be found");
        }
        // standaard return
        return ResponseEntity.ok("Television: " + id);
    }


    @PostMapping
    public ResponseEntity<Object> addTelevision(@RequestParam int id) {
        return ResponseEntity.ok("TV toegevoegd met id: " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTv(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("TV bijgewerkt: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTV(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("TV verwijderd: " + id);
    }

}
