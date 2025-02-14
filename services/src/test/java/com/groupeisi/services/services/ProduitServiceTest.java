package com.groupeisi.services.services;

import com.groupeisi.services.dao.IProduitRepository;
import com.groupeisi.services.dto.Produit;
import com.groupeisi.services.entities.ProduitEntity;
import com.groupeisi.services.exception.EntityNotFoundException;
import com.groupeisi.services.mapping.ProduitMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProduitServiceTest {

    @Mock
    private IProduitRepository iProduitRepository;
    @Mock
    private ProduitMapper produitMapper;
    @InjectMocks
    ProduitService produitService;
    @Mock
    MessageSource messageSource;

    @Test
    void getAllProduits_ShouldReturnAllMappedProduits() {
        var entityProduit1 = new ProduitEntity();
        var dtoProduit1 = new Produit();

        when(iProduitRepository.findAll()).thenReturn(List.of(entityProduit1));
        when(produitMapper.toProduit(entityProduit1)).thenReturn(dtoProduit1);

        List<Produit> result = produitService.getAllProduits();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(iProduitRepository).findAll();
    }

    @Test
    void getProduit_WithValidId_ShouldReturnProduit() {
        int id = 1;
        var entityProduit = new ProduitEntity();
        var dtoProduit = new Produit();

        when(iProduitRepository.findById(id)).thenReturn(Optional.of(entityProduit));
        when(produitMapper.toProduit(entityProduit)).thenReturn(dtoProduit);

        Produit result = produitService.getOneProduit(id);

        assertNotNull(result);
        verify(iProduitRepository).findById(id);
    }

    @Test
    void getAppProduit_WithInvalidId_ShouldThrowEntityNotFoundException() {
        int id = 9;
        when(iProduitRepository.findById(id)).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("produit.notfound"), any(), eq(Locale.getDefault())))
                .thenReturn("produit not found");

        assertThrows(EntityNotFoundException.class, () -> produitService.getOneProduit(id));
        verify(iProduitRepository).findById(id);
    }

    @Test
    void updateProduit_WithValidId_ShouldReturnUpdatedProduit() {
        int id = 1;
        var dtoProduit = new Produit();
        var entityProduit = new ProduitEntity();

        when(iProduitRepository.findById(id)).thenReturn(Optional.of(entityProduit));
        when(produitMapper.fromProduit(dtoProduit)).thenReturn(entityProduit);
        when(iProduitRepository.save(entityProduit)).thenReturn(entityProduit);
        when(produitMapper.toProduit(entityProduit)).thenReturn(dtoProduit);

        Produit result = produitService.updateProduit(id, dtoProduit);

        assertNotNull(result);
        verify(iProduitRepository).findById(id);
        verify(iProduitRepository).save(entityProduit);
    }

    @Test
    void updateProduit_WithInvalidId_ShouldThrowEntityNotFoundException() {
        int id = 999;
        var dtoProduit = new Produit();

        when(iProduitRepository.findById(id)).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("produit.notfound"), any(), eq(Locale.getDefault())))
                .thenReturn("Produit not found");

        assertThrows(EntityNotFoundException.class, () -> produitService.updateProduit(id, dtoProduit));
        verify(iProduitRepository).findById(id);
        verify(iProduitRepository, never()).save(any());
    }
}
