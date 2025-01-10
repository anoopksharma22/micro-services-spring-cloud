package in.itsanoop.address_ms.controller;

import in.itsanoop.address_ms.dto.AddressDto;
import in.itsanoop.address_ms.dto.AddressResponseDto;
import in.itsanoop.address_ms.mapper.AdrressMapper;
import in.itsanoop.address_ms.model.Address;
import in.itsanoop.address_ms.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressResponseDto> createAddress(@RequestBody AddressDto addressDTO){
        Address address = addressService.createAddress(addressDTO);
        AddressResponseDto addressResponseDto = new AddressResponseDto();
        AdrressMapper.toAddressResponseDto(address, addressResponseDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(addressResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDto>> getAllAddress(){
        List<AddressResponseDto> addressResponseDtos = new ArrayList<>();
        List<Address> addresses = addressService.getAllAddresses();
        for(Address a: addresses){
            AddressResponseDto addressResponseDto = new AddressResponseDto();
            AdrressMapper.toAddressResponseDto(a, addressResponseDto);
            addressResponseDtos.add(addressResponseDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(addressResponseDtos);
    }

    @GetMapping("/addressById/{id}")
    public ResponseEntity<AddressResponseDto> getAddressById(@PathVariable Integer id){
        logger.info("Serving get address by id " + id);
        AddressResponseDto addressResponseDto = new AddressResponseDto();
        Address address = addressService.getAddressById(id);
        AdrressMapper.toAddressResponseDto(address, addressResponseDto);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponseDto);
    }

}
