package Agenda_Telefonica;

/**
 * Aceasta clasa reprezinta o structura de date Trie pentru stocarea si cautarea rapida a cuvintelor.
 * Fiecare nod al trie.ului contine o referinta la un array de noduri pentru literele alfabetului,
 * si un indicator care marcheaza daca cuvantul pana la acest nod reprezinta sfarsitul unui numar de telefon.
 */
public class Trie {
    private TrieNode root;

    /**
     * Constructorul initializeaza radacina trie-ului la un nod nou.
     */
    public Trie() {
        this.root = new TrieNode();
    }

    /**
     * Insereaza un cuvant in trie, construind nodurile corespunzatoare pentru fiecare litera.
     * La final, marcheaza nodul corespunzator ultimei litere ca sfarsitul unui numar de telefon.
     *
     * @param word Cuvantul de inserat in trie.
     * @param phoneNumber Numarul de telefon asociat cuvantului.
     */
    public void insert(String word, String phoneNumber) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true; // sfarsitul cuvantului
    }

    /**
     * Cauta un cuvant in trie, parcurgand nodurile corespunzatoare fiecarei litere din cuvant.
     * Returneaza true daca cuvantul este gasit in trie, altfel false.
     *
     * @param word Cuvantul de cautat in trie.
     * @return true daca cuvantul este gasit, altfel false.
     */
    public boolean search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                return false; // cuvantul nu este gasit
            }
            current = current.children[index];
        }
        return current.isEndOfWord; // verifica daca este sfarsitul unui cuvant.
    }
}
