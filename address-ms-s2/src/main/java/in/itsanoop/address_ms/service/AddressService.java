package in.itsanoop.address_ms.service;

import in.itsanoop.address_ms.dto.AddressDto;
import in.itsanoop.address_ms.model.Address;
import in.itsanoop.address_ms.repository.AddressRepository;
import org.springframework.stereotype.Service;
import in.itsanoop.address_ms.mapper.AdrressMapper;
import java.util.List;

@Service
public class AddressService {
    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address createAddress(AddressDto addressDTO){
        Address address = new Address();
        AdrressMapper.toAddress(addressDTO, address);
        return addressRepository.save(address);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Integer id) {
        return addressRepository.findById(id).orElse(null);
    }
}
