package com.groupeisi.services.dao;

import com.groupeisi.services.entities.AppRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAppRolesRepository extends JpaRepository<AppRolesEntity, Integer> {

    Optional<AppRolesEntity> findByNom(String nom);
}
