package Domain.QuestionarioPrimeiroMenu;

import Domain.Clear;
import Domain.QuestionarioMenu.Perguntas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Scanner;

public class FileQ {
    Clear clear = new Clear();
    Perguntas q = new Perguntas();
    Scanner scanner = new Scanner(System.in);

    public void start(String[] msg){
        int pts = 0; String r;
        // getting file
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("-> Para comecar escreva o nome do questionario:");
        String nome = scanner.nextLine();

        String currentDir = System.getProperty("user.dir");
        String directory = new File(currentDir).getParent();
        directory += "/src/Questionarios/"+nome+".csv";

        if (!q.fileExist(directory)) {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("ERRO: Questionario nao encontrado");
            return;
        }

        clear.tela();

        //  wait warning...
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Saiba que as respostas tem que ser 'sim' ou 'nao', sem abreviar");

        // wait...
        try {
            for (int i = 0; i < 3; ++i) {
                System.out.println("em "+i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {}


        // Game
        clear.tela();
        try (FileReader fileReader = new FileReader(directory)){
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();

            String l;
            while ((l = bufferedReader.readLine()) != null) {
                String[] p = l.split(",");
                if (p.length > 1) {
                    System.out.println("->"+p[1]);
                    do {
                        r = scanner.nextLine().toUpperCase();
                    } while (!r.equals("SIM") && !r.equals("NAO"));

                    if (r.equals("SIM"))
                        pts++;
                }
            }
        } catch (IOException e) {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("ERRO: Falha ao abrir arquivo "+nome+".csv");
            return;
        }

        int ql = q.qLinha(directory);

        // WINERRRRRRRRR
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        if (pts >= 0 && pts <= 2) {
            System.out.println(msg[0]);
        } else if (pts > 2 && pts < 5) {
            System.out.println(msg[1]);
        } else if (pts >= 5 && pts < 10) {
            System.out.println(msg[2]);
        } else {
            System.out.println(msg[3]);
        }

        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Precione enter ou qualquer coisa para voltar");
        String nada = scanner.nextLine();

        clear.tela();
    }

    public void list() {
        String currentDir = System.getProperty("user.dir");
        String directory = new File(currentDir).getParent();
        directory += "/src/Questionarios/";

        clear.tela();

        try {
            String s = System.getProperty("os.name");
            if (s.equals("Windows")) {
                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                new ProcessBuilder("cmd", "/c", "dir "+directory).inheritIO().start().waitFor();
            } else {
                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                new ProcessBuilder("bash", "-c", "TERM=xterm ls "+directory).inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("ERRO: Falha ao execultar o comando");
            return;
        }
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("-> Precione enter para voltar");
        String nada = scanner.nextLine();

        clear.tela();
    }

    public void descrever() {
        // getting file
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Descrever questionario:");
        String nome = scanner.nextLine();

        String currentDir = System.getProperty("user.dir");
        String directory = new File(currentDir).getParent();
        directory += "/src/Questionarios/"+nome+".csv";

        // existe?
        if (!q.fileExist(directory)) {
            clear.tela();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("ERRO: Arquivo nao existe");
            return;
        }

        clear.tela();

        // descrevendo
        try (FileReader fileReader = new FileReader(directory)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String l;
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            while ((l = bufferedReader.readLine()) != null) {
                System.out.println(l);
            }
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        } catch (IOException e) {
            clear.tela();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("ERRO: Falha ao Abrir arquivo");
            return;
        }

        System.out.println("Precione enter para sair");
        String nada = scanner.nextLine();
        clear.tela();
    }
}
