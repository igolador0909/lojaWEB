/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lojaweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ConectaJPA {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("lojaWEBPU");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = null;
}
