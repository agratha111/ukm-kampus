package com.ipwija.ukm_kampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipwija.ukm_kampus.model.Ukm;

@Repository
public interface UkmRepository extends JpaRepository<Ukm, Long> {
}