package com.novi.TechItEasy.services;

import com.novi.TechItEasy.exceptions.RecordNotFoundException;
import com.novi.TechItEasy.dtos.RemoteControllerDto;
import com.novi.TechItEasy.dtos.RemoteControllerInputDto;
import com.novi.TechItEasy.models.RemoteController;
import com.novi.TechItEasy.repositories.RemoteControllerRepository;
import com.novi.TechItEasy.repositories.TelevisionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;


    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository, TelevisionRepository televisionRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public List<RemoteControllerDto> getAllRemotes() {
        Iterable<RemoteController> remoteControllers = remoteControllerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        List<RemoteControllerDto> remoteControllerDtos = new ArrayList<>();

        for (RemoteController remote : remoteControllers) {
            // remote toevoegen aan de lijst via de transfer methode
            remoteControllerDtos.add(transferRemoteToDto(remote));
        }

        return remoteControllerDtos;
    }

    public RemoteControllerDto getRemote(Long id) {
        Optional<RemoteController> remoteControllerOptional = remoteControllerRepository.findById(id);

        if(remoteControllerOptional.isEmpty()) {
            throw new RecordNotFoundException("No remote controller found with id: " + id);
        }
        RemoteController remoteController = remoteControllerOptional.get();

        return transferRemoteToDto(remoteController);
    }

    public RemoteControllerDto createRemote(RemoteControllerInputDto remoteControllerInputDto) {
        RemoteController remoteController = transferDtoToRemote(remoteControllerInputDto);
        remoteControllerRepository.save(remoteController);

        return transferRemoteToDto(remoteController);
    }

    public RemoteControllerDto updateRemote(Long id, RemoteControllerInputDto remoteControllerInputDto) {
        Optional<RemoteController> remoteControllerOptional = remoteControllerRepository.findById(id);
        if(remoteControllerOptional.isEmpty()) {
            throw new RecordNotFoundException("No remote controller found with id: " + id);
        }

        RemoteController updateRemote = transferDtoToRemote(remoteControllerInputDto);
        updateRemote.setId(id);
        remoteControllerRepository.save(updateRemote);

        return transferRemoteToDto(updateRemote);
    }

    public void deleteRemote(Long id) {
        Optional<RemoteController> remoteControllerOptional = remoteControllerRepository.findById(id);
        if(remoteControllerOptional.isEmpty()) {
            throw new RecordNotFoundException("No remote controller found with id: " + id);
        }
        remoteControllerRepository.deleteById(id);
    }

    //assign remote doen we via Television, hoeft niet per se nog een keer andersom gedaan te worden

    public RemoteControllerDto transferRemoteToDto(RemoteController remote) {
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();

        remoteControllerDto.id = remote.getId();
        remoteControllerDto.batteryType = remote.getBatteryType();
        remoteControllerDto.brand = remote.getBrand();
        remoteControllerDto.name = remote.getName();
        remoteControllerDto.price = remote.getPrice();
        remoteControllerDto.compatibleWith = remote.getCompatibleWith();
        remoteControllerDto.originalStock = remote.getOriginalStock();
        remoteControllerDto.television = remote.getTelevision();

        return remoteControllerDto;
    }

    public RemoteController transferDtoToRemote(RemoteControllerInputDto remoteControllerInputDto) {
        RemoteController remoteController = new RemoteController();

        remoteController.setName(remoteControllerInputDto.name);
        remoteController.setBrand(remoteControllerInputDto.brand);
        remoteController.setPrice(remoteControllerInputDto.price);
        remoteController.setOriginalStock(remoteControllerInputDto.originalStock);
        remoteController.setBatteryType(remoteControllerInputDto.batteryType);
        remoteController.setCompatibleWith(remoteControllerInputDto.compatibleWith);
        remoteController.setTelevision(remoteControllerInputDto.television);

        return remoteController;
    }
}
