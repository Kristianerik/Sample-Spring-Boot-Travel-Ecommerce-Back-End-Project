package com.travelAgency.ecommerce.bootstrap;

import com.travelAgency.ecommerce.dao.CustomerRepository;
import com.travelAgency.ecommerce.dao.DivisionRepository;
import com.travelAgency.ecommerce.entities.Country;
import com.travelAgency.ecommerce.entities.Customer;
import com.travelAgency.ecommerce.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;


    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Customer> existingCustomers = customerRepository.findAll();
        if (existingCustomers.size() <= 1) {
            Division divisionOne = divisionRepository.findById(2L).orElse(null);
            Division divisionTwo = divisionRepository.findById(3L).orElse(null);

            //divisionRepository.save(divisionOne);
            //divisionRepository.save(divisionTwo);

            // Customer 1
            Customer jeff = new Customer("Jeff", "Smith", "123 st", "12345", "(800)-555-5555", divisionOne);
            //divisionOne.getCustomers().add(jeff);


            // Customer 2
            Customer sarah = new Customer("Sarah", "Smith", "123 st", "12345", "(700)-555-5555", divisionOne);
            //divisionOne.getCustomers().add(sarah);


            // Customer 3
            Customer derek = new Customer("Derek", "Adams", "456 st", "67891", "(900)-555-5555", divisionOne);
            //divisionOne.getCustomers().add(derek);


            // Customer 4
            Customer sylas = new Customer("Sylas", "Duncan", "789 st", "67891", "(600)-555-5555", divisionTwo);
            //divisionTwo.getCustomers().add(sylas);


            // Customer 5
            Customer ole = new Customer("Ole", "Olsen", "456 st", "67891", "(200)-555-5555", divisionTwo);
            //divisionTwo.getCustomers().add(ole);

            customerRepository.save(jeff);
            customerRepository.save(sarah);
            customerRepository.save(derek);
            customerRepository.save(sylas);
            customerRepository.save(ole);
        }


        System.out.println("Started in Bootstrap");


    }

}
