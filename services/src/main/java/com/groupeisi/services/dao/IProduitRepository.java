package com.groupeisi.services.dao;

import com.groupeisi.services.entities.ProduitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProduitRepository extends JpaRepository<ProduitEntity, Integer> {

    Optional<ProduitEntity> findByNom(String nom);
}
