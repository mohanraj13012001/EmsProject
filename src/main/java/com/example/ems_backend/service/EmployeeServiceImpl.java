package com.example.ems_backend.service;

import com.example.ems_backend.common.UtilLogger;
import com.example.ems_backend.dto.EmployeeDto;
import com.example.ems_backend.exception.ResourceNotFoundException;
import com.example.ems_backend.mapper.EmployeeMapper;
import com.example.ems_backend.model.Employee;
import com.example.ems_backend.repositary.EmployeeRepositary;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepositary employeeRepositary;

    private UtilLogger utilLogger=new UtilLogger();

    private final Logger logger=utilLogger.createLogger();
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee employee1=employeeRepositary.save(employee);
        logger.info("Employee SuccessFully Created with ID{}", employee1.getId());
        return EmployeeMapper.mapToEmployeeDto(employee1);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
       Employee employee=employeeRepositary.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Employee not exists with ID:"+id));

       return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employees=employeeRepositary.findAll();
       return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .toList();
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto,Long id){

        Employee employee=employeeRepositary.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exists with ID:"+id));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

       return EmployeeMapper.mapToEmployeeDto(employeeRepositary.save(employee));
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee=employeeRepositary.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not exists with ID:"+id));
        employeeRepositary.delete(employee);

    }
}
