package com.novi.TechItEasy.services;

import com.novi.TechItEasy.exceptions.RecordNotFoundException;
import com.novi.TechItEasy.dtos.WallBracketDto;
import com.novi.TechItEasy.dtos.WallBracketInputDto;
import com.novi.TechItEasy.models.WallBracket;
import com.novi.TechItEasy.repositories.WallBracketRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {

    private final WallBracketRepository wallBracketRepository;

    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }

    public List<WallBracketDto> getAllWallBrackets() {
        Iterable<WallBracket> wallBrackets = wallBracketRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<WallBracketDto> wallBracketDtos = new ArrayList<>();

        for (WallBracket wb : wallBrackets) {
            wallBracketDtos.add(transferWallBracketToDto(wb));
        }

        return wallBracketDtos;
    }

    public WallBracketDto getWallBracket(Long id) {
        Optional<WallBracket> wallBracketOptional = wallBracketRepository.findById(id);

        if(wallBracketOptional.isEmpty()) {
            throw new RecordNotFoundException("No wall bracket found with id: " + id);
        }

        WallBracket wallBracket = wallBracketOptional.get();

        return transferWallBracketToDto(wallBracket);
    }

    public WallBracketDto createWallBracket(WallBracketInputDto wallBracketInputDto) {
        WallBracket wallBracket = transferDtoToWallBracket(wallBracketInputDto);
        wallBracketRepository.save(wallBracket);

        return transferWallBracketToDto(wallBracket);
    }

    public WallBracketDto updateWallBracket(Long id, WallBracketInputDto wallBracketInputDto) {
        Optional<WallBracket> wallBracketOptional = wallBracketRepository.findById(id);

        if(wallBracketOptional.isEmpty()) {
            throw new RecordNotFoundException("No wall bracket found with id: " + id);
        }

        WallBracket updateWallbracket = transferDtoToWallBracket(wallBracketInputDto);
        updateWallbracket.setId(id);
        wallBracketRepository.save(updateWallbracket);

        return transferWallBracketToDto(updateWallbracket);
    }

    public void deleteWallBracket(Long id) {
        Optional<WallBracket> wallBracketOptional = wallBracketRepository.findById(id);

        if(wallBracketOptional.isEmpty()) {
            throw new RecordNotFoundException("No wall bracket found with id: " + id);
        }

        wallBracketRepository.deleteById(id);
    }

    public WallBracketDto transferWallBracketToDto(WallBracket wb) {
        WallBracketDto wallBracketDto = new WallBracketDto();

        wallBracketDto.id = wb.getId();
        wallBracketDto.name = wb.getName();
        wallBracketDto.price = wb.getPrice();
        wallBracketDto.size = wb.getSize();
        wallBracketDto.adjustable = wb.getAdjustable();

        return wallBracketDto;
    }

    public WallBracket transferDtoToWallBracket(WallBracketInputDto wallBracketInputDto) {
        WallBracket wallBracket = new WallBracket();

        wallBracket.setName(wallBracketInputDto.name);
        wallBracket.setSize(wallBracketInputDto.size);
        wallBracket.setPrice(wallBracketInputDto.price);
        wallBracket.setAdjustable(wallBracketInputDto.adjustable);

        return wallBracket;
    }
}
