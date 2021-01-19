package com.vakcinainfo.vakcina.service;

import com.vakcinainfo.vakcina.configuration.VaccinationConfigurationProperties;
import com.vakcinainfo.vakcina.entity.VaccinationPersonalData;
import com.vakcinainfo.vakcina.repository.VaccinationRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VaccinationService {

    private static final Logger log = LoggerFactory.getLogger(VaccinationService.class);

    private final VaccinationConfigurationProperties vaccinationConfigurationProperties;
    private final VaccinationRegistrationRepository vaccinationRegistrationRepository;

    @Autowired
    public VaccinationService(VaccinationConfigurationProperties vaccinationConfigurationProperties, VaccinationRegistrationRepository vaccinationRegistrationRepository) {
        this.vaccinationConfigurationProperties = vaccinationConfigurationProperties;
        this.vaccinationRegistrationRepository = vaccinationRegistrationRepository;
    }

    public VaccinationPersonalData addNewRegistration(VaccinationPersonalData vaccinationPersonalData) {
        Optional<VaccinationPersonalData> emailConflict = searchByEmail(vaccinationPersonalData.getEmail());
        Optional<VaccinationPersonalData> sinConfict = searchBySIN(vaccinationPersonalData.getSocialInsuranceNumber());

        if (emailConflict.isEmpty() || sinConfict.isEmpty()) {
            return vaccinationRegistrationRepository.save(vaccinationPersonalData);
        } else {
            log.debug("The email address and/or SIN are already present in the DB.");
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    public List<VaccinationPersonalData> showAllRegistrations(Optional<Integer> page, Optional<Integer> limit) {
        List<VaccinationPersonalData> registrations;
        if (page.isPresent() && limit.isPresent()) {
            Page<VaccinationPersonalData> vaccinationRegistrationPage = vaccinationRegistrationRepository.findAll(PageRequest.of(page.get(), limit.get()));
            registrations = vaccinationRegistrationPage.getContent();
            log.debug("Total count of elements: {}, total possible pages: {}.", vaccinationRegistrationPage.getTotalElements(), vaccinationRegistrationPage.getTotalPages());
        } else if (page.isPresent()) {
            int defaultLimit = vaccinationConfigurationProperties.getLimit();
            Page<VaccinationPersonalData> vaccinationRegistrationPage = vaccinationRegistrationRepository.findAll(PageRequest.of(page.get(), defaultLimit));
            registrations = vaccinationRegistrationPage.getContent();
            log.debug("Since no limit was provided, the default value of {} was used.", defaultLimit);
        } else {
            registrations = vaccinationRegistrationRepository.findAll();
            log.debug("No page or limit parameters were provided, showing all the registrations.");
        }

        return registrations;
    }

    public Optional<VaccinationPersonalData> findById(UUID id) {
        Optional<VaccinationPersonalData> result = vaccinationRegistrationRepository.findById(id);

        if (result.isPresent()) {
            return result;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Optional<VaccinationPersonalData> findByName(String name) {
        Optional<VaccinationPersonalData> result = vaccinationRegistrationRepository.findByName(name);

        if (result.isPresent()) {
            return result;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Optional<VaccinationPersonalData> searchByEmail(String emailAddress) {
        Optional<VaccinationPersonalData> result = vaccinationRegistrationRepository.findByEmail(emailAddress);

        return result.isPresent() ? result : Optional.empty();
    }

    public Optional<VaccinationPersonalData> searchBySIN(int socialInsuranceNumber) {
        Optional<VaccinationPersonalData> result = vaccinationRegistrationRepository.findBySocialInsuranceNumber(socialInsuranceNumber);

        return result.isPresent() ? result : Optional.empty();
    }

    public long countAll() {
        return vaccinationRegistrationRepository.countAll();
    }

}