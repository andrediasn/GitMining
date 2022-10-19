/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc.dcc093.gitmining.model;

import java.util.List;
/**
 *
 * @author andrediasn
 */
public class Merge {
    
    private final String hash;
    private final String commiterName;
    private final List<Commit> parents;

    public Merge(String hash, String commiterName, List<Commit> parents) {
        this.hash = hash;
        this.commiterName = commiterName;
        this.parents = parents;
    }

    @Override
    public String toString() {

        return "Merge{" + hash + " - " + commiterName + "} Parents{" + parents.get(0).toString() + ", " + parents.get(1).toString() + "}";
    }

    public String getHash() {
        return hash;
    }

    public String getCommiterName() {
        return commiterName;
    }
    
}
