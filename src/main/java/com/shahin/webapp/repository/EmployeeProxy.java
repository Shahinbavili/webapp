package com.shahin.webapp.repository;

import com.shahin.webapp.configuration.CustomProperties;
import com.shahin.webapp.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class EmployeeProxy {
    @Autowired
    private CustomProperties props;

    /**
     * Get all employees
     *
     * @return An iterable of all employees
     */
    public Iterable<Employee> getEmployees() {
        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {
                }
        );
        log.debug("Get Employees call " + response.getStatusCode());

        return response.getBody();
    }
}
