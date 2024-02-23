package Agenda_Telefonica;
/**
 * Aceasta clasa reprezinta un nod al trie-ului utilizat pentru structura de date Trie.
 * Fiecare nod contine un array de referinte la alte noduri pentru literele alfabetului si
 * un indicator care marcheaza daca acest nod reprezinta sfarsitul unui cuvant (numar de telefon).
 */
class TrieNode {
    TrieNode[] children; // Array de referinte la noduri pentru fiecare litera din alfabet
    boolean isEndOfWord; // Indicator pentru a marca sfarsitul unui cuvant

    /**
     * Constructorul initializeaza array-ul de copii cu noduri nule si indicatorul cu false.
     */
    public TrieNode() {
        this.children = new TrieNode[26];
        this.isEndOfWord = false;
    }
}
