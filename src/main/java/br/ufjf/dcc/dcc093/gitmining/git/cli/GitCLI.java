/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufjf.dcc.dcc093.gitmining.git.cli;

import br.ufjf.dcc.dcc093.gitmining.cli.CLIExecute;
import br.ufjf.dcc.dcc093.gitmining.cli.CLIExecution;
import br.ufjf.dcc.dcc093.gitmining.model.Commit;
import br.ufjf.dcc.dcc093.gitmining.model.Branch;
import br.ufjf.dcc.dcc093.gitmining.model.Merge;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author gleiph
 * @author andrediasn
 */
public class GitCLI {

    public static Boolean clone(String repository, String path) throws IOException {

        String cmdRemove = "rm -rf " + repository;
        CLIExecution execRemove = CLIExecute.execute(cmdRemove, path);
        
        for (String line : execRemove.getError()) {
            System.out.println(line);
        }
        
        if (!execRemove.getError().isEmpty()) {
            throw new RuntimeException("execRemove error");
        }
        
        String cmdClone = "git clone " + repository;
        CLIExecution execClone = CLIExecute.execute(cmdClone, path);
        
        if (!execClone.getError().isEmpty() && !execClone.getError().get(0).contains("Cloning into") && !execClone.getError().get(0).contains("already exists and is not an empty directory")) {
            for (String line : execClone.getError()) {
                System.out.println(line);
            }
            throw new RuntimeException("Git Clone não realizado.");
        }

        return true;
    }

    public static Commit getCommit(String path, String hash) throws IOException {

        String command = "git show --pretty=\"format:'%an' " + hash + " -s";
        CLIExecution execute = CLIExecute.execute(command, path);

        if (!execute.getError().isEmpty()) {
            throw new RuntimeException("The path does not have a Git Repository.");
        }

        String line = execute.getOutput().get(0);

        int commiterNameBegin = line.indexOf("\'");
        int commiterNameEnd = line.indexOf("\'", commiterNameBegin + 1);
        String commiterName = line.substring(commiterNameBegin + 1, commiterNameEnd);

        Commit commit = new Commit(hash, commiterName);
        
        return commit;

    }

    public static List<Branch> getBranch(String path) throws IOException {

        String command = "git branch -a";
        List<Branch> branchs = new ArrayList<>();
        CLIExecution execute = CLIExecute.execute(command, path);

        if (!execute.getError().isEmpty()) {
            throw new RuntimeException("The path does not have a Git Repository.");
        }

        for (String line : execute.getOutput()) {
            String branchName = line.substring(2);
            Branch branch = new Branch(branchName);
            branchs.add(branch);
        }

        return branchs;
    }

    public static List<Merge> getMerge(String path) throws IOException {

        String command = "git log --all --merges --pretty=\"format:'%H''%cn''%P'\"";
        List<Merge> merges = new ArrayList<>();
        CLIExecution execute = CLIExecute.execute(command, path);

        if (!execute.getError().isEmpty()) {
            for (String line : execute.getError()) {

                System.out.println(line);
            }
            throw new RuntimeException("The path does not have a Git Repository.");
        }

        for (String line : execute.getOutput()) {

            int hashBegin = line.indexOf("\'");
            int hashEnd = line.indexOf("\'", hashBegin + 1);
            String hash = line.substring(hashBegin + 1, hashEnd);

            int commiterNameBegin = line.indexOf("\'", hashEnd + 1);
            int commiterNameEnd = line.indexOf("\'", commiterNameBegin + 1);
            String commiterName = line.substring(commiterNameBegin + 1, commiterNameEnd);

            int parentBegin1 = line.indexOf("\'", commiterNameEnd + 1);
            int parentEnd1 = line.indexOf(" ", parentBegin1);
            int parentBegin2 = parentEnd1;
            int parentEnd2 = line.indexOf("\'", parentBegin2 + 1);
            String hashParent1 = line.substring(parentBegin1 + 1, parentEnd1);
            String hashParent2 = line.substring(parentBegin2 + 1, parentEnd2);
            
            List<Commit> parents  = new ArrayList<>();
            parents.add(getCommit(path, hashParent1));
            parents.add(getCommit(path, hashParent2));

            Merge merge = new Merge(hash, commiterName, parents);
            
            merges.add(merge);
        }

        return merges;
    }

}
