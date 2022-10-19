/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc.dcc093.gitmining.model;

/**
 *
 * @author gleiph
 * @author andrediasn
 */
public class Commit {
    
    private final String hash;
    private final String commiterName;

    public Commit(String hash, String commiterName) {
        this.hash = hash;
        this.commiterName = commiterName;
    }

    @Override
    public String toString() {
        return hash + " - " + commiterName;
    }

    public String getHash() {
        return hash;
    }

    public String getCommiterName() {
        return commiterName;
    }
    
}
