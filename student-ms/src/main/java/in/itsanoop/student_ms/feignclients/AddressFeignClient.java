package in.itsanoop.student_ms.feignclients;

import in.itsanoop.student_ms.dto.AddressDto;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(path = "/api/address", value = "address-ms")
public interface AddressFeignClient {

    @GetMapping("/addressById/{id}")
    public AddressDto getAddressById(@PathVariable Integer id);
}
