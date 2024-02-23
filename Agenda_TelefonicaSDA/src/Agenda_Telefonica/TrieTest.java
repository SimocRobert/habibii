package Agenda_Telefonica;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
/**
 * Aceasta clasa contine teste unitare pentru clasa Trie. Se efectueaza teste pentru operatiile
 * de inserare si cautare in Trie, folosind inserarea a trei utilizatori in prealabil.
 */
public class TrieTest {
    private Trie trie;

    /**
     * Seteaza testul prin initializarea unei instante a clasei Trie si inserarea a trei utilizatori.
     */
    @Before
    public void setUp() {
        trie = new Trie();
        trie.insert("John", "123456789");
        trie.insert("Alice", "987654321");
        trie.insert("Bob", "456123789");
    }

    /**
     * Testeaza operatia de inserare in Trie pentru trei utilizatori si verifica daca acestia pot fi gasiti ulterior.
     */
    @Test
    public void testInsert() {
        assertTrue(trie.search("John"));
        assertTrue(trie.search("Alice"));
        assertTrue(trie.search("Bob"));
    }

    /**
     * Testeaza operatia de cautare in Trie pentru trei utilizatori existenti si unul inexistent.
     */
    @Test
    public void testSearch() {
        assertTrue(trie.search("John"));
        assertTrue(trie.search("Alice"));
        assertTrue(trie.search("Bob"));
        assertFalse(trie.search("Unknown"));
    }
}
