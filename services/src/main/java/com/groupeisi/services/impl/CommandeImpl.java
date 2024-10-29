package com.groupeisi.services.impl;

import com.groupeisi.services.ICommande;
import org.springframework.stereotype.Service;

@Service
public class CommandeImpl implements ICommande {

    @Override
    public String getCommand() {
        return "Commande de véhicules";
    }
}
