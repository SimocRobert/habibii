package Agenda_Telefonica;

import static org.junit.Assert.*;
import org.junit.*;
/**
 * Aceasta clasa contine teste pentru functionalitatea clasei {@link RedBlackTree}.
 * Se testeaza operatiile de inserare, stergere si cautare in cadrul arborelui Red-Black.
 */
public class RedBlackTreeTest {
    private RedBlackTree redBlackTree;

    /**
     * Seteaza conditiile initiale pentru teste prin crearea unui arbore Red-Black
     * si inserarea a trei utilizatori cu nume si numere de telefon asociate.
     */
    @Before
    public void setUp() {
        redBlackTree = new RedBlackTree();
        redBlackTree.insert("John", "123456789");
        redBlackTree.insert("Alice", "987654321");
        redBlackTree.insert("Bob", "456123789");
    }

    /**
     * Testeaza corectitudinea operatiei de inserare, verificand daca numerele de telefon
     * asociate utilizatorilor sunt cele asteptate.
     */
    @Test
    public void testInsert() {
        assertEquals("123456789", redBlackTree.getPhoneNumber("John"));
        assertEquals("987654321", redBlackTree.getPhoneNumber("Alice"));
        assertEquals("456123789", redBlackTree.getPhoneNumber("Bob"));
    }

    /**
     * Testeaza corectitudinea operatiei de stergere, verificand daca utilizatorul sters
     * nu mai are un numar de telefon asociat in arbore.
     */
    @Test
    public void testDelete() {
        redBlackTree.delete("Alice");
        assertNull(redBlackTree.getPhoneNumber("Alice"));
    }

    /**
     * Testeaza corectitudinea operatiei de cautare, verificand daca utilizatorii existenti
     * sunt gasiti, iar un utilizator inexistent nu este gasit.
     */
    @Test
    public void testSearch() {
        assertTrue(redBlackTree.search("John"));
        assertFalse(redBlackTree.search("Unknown"));
    }
}
