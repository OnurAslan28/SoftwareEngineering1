package com.haw.se1lab.Nutzer.facade.api;

import  com.haw.se1lab.Nutzer.dataaccess.api.entity.Nutzer;

import java.util.List;

public interface NutzerFacade {

    /**
     *
     * @param email
     * @param passwort
     * @return
     */
    Nutzer login(String email,String passwort);
}
