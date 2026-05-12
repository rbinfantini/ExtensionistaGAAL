/**
 * @author Rafael Barros Infantini
 * @author Pedro Henrique Freire
 * @author Guilherme de Rivoredo
 * @version Extensionista - 11/05/2026
 */
public class HillCipher {

    // Matriz Chave 2x2: K = [[3, 3], [2, 5]]
    private static final int[][] K = {
        {3, 3},
        {2, 5}
    };

    // Matriz Inversa 2x2 módulo 26 pré-calculada: INV = [[15, 17], [20, 9]]
    private static final int[][] K_INV = {
        {15, 17},
        {20, 9}
    };

    // Transforma char em int (A=0, Z=25)
    private static int charToInt(char c) {
        return c - 'A';
    }

    // Transforma int em char
    private static char intToChar(int i) {
        return (char) (i + 'A');
    }

    // Multiplica uma matriz 2x2 por um vetor 2x1 aplicando o módulo 26
    private static int[] multiplyMatrixAndVector(int[][] matrix, int[] vector) {
        int[] result = new int[2];
        
        result[0] = (matrix[0][0] * vector[0] + matrix[0][1] * vector[1]) % 26;
        result[1] = (matrix[1][0] * vector[0] + matrix[1][1] * vector[1]) % 26;

        // Corrige módulo negativo (peculiaridade do operador % no Java)
        if (result[0] < 0) result[0] += 26;
        if (result[1] < 0) result[1] += 26;

        return result;
    }

    // Método para Encriptar
    public static String encrypt(String text) {
        // Limpa o texto: deixa tudo maiúsculo e remove o que não for letra (ex: espaços, números)
        text = text.toUpperCase().replaceAll("[^A-Z]", "");

        // Se a palavra tiver tamanho ímpar, adiciona um 'X' no final para fechar o bloco 2x1
        if (text.length() % 2 != 0) {
            text += "X";
        }

        StringBuilder result = new StringBuilder();

        // Pula de 2 em 2 letras
        for (int i = 0; i < text.length(); i += 2) {
            int[] vector = { charToInt(text.charAt(i)), charToInt(text.charAt(i + 1)) };
            int[] encryptedVector = multiplyMatrixAndVector(K, vector);
            result.append(intToChar(encryptedVector[0])).append(intToChar(encryptedVector[1]));
        }

        return result.toString();
    }

    // Método para Desencriptar
    public static String decrypt(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int[] vector = { charToInt(text.charAt(i)), charToInt(text.charAt(i + 1)) };
            int[] decryptedVector = multiplyMatrixAndVector(K_INV, vector);
            result.append(intToChar(decryptedVector[0])).append(intToChar(decryptedVector[1]));
        }

        return result.toString();
    }
}