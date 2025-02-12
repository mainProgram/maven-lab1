package com.groupeisi.services.mapping;

import com.groupeisi.services.dto.AppRoles;
import com.groupeisi.services.entities.AppRolesEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AppRolesMapper {
    AppRoles toAppRoles(AppRolesEntity appRolesEntity);
    AppRolesEntity fromAppRoles(AppRoles appRoles);
}
