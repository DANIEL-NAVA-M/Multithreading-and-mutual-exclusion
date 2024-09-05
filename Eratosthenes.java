package sieve;

import javax.swing.JFrame;

public class Eratosthenes {
    public static final int CONTROL_SIZE = 100;  
    public static final int NUMBER_SIZE = CONTROL_SIZE / 2; 
    public static final int TESTS = 100; 
    public static final int ROWS = 10; 
    public static final int COLUMNS = 10;

    public static void main(String[] args) {
        // Crear una instancia del marco principal
        EratosthenesFrame frame = new EratosthenesFrame("Eratosthenes Sieve");

        // Configurar el cierre de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hacer visible la ventana
        frame.setVisible(true);
    }
}
