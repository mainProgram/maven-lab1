package com.groupeisi.services.dao;

import com.groupeisi.services.entities.ProduitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProduitRepository extends JpaRepository<ProduitEntity, Integer> {
}
