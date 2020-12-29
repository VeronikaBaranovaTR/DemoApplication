package com.example.demo.dao;

import com.example.demo.entity.ScrapedDocketData;
import com.example.demo.entity.ScrapedDocketDataPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapedDocketDataRepository extends CrudRepository<ScrapedDocketData, ScrapedDocketDataPK> {
}
