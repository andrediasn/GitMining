/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc.dcc093.gitmining.model;

/**
 *
 * @author gleiph
 */
public class Contribution {
    
    private final Contributor contributor;
    private final int contributions;

    public Contribution(String login, int contributions) {
        this.contributor = new Contributor(login);
        this.contributions = contributions;
    }

    public String getContributorName() {
        return contributor.getName();
    }

    public int getContributions() {
        return contributions;
    }

    @Override
    public String toString() {
        return  contributor.getName() + " ===> " + contributions;
    }
    
    
    
    
}
