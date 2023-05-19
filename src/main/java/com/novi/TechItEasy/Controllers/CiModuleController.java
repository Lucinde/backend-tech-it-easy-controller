package com.novi.TechItEasy.Controllers;

import com.novi.TechItEasy.dtos.CiModuleDto;
import com.novi.TechItEasy.dtos.CiModuleInputDto;
import com.novi.TechItEasy.dtos.TelevisionDto;
import com.novi.TechItEasy.dtos.TelevisionInputDto;
import com.novi.TechItEasy.services.CiModuleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cimodules")
public class CiModuleController {

    private final CiModuleService ciModuleService;

    public CiModuleController(CiModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @GetMapping
    public ResponseEntity<List<CiModuleDto>> getAllCiModules() {
        return ResponseEntity.ok().body(ciModuleService.getAllCiModules());
    }

    @GetMapping("{id}")
    public ResponseEntity<CiModuleDto> getCiModule(@PathVariable Long id) {
        return ResponseEntity.ok().body(ciModuleService.getCiModule(id));
    }

    @PostMapping
    public ResponseEntity<Object> addCiModule(@Valid @RequestBody CiModuleInputDto ciModuleInputDto, BindingResult br) {
        if(br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField() + ": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {
            CiModuleDto ciModuleDto = ciModuleService.createCiModule(ciModuleInputDto);
            URI uri = URI.create(String.valueOf(ServletUriComponentsBuilder.fromCurrentRequest().path("/" + ciModuleDto.id)));
            return ResponseEntity.created(uri).body(ciModuleDto);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCiModule(@PathVariable Long id, @RequestBody CiModuleInputDto ciModuleInputDto) {
        CiModuleDto ciModuleDto = ciModuleService.updateCiModule(id, ciModuleInputDto);
        return ResponseEntity.ok().body(ciModuleDto);
    }

    @PutMapping("/{id}/television/{television_id}")
    public ResponseEntity<Object> assignTelevisionToCi(@PathVariable Long id, @PathVariable Long television_id) {
        return ResponseEntity.ok().body(ciModuleService.assignTelevisionToCi(id, television_id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCiModule(@PathVariable Long id) {
        ciModuleService.deleteCiModule(id);
        return ResponseEntity.noContent().build();
    }

}
