package Domain.QuestionarioMenu;

import Domain.Clear;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Perguntas {
    Scanner scanner = new Scanner(System.in);
    Clear clear = new Clear();
    public void add() {
        // getting file
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Adicionar pergunta ao questionario:");
        String nome = scanner.nextLine();

        // directory
        String currentDir = System.getProperty("user.dir");
        String directory = new File(currentDir).getParent();
        directory += "/src/Questionarios/"+nome+".csv";

        // getting line
        String a;
        int q = 0;
        do {
            clear.tela();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println(++q+"°) Pergunta de sim ou nao:");
            String p = scanner.nextLine();
            String r = "nada";
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

            int ql = qLinha(directory);

            InfoQ l = new InfoQ(p, ql, r);

            try {
                FileWriter fileW = new FileWriter(directory, true);
                BufferedWriter fileBW = new BufferedWriter(fileW);

                fileBW.write(l.getId() + ", " + l.getQuestion() + "\n");
                fileBW.flush();

                fileBW.close();
            } catch (IOException e) {
                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                System.out.println("ERRO: Falha ao adicionar pergunta ao arquivo");
                System.out.println("LOCAL: " + directory);
                e.printStackTrace();
                return;
            }

            System.out.println("Continuar? (Enter para continuar e a tecla V para sair)");
            a = scanner.nextLine().toUpperCase();
        } while (!a.equals("V"));
    }

    public void delete() {
        // getting file // id
        clear.tela();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Apagar pergunta do questionario:");
        String nome = scanner.nextLine();
        System.out.println("ID da pergunta: (Para saber descreva o questionario)");
        int id = scanner.nextInt();
        scanner.nextLine();

        // file
        String currentDir = System.getProperty("user.dir");
        String directory = new File(currentDir).getParent();
        directory += "/src/Questionarios/"+nome+".csv";

        if (!fileExist(directory)) {
            System.out.println("ERRO: Questionario nao existe");
            return;
        }

        int ql = qLinha(directory);
        if (id < 0 || id > ql) {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("ERRO: ID invalido");
            return;
        }

        // saving file
        ArrayList<String> arrayLinhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(directory))){

            String l;
            br.readLine();
            while ((l = br.readLine()) != null) {
                int lID = Integer.parseInt(l.split(",")[0].trim());
                if (id != lID) {
                    arrayLinhas.add(l);
                }
            }

        }catch (IOException e) {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("ERRO: Falha ao salvar arquivo");
            e.printStackTrace();
            return;
        }

        // Creating new file
        try(PrintWriter pw = new PrintWriter(new FileWriter(directory))) {
            pw.println("id, question");
            for (String linha : arrayLinhas) {
                pw.println(linha);
            }
        } catch (IOException e) {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("ERRO: Falha ao escrever no arquivo");
            e.printStackTrace();
            return;
        }

        clear.tela();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("WARNING: Pergunta removida com sucesso");
    }
    public int qLinha (String arquivo) {
        int Ql = 0;

        try {
            String l;

            FileReader rFile = new FileReader(arquivo);
            BufferedReader bFile = new BufferedReader(rFile);

            while ((l = bFile.readLine()) != null) {
                Ql++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Ql;
    }

    public String[] frasesFinais() {
        clear.tela();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("A mensagem final e para todos os questionarios");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Mensagem de 0 a 2 pts:");
        String m1 = scanner.nextLine();
        System.out.println("Mensagem de 2 a 4 pts:");
        String m2 = scanner.nextLine();
        System.out.println("Mensagem de 5 a 9 pts:");
        String m3 = scanner.nextLine();
        System.out.println("Mensagem 10+ pts");
        String m4 = scanner.nextLine();

        clear.tela();
        String[] mensagens = {m1, m2, m3, m4};
        return mensagens;
    }
    public boolean fileExist(String file) {
        File Afile = new File(file);
        return Afile.exists();
    }
}
