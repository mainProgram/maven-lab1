package com.groupeisi.services.dao;

import com.groupeisi.services.entities.AppRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAppRolesRepository extends JpaRepository<AppRolesEntity, Integer> {
}
