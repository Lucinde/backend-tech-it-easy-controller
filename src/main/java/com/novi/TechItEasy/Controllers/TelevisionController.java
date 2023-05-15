package com.novi.TechItEasy.Controllers;

import com.novi.TechItEasy.Exceptions.RecordNotFoundException;
import com.novi.TechItEasy.dtos.TelevisionDto;
import com.novi.TechItEasy.dtos.TelevisionInputDto;
import com.novi.TechItEasy.models.Television;
import com.novi.TechItEasy.services.TelevisionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {
//    autowired is niet aanbevolen dus beter gebruik maken van een constructor
    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    // Springboot weet of je post/get/put/delete gebruikt dus de mapping-links kunnen hetzelfde zijn
    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions() {
        return ResponseEntity.ok().body(televisionService.getAllTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTv(@PathVariable Long id) {
        return ResponseEntity.ok().body(televisionService.getTv(id));
    }

    @PostMapping
    public ResponseEntity<Object> addTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto, BindingResult br) {
        if(br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {
            TelevisionDto televisionDto = televisionService.createTelevision(televisionInputDto);
            URI uri = URI.create(String.valueOf(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + televisionDto.id)));
            return ResponseEntity.created(uri).body(televisionDto);
        }
//        Deze manier voor het maken van een URI staat er nog in ter herinnering:
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + television.getId()).toUriString());
//        return ResponseEntity.created(uri).body(television);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Object> updateTv(@PathVariable Long id, @RequestBody Television television) {
//        Optional<Television> optionalTelevision = televisionRepository.findById(id);
//        if(optionalTelevision.isEmpty()) {
//            throw new RecordNotFoundException("No television found with id: " + id);
//        }
//        Television updateTelevision = optionalTelevision.get();
//        updateTelevision.setType(television.getType());
//        updateTelevision.setBrand(television.getBrand());
//        updateTelevision.setName(television.getName());
//        updateTelevision.setPrice(television.getPrice());
//        updateTelevision.setAvailableSize(television.getAvailableSize());
//        updateTelevision.setRefreshRate(television.getRefreshRate());
//        updateTelevision.setScreenType(television.getScreenType());
//        updateTelevision.setScreenQuality(television.getScreenQuality());
//        updateTelevision.setSmartTv(television.getSmartTv());
//        updateTelevision.setWifi(television.getWifi());
//        updateTelevision.setVoiceControl(television.getVoiceControl());
//        updateTelevision.setHdr(television.getHdr());
//        updateTelevision.setBluetooth(television.getBluetooth());
//        updateTelevision.setAmbilight(television.getAmbilight());
//        updateTelevision.setOriginalStock(television.getOriginalStock());
//        updateTelevision.setSold(television.getSold());
//
//        Television editedTelevision = televisionRepository.save(updateTelevision);
//
//        return ResponseEntity.ok().body(editedTelevision);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteTV(@PathVariable Long id) {
//        Optional<Television> optionalTelevision = televisionRepository.findById(id);
//        if(optionalTelevision.isEmpty()) {
//            throw new RecordNotFoundException("No television found with id: " + id);
//        }
//        televisionRepository.deleteById(id);
//
//        return ResponseEntity.noContent().build();
//    }

}
