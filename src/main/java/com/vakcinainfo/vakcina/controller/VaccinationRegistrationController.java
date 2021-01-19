package com.vakcinainfo.vakcina.controller;

import com.vakcinainfo.vakcina.entity.VaccinationPersonalData;
import com.vakcinainfo.vakcina.service.VaccinationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/vaccine")
public class VaccinationRegistrationController {

    private static final Logger log = LoggerFactory.getLogger(VaccinationRegistrationController.class);

    private final VaccinationService vaccinationService;

    @Autowired
    public VaccinationRegistrationController(VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }

    @PostMapping("/registrate")
    @ResponseStatus(HttpStatus.CREATED)
    public VaccinationPersonalData addNewRegistration(@RequestBody VaccinationPersonalData vaccinationPersonalData) {
        log.info("Adding new registration to database: {}", vaccinationPersonalData);
        VaccinationPersonalData newRegistration = vaccinationService.addNewRegistration(vaccinationPersonalData);

        log.debug("Registration added to database: {}.", newRegistration);
        return newRegistration;
    }

    @GetMapping("/registrations")
    @ResponseStatus(HttpStatus.FOUND)
    public List<VaccinationPersonalData> showAllRegistrations(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                                              @RequestParam(value = "limit", required = false) Optional<Integer> limit) {
        log.info("Retrieving registrations from page {} and limit {}.", page.isPresent() ? page.get() : "n.a.", limit.isPresent() ? limit.get() : "n.a");

        return vaccinationService.showAllRegistrations(page, limit);
    }

    @GetMapping("/findbyid")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<VaccinationPersonalData> findById(@RequestParam(value = "id") UUID id) {
        log.info("Received request to retrieve personal data by id: {}.", id);

        return vaccinationService.findById(id);
    }

    @GetMapping("/findbyname")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<VaccinationPersonalData> findByName(@RequestParam(value = "name") String name) {
        log.info("Received request to retrieve personal data by name: {}.", name);

        return vaccinationService.findByName(name);
    }

    @GetMapping("/findbyemail")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<VaccinationPersonalData> findByEmail(@RequestParam(value = "emailaddress") String emailAddress) {
        log.info("Received request to retrieve personal data by email address: {}.", emailAddress);

        return vaccinationService.searchByEmail(emailAddress);
    }

    @GetMapping("/findbysin")
    @ResponseStatus(HttpStatus.FOUND)
    public Optional<VaccinationPersonalData> findBySIN(@RequestParam(value = "socialinsurancenumber") int socialInsuranceNumber) {
        log.info("Received request to retrieve personal data by SIN: {}.", socialInsuranceNumber);

        return vaccinationService.searchBySIN(socialInsuranceNumber);
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.FOUND)
    public long countAll() {
        log.info("Received request to count all registrated persons.");
        long result = vaccinationService.countAll();

        log.debug("Data successfully fetched: {}.", result);
        return result;
    }

}