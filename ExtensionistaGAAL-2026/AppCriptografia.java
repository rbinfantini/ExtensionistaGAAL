/**
 * @author Rafael Barros Infantini
 * @author Pedro Henrique Freire
 * @author Guilherme de Rivoredo
 * @version Extensionista - 11/05/2026
 */
import javax.swing.*;
import java.awt.*;

public class AppCriptografia {
    public static void main(String[] args) {
        // Criação da janela principal
        JFrame frame = new JFrame("GAAL: Cifra de Hill");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1)); // 4 linhas e 1 coluna

        // Criando os elementos da tela
        JLabel titleLabel = new JLabel("Digite a mensagem para encriptar ou desencriptar:", SwingConstants.CENTER);
        JTextField inputField = new JTextField();
        
        JPanel panelBotoes = new JPanel();
        JButton btnEncrypt = new JButton("Encriptar");
        JButton btnDecrypt = new JButton("Desencriptar");
        panelBotoes.add(btnEncrypt);
        panelBotoes.add(btnDecrypt);

        JLabel resultLabel = new JLabel("Resultado: ", SwingConstants.CENTER);

        // Ações do botão Encriptar
        btnEncrypt.addActionListener(e -> {
            String texto = inputField.getText();
            if (!texto.isBlank()) {
                String cifrado = HillCipher.encrypt(texto);
                resultLabel.setText("Resultado: " + cifrado);
            }
        });

        // Ações do botão Desencriptar
        btnDecrypt.addActionListener(e -> {
            String texto = inputField.getText();
            if (!texto.isBlank()) {
                String decifrado = HillCipher.decrypt(texto);
                resultLabel.setText("Resultado: " + decifrado);
            }
        });

        // Adicionando os elementos no Frame na ordem correta
        frame.add(titleLabel);
        frame.add(inputField);
        frame.add(panelBotoes);
        frame.add(resultLabel);

        // Exibe a janela no centro da tela
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}