package com.groupeisi.services.mapping;

import com.groupeisi.services.dto.AppRoles;
import com.groupeisi.services.entities.AppRolesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AppRolesMapper {
    AppRoles toAppRoles(AppRolesEntity appRolesEntity);
    AppRolesEntity fromAppRoles(AppRoles appRoles);

    List<AppRoles> toAppRolesList(List<AppRolesEntity> appRolesEntity);
    List<AppRolesEntity> fromAppRolesList(List<AppRoles> appRoles);
}
