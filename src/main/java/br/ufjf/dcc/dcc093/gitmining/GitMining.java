package br.ufjf.dcc.dcc093.gitmining;

import br.ufjf.dcc.dcc093.gitmining.git.cli.GitCLI;
import static br.ufjf.dcc.dcc093.gitmining.github.api.GitHubAPI3.*;
import br.ufjf.dcc.dcc093.gitmining.github.api.GitHubRepository;
import br.ufjf.dcc.dcc093.gitmining.model.Contribution;
import br.ufjf.dcc.dcc093.gitmining.model.Branch;
import br.ufjf.dcc.dcc093.gitmining.model.Merge;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author gleiph
 * @author andrediasn
 */
public class GitMining {

    public static void main(String[] args) throws IOException {
        GitHubRepository repository = null;

        if (args.length > 1) {
            System.out.println("\n\n================================== Desenvolvedores ==================================\n");
            if (args.length == 2) {
                repository = new GitHubRepository(args[1]);
            } else if (args.length == 3) {
                repository = new GitHubRepository(args[1], args[2]);
            } else {
                System.out.println("Invalid arguments");
            }
            if (repository != null) {

                List<Contribution> contributions = repositoryContributorsWithMoreThan10Contributions(repository);
                
                for (Contribution contribution : contributions) {
                    System.out.println(contribution);
                }

                if(contributions.size() > 5 && GitCLI.clone(args[1], args[0])) {
                    String path = args[0] + "/" + repository.getName();

                    System.out.println("\n\n================================ Branches ==================================\n");
                    List<Branch> branchList = GitCLI.getBranch(path);
                    
                    for (Branch branch : branchList) {
                        System.out.println(branch.getName());
                    }

                    System.out.println("\n\n================================ Merges ==================================\n");
                    List<Merge> mergeList = GitCLI.getMerge(path);
                    System.out.println("Total de Merges: " + mergeList.size());

                    for (Merge merge : mergeList) {
                        System.out.println(merge.toString());
                    }

                } else {

                    System.out.println("\n\n Número de desenvolvedores insuficientes para prosseguir.");
                }
            }

        } else {
            System.out.println("Por favor, informe: [diretório_para_clonagem] [url_repositório] [token_opcional]");
        }

    }

}
