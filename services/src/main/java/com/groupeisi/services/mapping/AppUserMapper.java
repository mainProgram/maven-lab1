package com.groupeisi.services.mapping;

import com.groupeisi.services.dto.AppUser;
import com.groupeisi.services.entities.AppUserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AppUserMapper {
    AppUser toAppUser(AppUserEntity appUserEntity);
    AppUserEntity fromAppUser(AppUser appUser);
}
