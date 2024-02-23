package Agenda_Telefonica;

import java.util.Scanner;

/**
 * Aceasta clasa contine clasa main si afiseaza interfata grafica in consola
 */
public class PhoneBookApp { 
    public static void main(String[] args) {
        // Initializarea structurilor de date pentru stocarea contactelor
        RedBlackTree redBlackTree = new RedBlackTree();
        Trie trie = new Trie();
        // Initializarea scanner-ului pentru citirea input-ului de la utilizator
        Scanner scanner = new Scanner(System.in);
        
        // Meniul principal al aplicației
        while (true) {
            // Afisarea opțiunilor disponibile în consolă
            System.out.println("1. Adaugă contact");
            System.out.println("2. Șterge contact");
            System.out.println("3. Caută contact");
            System.out.println("4. Afișează toate contactele");
            System.out.println("5. Ieșire");

            // Citirea opțiunii alese de utilizator
            System.out.print("Alege opțiunea: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumarea caracterului newline

            // Utilizarea unei structuri switch pentru gestionarea opțiunilor
            switch (choice) {
                case 1:
                    // Adăugarea unui contact nou
                    System.out.print("Introdu numele: ");
                    String name = scanner.nextLine();
                    System.out.print("Introdu numărul de telefon: ");
                    String phoneNumber = scanner.nextLine();

                    // Inserarea contactului în structurile de date
                    redBlackTree.insert(name, phoneNumber);
                    trie.insert(name, phoneNumber);

                    System.out.println("Contact adăugat cu succes!\n");
                    break;
                case 2:
                    // Ștergerea unui contact existent
                    System.out.print("Introdu numele contactului de șters: ");
                    String deleteName = scanner.nextLine();

                    // Ștergerea contactului din structurile de date
                    redBlackTree.delete(deleteName);
                    System.out.println("Contact șters cu succes!\n");
                    break;
                case 3:
                    // Căutarea unui contact în structura Trie
                    System.out.print("Introdu numele contactului de căutat: ");
                    String searchName = scanner.nextLine();

                    // Verificarea existenței contactului în Trie
                    boolean foundInTrie = trie.search(searchName);
                    System.out.println("Cautare în Trie: " + (foundInTrie ? "Găsit" : "Nu găsit"));
                    break;
                case 4:
                    // Afișarea tuturor contactelor din structura Red-Black Tree
                    redBlackTree.display();
                    break;
                case 5:
                    // Ieșirea din aplicație
                    System.out.println("Aplicația se închide. La revedere!");
                    System.exit(0);
                    break;
                default:
                    // Mesaj pentru opțiune invalidă
                    System.out.println("Opțiune invalidă. Încearcă din nou.\n");
            }
        }
    }
}
