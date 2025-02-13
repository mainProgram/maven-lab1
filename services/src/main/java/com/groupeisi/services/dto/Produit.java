package com.groupeisi.services.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
    private int id;

    @NotNull(message = "Le nom est requis.")
    private String nom;

    @NotNull
    private double qtStock;

}
