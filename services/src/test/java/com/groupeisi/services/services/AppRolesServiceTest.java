package com.groupeisi.services.services;

import com.groupeisi.services.dao.IAppRolesRepository;
import com.groupeisi.services.dto.AppRoles;
import com.groupeisi.services.entities.AppRolesEntity;
import com.groupeisi.services.exception.EntityNotFoundException;
import com.groupeisi.services.mapping.AppRolesMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppRolesServiceTest {

    @Mock
    private IAppRolesRepository iAppRolesRepository;

    @InjectMocks
    AppRolesService appRolesService;

    @Mock
    private AppRolesMapper appRolesMapper;

    @Mock
    MessageSource messageSource;


    @Test
    void getAppRoles_ShouldReturnListOfAppRoles() {
        var entityRole1 = new AppRolesEntity();
        var dtoRole1 = new AppRoles();

        when(iAppRolesRepository.findAll()).thenReturn(List.of(entityRole1));
        when(appRolesMapper.toAppRoles(entityRole1)).thenReturn(dtoRole1);

        List<AppRoles> result = appRolesService.getAppRoles();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(iAppRolesRepository).findAll();
    }

    @Test
    void getAppRole_WithValidId_ShouldReturnAppRole() {
        int id = 1;
        var entityRole = new AppRolesEntity();
        var dtoRole = new AppRoles();

        when(iAppRolesRepository.findById(id)).thenReturn(Optional.of(entityRole));
        when(appRolesMapper.toAppRoles(entityRole)).thenReturn(dtoRole);

        AppRoles result = appRolesService.getAppRole(id);

        assertNotNull(result);
        verify(iAppRolesRepository).findById(id);
    }

    @Test
    void getAppRole_WithInvalidId_ShouldThrowEntityNotFoundException() {
        int id = 9;
        when(iAppRolesRepository.findById(id)).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("role.notfound"), any(), eq(Locale.getDefault())))
                .thenReturn("Role not found");

        assertThrows(EntityNotFoundException.class, () -> appRolesService.getAppRole(id));
        verify(iAppRolesRepository).findById(id);
    }

    @Test
    void updateAppRoles_WithValidId_ShouldReturnUpdatedRole() {
        int id = 1;
        var dtoRole = new AppRoles();
        dtoRole.setNom("ROLE_ADMIN");
        var entityRole = new AppRolesEntity();

        when(iAppRolesRepository.findById(id)).thenReturn(Optional.of(entityRole));
        when(appRolesMapper.fromAppRoles(dtoRole)).thenReturn(entityRole);
        when(iAppRolesRepository.save(entityRole)).thenReturn(entityRole);
        when(appRolesMapper.toAppRoles(entityRole)).thenReturn(dtoRole);

        AppRoles result = appRolesService.updateAppRoles(id, dtoRole);

        assertNotNull(result);
        verify(iAppRolesRepository).findById(id);
        verify(iAppRolesRepository).save(entityRole);
    }

    @Test
    void updateAppRoles_WithInvalidId_ShouldThrowEntityNotFoundException() {
        int id = 999;
        var dtoRole = new AppRoles();

        when(iAppRolesRepository.findById(id)).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("role.notfound"), any(), eq(Locale.getDefault())))
                .thenReturn("Role not found");

        assertThrows(EntityNotFoundException.class, () -> appRolesService.updateAppRoles(id, dtoRole));
        verify(iAppRolesRepository).findById(id);
        verify(iAppRolesRepository, never()).save(any());
    }

}
