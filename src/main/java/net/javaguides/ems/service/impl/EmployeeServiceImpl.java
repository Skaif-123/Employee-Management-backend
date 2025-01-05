package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.EmployeeMapper;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.mapper.EmployeeMapper;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee=employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployedById(Long employeeId) {
       Employee employee= employeeRepository.findById(employeeId)
        .orElseThrow(() ->
                new ResourceNotFoundException("Employee Does not exist with given ID:  "+employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }
}