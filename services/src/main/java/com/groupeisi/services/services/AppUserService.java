package com.groupeisi.services.services;

import com.groupeisi.services.dao.IAppRolesRepository;
import com.groupeisi.services.dao.IAppUserRepository;
import com.groupeisi.services.dto.AppRoles;
import com.groupeisi.services.dto.AppUser;
import com.groupeisi.services.entities.AppRolesEntity;
import com.groupeisi.services.entities.AppUserEntity;
import com.groupeisi.services.exception.EntityExistsException;
import com.groupeisi.services.exception.EntityNotFoundException;
import com.groupeisi.services.exception.RequestException;
import com.groupeisi.services.mapping.AppRolesMapper;
import com.groupeisi.services.mapping.AppUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@RequiredArgsConstructor
public class AppUserService {
    private final IAppUserRepository iAppUserRepository;
    private final IAppRolesRepository iAppRolesRepository;
    private final AppUserMapper appUserMapper;
    private final AppRolesMapper appRolesMapper;
    private final MessageSource messageSource;

    @Transactional(readOnly = true)
    public List<AppUser> getAllUsers() {
        return StreamSupport.stream(iAppUserRepository.findAll().spliterator(), false)
                .map(appUserMapper::toAppUser)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AppUser getOneUser(int id) {
        return appUserMapper.toAppUser(iAppUserRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    public AppUser createAppUser(AppUser appUser) {
        if (iAppUserRepository.findByEmail(appUser.getEmail()).isPresent()) {
            throw new EntityExistsException(
                    messageSource.getMessage("user.exists",
                            new Object[]{appUser.getEmail()},
                            Locale.getDefault())
            );
        }
        List<AppRolesEntity> roles = new ArrayList<>();
        for (AppRoles roleDto : appUser.getAppRoleEntities()) {
            AppRolesEntity role = iAppRolesRepository.findById(roleDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            messageSource.getMessage("role.notfound", new Object[]{roleDto.getId()}, Locale.getDefault())));
            roles.add(role);
        }
        appUser.setAppRoleEntities(appRolesMapper.toAppRolesList(roles));

        AppUserEntity savedUser = iAppUserRepository.save(appUserMapper.fromAppUser(appUser));

        return appUserMapper.toAppUser(savedUser);
    }

    @Transactional
    public AppUser updateAppUser(int id, AppUser appUser) {
        return iAppUserRepository.findById(id)
                .map(entity -> {
                    appUser.setId(id);
                    return appUserMapper.toAppUser(
                            iAppUserRepository.save(appUserMapper.fromAppUser(appUser)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteAppUser(int id) {
        getOneUser(id);
        try {
            iAppUserRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("user.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
