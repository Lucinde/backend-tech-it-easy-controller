package com.novi.TechItEasy.Controllers;

import com.novi.TechItEasy.Exceptions.RecordNotFoundException;
import com.novi.TechItEasy.models.Television;
import com.novi.TechItEasy.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {
    /* autowired is niet aanbevolen dus beter gebruik maken van een constructor */
    /* dit gaan we uiteindelijk verplaatsen naar de servicelaag */
    private final TelevisionRepository televisionRepository;

    public TelevisionController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    // Springboot weet of je post/get/put/delete gebruikt dus de mapping-links kunnen hetzelfde zijn
    @GetMapping
    public ResponseEntity<Iterable<Television>> getTelevisions() {
        return ResponseEntity.ok(televisionRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTv(@PathVariable Long id) {
        // maakt een nieuwe optional televisie; hierna ga je checken of er ook iets in staat
        Optional<Television> televisionOptional = televisionRepository.findById(id);
        // foutmelding voor een missend ID
        if(televisionOptional.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
        Television television = televisionOptional.get();
        return ResponseEntity.ok().body(television);
    }


    @PostMapping
    public ResponseEntity<Television> addTelevision(@RequestBody Television television) {
        televisionRepository.save(television);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + television.getId()).toUriString());
        return ResponseEntity.created(uri).body(television);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTv(@PathVariable Long id, @RequestBody Television television) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if(optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
        Television updateTelevision = optionalTelevision.get();
        updateTelevision.setType(television.getType());
        updateTelevision.setBrand(television.getBrand());
        updateTelevision.setName(television.getName());
        updateTelevision.setPrice(television.getPrice());
        updateTelevision.setAvailableSize(television.getAvailableSize());
        updateTelevision.setRefreshRate(television.getRefreshRate());
        updateTelevision.setScreenType(television.getScreenType());
        updateTelevision.setScreenQuality(television.getScreenQuality());
        updateTelevision.setSmartTv(television.getSmartTv());
        updateTelevision.setWifi(television.getWifi());
        updateTelevision.setVoiceControl(television.getVoiceControl());
        updateTelevision.setHdr(television.getHdr());
        updateTelevision.setBluetooth(television.getBluetooth());
        updateTelevision.setAmbilight(television.getAmbilight());
        updateTelevision.setOriginalStock(television.getOriginalStock());
        updateTelevision.setSold(television.getSold());

        Television editedTelevision = televisionRepository.save(updateTelevision);

        return ResponseEntity.ok().body(editedTelevision);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTV(@PathVariable Long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if(optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
        televisionRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
