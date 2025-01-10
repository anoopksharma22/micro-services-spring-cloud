package in.itsanoop.student_ms.service;


import in.itsanoop.student_ms.dto.AddressDto;
import in.itsanoop.student_ms.feignclients.GlobalFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CommonService {
    Logger logger = LoggerFactory.getLogger(CommonService.class);

    GlobalFeignClient globalFeignClient;

    public CommonService(GlobalFeignClient globalFeignClient) {
        this.globalFeignClient = globalFeignClient;
    }
    @CircuitBreaker(name = "address-ms", fallbackMethod = "fallbackGetAddressById")
    public AddressDto getAddressById(Integer id) throws Exception {
        logger.info("In circuit breaker calling getAddressById: " + id);
        return globalFeignClient.getAddressById(id);
    }
    public AddressDto fallbackGetAddressById(Throwable throwable){
        logger.info("In circuit breaker fallback returning dummy addredd for: " + throwable.getMessage());
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("dummy");
        addressDto.setStreet("dummy");
        return addressDto;
    }
}
