package com.attraya.ems.service;

import com.attraya.ems.dto.EmployeeDto;
import com.attraya.ems.entity.Employee;
import com.attraya.ems.exception.ResourceNotFoundException;
import com.attraya.ems.mapper.EmployeeMapper;
import com.attraya.ems.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with given id :" + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }
}
