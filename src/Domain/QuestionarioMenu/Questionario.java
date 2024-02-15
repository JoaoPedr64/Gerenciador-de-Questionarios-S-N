package Domain.QuestionarioMenu;

import Domain.Clear;

import java.io.*;
import java.util.Scanner;

public class Questionario {
    Scanner scanner = new Scanner(System.in);
    Clear clear = new Clear();
    Perguntas q = new Perguntas();

    // function that creates a file
    public void register() {
        clear.tela();
        // name
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Nome do arquivo: ");
        String name = scanner.nextLine();

        // question
        String answer = "anything";

        String currentDir = System.getProperty("user.dir");
        String directory = new File(currentDir).getParent();
        directory += "/src/Questionarios/"+name+".csv";

        if (q.fileExist(directory)) {
            clear.tela();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("-> Arquivo ja existe <-");
        }

        // creating file
        try(
            FileWriter newFile = new FileWriter(directory, true);
            BufferedWriter bufferFile = new BufferedWriter(newFile);
            PrintWriter writerFile = new PrintWriter(bufferFile);
        ) {
            writerFile.println("id, question");
            writerFile.close();

            clear.tela();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("WARNING: Arquivo criado");
        } catch (IOException e) {
            System.out.println("ERRO: Falha ao criar arquivo");
            e.printStackTrace();
            return;
        }
    }

    // function that deletes a file
    public void delete() {
        clear.tela();
        // Selecting file
        System.out.println("Apagar o arquivo:");
        String arquivo = scanner.nextLine();

        String currentDir = System.getProperty("user.dir");
        String directory = new File(currentDir).getParent();
        directory += "/src/Questionarios/"+arquivo+".csv";

        // if exists delete
        File arq = new File(directory);
        if (arq.exists()) {
            arq.delete();
            clear.tela();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("WARNING: Arquivo deletado com sucesso");
        } else {
            clear.tela();
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("ERRO: Arquivo nao encontrado");
        }
    }
}
