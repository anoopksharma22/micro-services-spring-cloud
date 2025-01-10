package in.itsanoop.student_ms.feignclients;

import in.itsanoop.student_ms.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "gateway-ms")
public interface GlobalFeignClient {
    @GetMapping("/address-ms/api/address/addressById/{id}")
    public AddressDto getAddressById(@PathVariable Integer id);
}
