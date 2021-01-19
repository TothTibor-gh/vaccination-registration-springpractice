package com.vakcinainfo.vakcina.bootstrap;

import com.vakcinainfo.vakcina.entity.VaccinationPersonalData;
import com.vakcinainfo.vakcina.repository.VaccinationRegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VaccinationRegistrationRepositoryBootstrap implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(VaccinationRegistrationRepositoryBootstrap.class);

    private final VaccinationRegistrationRepository vaccinationRegistrationRepository;

    @Autowired
    public VaccinationRegistrationRepositoryBootstrap(VaccinationRegistrationRepository vaccinationRegistrationRepository) {
        this.vaccinationRegistrationRepository = vaccinationRegistrationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        vaccinationRegistrationRepository.save(
                new VaccinationPersonalData(
                        UUID.randomUUID(),
                        "Juci",
                        "Budapest",
                        1037,
                        "06702888870",
                        "juci@gmail.com",
                        136643411
                ));

        vaccinationRegistrationRepository.save(
                new VaccinationPersonalData(
                        UUID.randomUUID(),
                        "Frici",
                        "Szeged",
                        6666,
                        "06304848870",
                        "frici@fmails.com",
                        134547771
                ));

        log.debug("Repository successfully uploaded with initial data.");
    }
}