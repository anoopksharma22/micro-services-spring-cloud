package in.itsanoop.address_ms.mapper;

import in.itsanoop.address_ms.dto.AddressResponseDto;
import in.itsanoop.address_ms.dto.AddressDto;
import in.itsanoop.address_ms.model.Address;

public class AdrressMapper {
    public static void toAddressResponseDto(Address address, AddressResponseDto addressResponseDto){
        addressResponseDto.setId(address.getId());
        addressResponseDto.setCity(address.getCity());
        addressResponseDto.setStreet(address.getStreet());
    }
    public static void toAddress(AddressDto addressDto, Address address){
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
    }
}
