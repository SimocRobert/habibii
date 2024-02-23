package Agenda_Telefonica;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Aceasta clasa contine clasa main si afiseaza interfata grafica prin JavaFX.
 */
public class PhoneBookGUI extends Application {
    private RedBlackTree redBlackTree;
    private Trie trie;
    private TextArea displayArea;

    /**
     * Metoda principala care lanseaza aplicatia JavaFX.
     *
     * @param args Argumentele liniei de comanda, neutilizate in acest context.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metoda suprascrisa pentru initializarea interfetei grafice.
     *
     * @param primaryStage Stadiul principal al aplicatiei.
     */
    @Override
    public void start(Stage primaryStage) {	
        // Initializarea structurilor de date pentru stocarea contactelor
        redBlackTree = new RedBlackTree();
        trie = new Trie();

        // Setarea titlului stadiului principal
        primaryStage.setTitle("Agenda Telefonica");

        // Crearea unui BorderPane pentru aranjarea elementelor in interfata
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 20, 10, 20));

        // Crearea unui TextArea pentru afisarea rezultatelor
        displayArea = new TextArea();
        displayArea.setEditable(false);
        borderPane.setCenter(displayArea);

        // Crearea unui VBox pentru a aranja butoanele in coloana
        VBox buttonsVBox = new VBox(10);
        buttonsVBox.setPadding(new Insets(10, 0, 0, 0));

        // Crearea campurilor de introducere pentru nume si numar de telefon
        TextField nameField = new TextField();
        TextField phoneNumberField = new TextField();

        // Crearea butonului pentru adaugarea unui contact
        Button addButton = new Button("Adaugă contact");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String phoneNumber = phoneNumberField.getText();
            redBlackTree.insert(name, phoneNumber);
            trie.insert(name, phoneNumber);
            updateDisplay("Contact adăugat cu succes!");
            displayAllContacts();
        });

        // Crearea butonului pentru stergerea unui contact
        Button deleteButton = new Button("Șterge contact");
        deleteButton.setOnAction(e -> {
            String name = nameField.getText();
            redBlackTree.delete(name);
            updateDisplay("Contact șters cu succes!");
            displayAllContacts();
        });

        // Crearea butonului pentru cautarea unui contact
        Button searchButton = new Button("Caută contact");
        searchButton.setOnAction(e -> {
            String name = nameField.getText();
            String phoneNumber = redBlackTree.getPhoneNumber(name);
            if (phoneNumber != null) {
                updateDisplay("Contact găsit: " + name + " - " + phoneNumber);
            } else {
                updateDisplay("Contactul nu a fost găsit.");
            }
        });

        // Crearea butonului pentru afisarea tuturor contactelor
        Button displayAllButton = new Button("Afișează toate contactele");
        displayAllButton.setOnAction(e -> displayAllContacts());

        // Adaugarea elementelor la VBox
        buttonsVBox.getChildren().addAll(
                new Label("Nume: "), nameField,
                new Label("Număr de Telefon: "), phoneNumberField,
                addButton, deleteButton, searchButton, displayAllButton
        );

        // Setarea elementelor in BorderPane
        borderPane.setRight(buttonsVBox);

        // Crearea scenei cu BorderPane, specificand dimensiunile
        Scene scene = new Scene(borderPane, 600, 400);
        // Setarea scenei pentru stadiu
        primaryStage.setScene(scene);
        // Afisarea stadiului
        primaryStage.show();
    }

    /**
     * Metoda pentru actualizarea afisajului in TextArea.
     *
     * @param message Mesajul de afisat.
     */
    private void updateDisplay(String message) {
        displayArea.appendText(message + "\n");
    }

    /**
     * Metoda pentru afisarea tuturor contactelor din structura de date RedBlack Tree.
     */
    private void displayAllContacts() {
        updateDisplay("Toate contactele:");
        for (String name : redBlackTree.getAllNames()) {
            updateDisplay(name + ": " + redBlackTree.getPhoneNumber(name));
        }
    }
}
