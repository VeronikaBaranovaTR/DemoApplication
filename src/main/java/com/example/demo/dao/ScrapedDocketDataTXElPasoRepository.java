package com.example.demo.dao;

import com.example.demo.entity.ScrapedDocketDataTXElPaso;
import com.example.demo.entity.ScrapedDocketDataTXElPasoPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapedDocketDataTXElPasoRepository extends CrudRepository<ScrapedDocketDataTXElPaso, ScrapedDocketDataTXElPasoPK> {
    /*List<ScrapedDocketDataTXElPaso> findByCaseType(String caseType);
    List<ScrapedDocketDataTXElPaso> findByDocketNumber(String number);*/
}
