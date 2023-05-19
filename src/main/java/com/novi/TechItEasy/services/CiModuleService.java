package com.novi.TechItEasy.services;

import com.novi.TechItEasy.Exceptions.RecordNotFoundException;
import com.novi.TechItEasy.dtos.CiModuleDto;
import com.novi.TechItEasy.dtos.CiModuleInputDto;
import com.novi.TechItEasy.dtos.TelevisionDto;
import com.novi.TechItEasy.dtos.TelevisionInputDto;
import com.novi.TechItEasy.models.CiModule;
import com.novi.TechItEasy.models.RemoteController;
import com.novi.TechItEasy.models.Television;
import com.novi.TechItEasy.repositories.CiModuleRepository;
import com.novi.TechItEasy.repositories.TelevisionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CiModuleService {
    
    private final CiModuleRepository ciModuleRepository;
    private final TelevisionRepository televisionRepository;

    public CiModuleService(CiModuleRepository ciModuleRepository, TelevisionRepository televisionRepository) {
        this.ciModuleRepository = ciModuleRepository;
        this.televisionRepository = televisionRepository;
    }

    public List<CiModuleDto> getAllCiModules() {
        Iterable<CiModule> ciModules = ciModuleRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<CiModuleDto> ciModuleDtos = new ArrayList<>();

        for (CiModule ci: ciModules) {
            ciModuleDtos.add(transferCiModuleToDto(ci));
        }

        return ciModuleDtos;
    }

    public CiModuleDto getCiModule(Long id) {
        Optional<CiModule> ciModuleOptional = ciModuleRepository.findById(id);

        // foutmelding voor een missend ID
        if(ciModuleOptional.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
        CiModule ciModule = ciModuleOptional.get();

        return transferCiModuleToDto(ciModule);

    }

    public CiModuleDto createCiModule(CiModuleInputDto ciModuleInputDto) {
        // tv aanmaken via transfer dto-tv
        CiModule ciModule = transferDtoToCiModule(ciModuleInputDto);
        ciModuleRepository.save(ciModule);

        return transferCiModuleToDto(ciModule);
    }

    public CiModuleDto updateCiModule(Long id, CiModuleInputDto ciModuleInputDto) {
        Optional<CiModule> optionalCiModule = ciModuleRepository.findById(id);
        if(optionalCiModule.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        }

        CiModule updateCiModule = transferDtoToCiModule(ciModuleInputDto);
        //set id toegevoegd omdat je deze anders in de URL én in de JSON toe moet voegen (dubbel werk)
        updateCiModule.setId(id);
        ciModuleRepository.save(updateCiModule);

        return transferCiModuleToDto(updateCiModule);
    }

    public CiModuleDto assignTelevisionToCi(Long id, Long television_id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if(optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        }

        Optional<CiModule> optionalCiModule = ciModuleRepository.findById(television_id);
        if(optionalCiModule.isEmpty()) {
            throw new RecordNotFoundException("No CI-module found with id: " + television_id);
        }

        Television television = optionalTelevision.get();
        CiModule ciModule = optionalCiModule.get();
        // de remote toevoegen aan de television
        ciModule.setTelevision(television);
        // de television opslaan in de repos
        ciModuleRepository.save(ciModule);
        
        return transferCiModuleToDto(ciModule);
        
        
    }

    public void deleteCiModule(Long id) {
        Optional<CiModule> optionalCiModule = ciModuleRepository.findById(id);
        if(optionalCiModule.isEmpty()) {
            throw new RecordNotFoundException("No CI-module found with id: " + id);
        }
        ciModuleRepository.deleteById(id);
    }



    public CiModule transferDtoToCiModule(CiModuleInputDto ciModuleInputDto) {
        CiModule ciModule = new CiModule();

        ciModule.setType(ciModuleInputDto.type);
        ciModule.setName(ciModuleInputDto.name);
        ciModule.setPrice(ciModuleInputDto.price);

        return ciModule;
    }

    public CiModuleDto transferCiModuleToDto(CiModule ciModule) {
        CiModuleDto ciModuleDto = new CiModuleDto();

        ciModuleDto.id = ciModule.getId();
        ciModuleDto.type = ciModule.getType();
        ciModuleDto.name = ciModule.getName();
        ciModuleDto.price = ciModule.getPrice();
        ciModuleDto.television = ciModule.getTelevision();

        return ciModuleDto;
    }
}
