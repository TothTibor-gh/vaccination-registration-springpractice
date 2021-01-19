package com.vakcinainfo.vakcina.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VaccinationConfiguration {

    private static final Logger log = LoggerFactory.getLogger(VaccinationConfiguration.class);

    @Bean
    public int VaccineConfiguration(VaccinationConfigurationProperties vaccinationConfigurationProperties) {
        // TODO: finish implementation if needed
        return 0;
    }

}