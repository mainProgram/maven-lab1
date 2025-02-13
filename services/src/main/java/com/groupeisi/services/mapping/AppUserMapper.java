package com.groupeisi.services.mapping;

import com.groupeisi.services.dto.AppUser;
import com.groupeisi.services.entities.AppUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface AppUserMapper {

    AppUserEntity fromAppUser(AppUser appUser);

    AppUser toAppUser(AppUserEntity appUserEntity);

    List<AppUser> toAppUsers(List<AppUserEntity> appUserEntities);
}
