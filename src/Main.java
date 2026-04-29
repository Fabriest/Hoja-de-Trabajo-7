import java.io.*;
import java.util.*;

public class Main {

    public static Association<String, String> parseLine(String line) {
        line = line.trim();
        line = line.replace("(", "").replace(")", "");
        String[] parts = line.split(",", 2);
        if (parts.length != 2) return null;

        String english = parts[0].trim().toLowerCase();
        String spanish = parts[1].trim();
        return new Association<>(english, spanish);
    }

    public static void main(String[] args) throws IOException {
        BinaryTree<Association<String, String>> tree = new BinaryTree<>();

        BufferedReader dictReader = new BufferedReader(new FileReader("diccionario.txt"));
        String line;
        while ((line = dictReader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                Association<String, String> entry = parseLine(line);
                if (entry != null) {
                    tree.insert(entry);
                }
            }
        }
        dictReader.close();
        System.out.println("Diccionario en orden alfabetico:");
        tree.inOrder();

        System.out.println("\nTexto traducido:");
        BufferedReader textReader = new BufferedReader(new FileReader("texto.txt"));
        StringBuilder output = new StringBuilder();

        while ((line = textReader.readLine()) != null) {
            String[] words = line.split(" ");

            for (int i = 0; i < words.length; i++) {
                String word = words[i];

                String clean = word.replaceAll("[^a-zA-ZáéíóúüñÁÉÍÓÚÜÑ]", "").toLowerCase();
                String punctuation = word.replaceAll("[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ]", "");

                Association<String, String> query = new Association<>(clean, "");
                Association<String, String> found = tree.search(query);

                if (found != null) {
                    output.append(found.getValue()).append(punctuation);
                } else {
                    output.append("*").append(word).append("*");
                }

                if (i < words.length - 1) {
                    output.append(" ");
                }
            }
            System.out.println(output.toString());
            output.setLength(0);
        }
        textReader.close();
    }
}