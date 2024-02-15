package Domain.Menu;

import Domain.Clear;
import Domain.QuestionarioMenu.Perguntas;
import Domain.QuestionarioMenu.Questionario;
import Domain.QuestionarioPrimeiroMenu.FileQ;

import java.util.Scanner;

public class Menu {
    String[] msg = {"0 a 2 sim, nao ligo c foi bem ruinzin", "3 a 4 sim, Top+", "5 a 9 sim, legal mas oque afeta o vasco?", "+10 DAHORA d+"};
    FileQ fileQ = new FileQ();
    Scanner scanner = new Scanner(System.in);
    Questionario questionario = new Questionario();
    Clear clear = new Clear();
    Perguntas perguntas = new Perguntas();

    public void table() {
        String t;
        int id = 0;
        do {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-MENU-=-=-=-=-=-=-=-=-=-=-=-");
            System.out.println("A) Selecionar questionario");
            System.out.println("B) Listar todos os questionarios");
            System.out.println("C) Descrever um questionário");
            System.out.println("D) Menu de questionarios");
            System.out.println("S) Sair");
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
            t = scanner.nextLine().toUpperCase();

            switch (t) {
                case "A":
                    fileQ.start(msg);
                    break;
                case "B":
                    fileQ.list();
                    break;
                case "C":
                    fileQ.descrever();
                    break;
                case "D":
                    String choice = tableQuestionario();
                    choices(choice);
                default:
                    break;
            }
        } while (!t.equals("S"));
    }
    private void choices(String choice) {
        switch (choice) {
            case "A":
                questionario.register();
                break;
            case "B":
                questionario.delete();
                break;
            case "C":
                perguntas.add();
                break;
            case "D":
                perguntas.delete();
                break;
            case "E":
                msg = perguntas.frasesFinais();
            case "S":
                clear.tela();
                break;
            default:
                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                System.out.println("-> Letra desconhecida <-");
                break;
        }
    }
    private String tableQuestionario() {
        clear.tela();
        System.out.println("-=-=-=-=-=-=-=-=MENU-QUESTIONARIO=-=-=-=-=-=-=-=-");
        System.out.println("A) Cadastrar um questionario");
        System.out.println("B) Excluir um questionario");
        System.out.println("C) Adicionar perguntas");
        System.out.println("D) Apagar perguntas");
        System.out.println("E) Mensagem final");
        System.out.println("S) Voltar");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");

        String t = scanner.nextLine().toUpperCase();

        if (!t.matches("[ABCDES]")) {
            System.out.println("-> opcao invalida <-");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {}
            return tableQuestionario();
        }

        return t;
    }
}
