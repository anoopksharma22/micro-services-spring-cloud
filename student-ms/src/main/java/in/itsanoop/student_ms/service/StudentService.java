package in.itsanoop.student_ms.service;

import in.itsanoop.student_ms.dto.AddressDto;
import in.itsanoop.student_ms.dto.StudentDto;
import in.itsanoop.student_ms.exceptions.StudentNotFoundException;
import in.itsanoop.student_ms.feignclients.AddressFeignClient;
import in.itsanoop.student_ms.feignclients.GlobalFeignClient;
import in.itsanoop.student_ms.mapper.StudentMapper;
import in.itsanoop.student_ms.model.Student;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import in.itsanoop.student_ms.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;
//    private AddressFeignClient addressFeignClient;
    private GlobalFeignClient globalFeignClient;
    private CommonService commonService;

    public StudentService(StudentRepository studentRepository, GlobalFeignClient globalFeignClient, CommonService commonService) {
        this.studentRepository = studentRepository;
        this.globalFeignClient = globalFeignClient;
        this.commonService = commonService;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<StudentDto> getAllStudents() {
        List<StudentDto> studentDtos = new ArrayList<>();
        List<Student> students = studentRepository.findAll();
        for( Student s: students){
            StudentDto studentDto= new StudentDto();
            StudentMapper.toStudentDto(s,studentDto);
            studentDto.setAddressDto(globalFeignClient.getAddressById(s.getAddressId()));
            studentDtos.add(studentDto);
        }

        return studentDtos;
    }

    public StudentDto getStudentById(Integer id) throws Exception {
        Student s = studentRepository.findById(id).orElse(null);
        if( s == null){
            throw new StudentNotFoundException("Student not found");
        }
        StudentDto studentDto = new StudentDto();
        StudentMapper.toStudentDto(s, studentDto);
//        studentDto.setAddressDto(globalFeignClient.getAddressById(s.getAddressId()));
        studentDto.setAddressDto(commonService.getAddressById(s.getAddressId()));
        return studentDto;
    }

//    @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
//    public AddressDto getAddressById(Integer id) throws Exception {
//        return globalFeignClient.getAddressById(id);
//    }

//    public AddressDto fallbackGetAddressById(Integer id) throws Exception {
//        AddressDto addressDto = new AddressDto();
//        addressDto.setCity("dummy");
//        addressDto.setStreet("dummy");
//        return addressDto;
//    }
}
