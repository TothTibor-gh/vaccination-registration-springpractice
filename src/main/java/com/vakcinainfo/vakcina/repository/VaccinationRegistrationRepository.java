package com.vakcinainfo.vakcina.repository;

import com.vakcinainfo.vakcina.entity.VaccinationPersonalData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VaccinationRegistrationRepository extends PagingAndSortingRepository<VaccinationPersonalData, UUID> {

    @Override
    Page<VaccinationPersonalData> findAll(Pageable pageable);

    @Query("SELECT COUNT(*) FROM VaccinationPersonalData")
    long countAll();

    @Override
    VaccinationPersonalData save(VaccinationPersonalData vaccinationPersonalData);

    @Override
    List<VaccinationPersonalData> findAll();

    @Override
    Optional<VaccinationPersonalData> findById(UUID uuid);

    @Override
    void delete(VaccinationPersonalData vaccinationPersonalData);

    Optional<VaccinationPersonalData> findByEmail(String email);

    Optional<VaccinationPersonalData> findByName(String name);

    Optional<VaccinationPersonalData> findBySocialInsuranceNumber(int sin);

    default void deleteByName(String name) {
        // TODO implement
    }
}