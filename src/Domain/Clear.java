package Domain;

public class Clear {
    public void tela() {
        String system = System.getProperty("os.name");
        try {
            if (system.equals("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("bash", "-c", "TERM=xterm clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
