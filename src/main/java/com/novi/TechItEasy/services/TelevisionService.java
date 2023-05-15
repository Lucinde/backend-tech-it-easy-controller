package com.novi.TechItEasy.services;

import com.novi.TechItEasy.Exceptions.RecordNotFoundException;
import com.novi.TechItEasy.dtos.TelevisionDto;
import com.novi.TechItEasy.dtos.TelevisionInputDto;
import com.novi.TechItEasy.models.Television;
import com.novi.TechItEasy.repositories.TelevisionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {
    private final TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public List<TelevisionDto> getAllTelevisions() {
        Iterable<Television> televisions = televisionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<TelevisionDto> televisionDtos = new ArrayList<>();

        for (Television tv: televisions) {
            // tv toevoegen aan de lijst via de transfer methode
            televisionDtos.add(transferTelevisionToDto(tv));
        }

        return televisionDtos;
    }

    public TelevisionDto getTv(Long id) {
         // maakt een nieuwe optional televisie; hierna ga je checken of er ook iets in staat
        Optional<Television> televisionOptional = televisionRepository.findById(id);

        // foutmelding voor een missend ID
        if(televisionOptional.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
        Television television = televisionOptional.get();

        return transferTelevisionToDto(television);
    }

    public TelevisionDto createTelevision(TelevisionInputDto televisionInputDto) {
        // tv aanmaken via transfer dto-tv
        Television television = transferDtoToTelevision(televisionInputDto);
        televisionRepository.save(television);

        return transferTelevisionToDto(television);
    }

    public TelevisionDto updateTelevision(Long id, TelevisionInputDto televisionInputDto) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if(optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        }

        Television updateTelevision = transferDtoToTelevision(televisionInputDto);
        //set id toegevoegd omdat je deze anders in de URL én in de JSON toe moet voegen (dubbel werk)
        updateTelevision.setId(id);
        televisionRepository.save(updateTelevision);

        return transferTelevisionToDto(updateTelevision);
    }

    public void deleteTelevision(Long id) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        if(optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        }
        televisionRepository.deleteById(id);
    }

    // voor GET requests
    public TelevisionDto transferTelevisionToDto(Television tv) {
        TelevisionDto televisionDto = new TelevisionDto();

        televisionDto.id = tv.getId();
        televisionDto.type = tv.getType();
        televisionDto.brand = tv.getBrand();
        televisionDto.name = tv.getName();
        televisionDto.price = tv.getPrice();
        televisionDto.availableSize = tv.getAvailableSize();
        televisionDto.refreshRate = tv.getRefreshRate();
        televisionDto.screenType = tv.getScreenType();
        televisionDto.screenQuality = tv.getScreenQuality();
        televisionDto.smartTv = tv.getSmartTv();
        televisionDto.wifi = tv.getWifi();
        televisionDto.voiceControl = tv.getVoiceControl();
        televisionDto.hdr = tv.getHdr();
        televisionDto.bluetooth = tv.getBluetooth();
        televisionDto.ambilight = tv.getAmbilight();
        televisionDto.originalStock = tv.getOriginalStock();
        televisionDto.sold = tv.getSold();

        return televisionDto;
    }

    // Voor PUT requests
    public Television transferDtoToTelevision(TelevisionInputDto televisionInputDto) {
        Television television = new Television();

        // geen setId nodig, genereert de database of staat in de URL
        // television.setId(televisionInputDto.id);
        television.setType(televisionInputDto.type);
        television.setBrand(televisionInputDto.brand);
        television.setName(televisionInputDto.name);
        television.setPrice(televisionInputDto.price);
        television.setAvailableSize(televisionInputDto.availableSize);
        television.setRefreshRate(televisionInputDto.refreshRate);
        television.setScreenType(televisionInputDto.screenType);
        television.setScreenQuality(televisionInputDto.screenQuality);
        television.setSmartTv(televisionInputDto.smartTv);
        television.setWifi(televisionInputDto.wifi);
        television.setVoiceControl(televisionInputDto.voiceControl);
        television.setHdr(televisionInputDto.hdr);
        television.setBluetooth(televisionInputDto.bluetooth);
        television.setAmbilight(televisionInputDto.ambilight);
        television.setOriginalStock(televisionInputDto.originalStock);
        television.setSold(televisionInputDto.sold);

        return television;
    }


}
