package com.novi.TechItEasy.controllers;

import com.novi.TechItEasy.dtos.RemoteControllerDto;
import com.novi.TechItEasy.dtos.RemoteControllerInputDto;
import com.novi.TechItEasy.services.RemoteControllerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/remotes")
public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;


    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping
    public ResponseEntity<List<RemoteControllerDto>> getAllRemotes() {
        return ResponseEntity.ok().body(remoteControllerService.getAllRemotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> getRemote(@PathVariable Long id) {
        return ResponseEntity.ok().body(remoteControllerService.getRemote(id));
    }

    @PostMapping
    public ResponseEntity<Object> addRemote(@Valid @RequestBody RemoteControllerInputDto remoteControllerInputDto, BindingResult br) {
        if(br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {
            RemoteControllerDto remoteControllerDto = remoteControllerService.createRemote(remoteControllerInputDto);
            URI uri = URI.create(String.valueOf(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + remoteControllerDto.id)));
            return ResponseEntity.created(uri).body(remoteControllerDto);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRemote(@PathVariable Long id, @RequestBody RemoteControllerInputDto remoteControllerInputDto) {
        RemoteControllerDto remoteControllerDto = remoteControllerService.updateRemote(id, remoteControllerInputDto);
        return ResponseEntity.ok().body(remoteControllerDto);
    }

    // Remote assignen gebeurt in Television controller, mag hier ook maar hoeft niet. Als je het wel toevoegt zorg dan in ieder geval dat het geen verplicht onderdeel is om een deadlock situatie te voorkomen.

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRemote(@PathVariable Long id) {
        remoteControllerService.deleteRemote(id);
        return ResponseEntity.noContent().build();
    }

}
