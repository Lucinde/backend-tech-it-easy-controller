package com.novi.TechItEasy.Controllers;

import com.novi.TechItEasy.dtos.WallBracketDto;
import com.novi.TechItEasy.dtos.WallBracketInputDto;
import com.novi.TechItEasy.services.WallBracketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/wallbrackets")
public class WallBracketController {

    private final WallBracketService wallBracketService;

    public WallBracketController(WallBracketService wallBracketService) {
        this.wallBracketService = wallBracketService;
    }

    @GetMapping
    public ResponseEntity<List<WallBracketDto>> getAllWallBrackets(){
        return ResponseEntity.ok().body(wallBracketService.getAllWallBrackets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracketDto> getWallBracket(@PathVariable Long id) {
        return ResponseEntity.ok().body(wallBracketService.getWallBracket(id));
    }

    @PostMapping
    public ResponseEntity<Object> addWallBracket(@Valid @RequestBody WallBracketInputDto wallBracketInputDto, BindingResult br) {
        if(br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {
            WallBracketDto wallBracketDto = wallBracketService.createWallBracket(wallBracketInputDto);
            URI uri = URI.create(String.valueOf(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + wallBracketDto.id)));
            return ResponseEntity.created(uri).body(wallBracketDto);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateWallBracket(@PathVariable Long id, @RequestBody WallBracketInputDto wallBracketInputDto) {
        WallBracketDto wallBracketDto = wallBracketService.updateWallBracket(id, wallBracketInputDto);
        return ResponseEntity.ok().body(wallBracketDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWallBracket(@PathVariable Long id) {
        wallBracketService.deleteWallBracket(id);
        return ResponseEntity.noContent().build();
    }

}
