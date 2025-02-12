package com.groupeisi.services.mapping;

import com.groupeisi.services.dto.Produit;
import com.groupeisi.services.entities.ProduitEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ProduitMapper {
    Produit toProduit(ProduitEntity produitEntity);
    ProduitEntity fromProduit(Produit produit);
}
