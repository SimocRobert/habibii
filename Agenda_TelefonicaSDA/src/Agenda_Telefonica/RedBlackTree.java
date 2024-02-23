package Agenda_Telefonica;

import java.util.Set;
import java.util.TreeMap;
import java.util.Map;

/**
 * Aceasta clasa implementeaza un arbore de tip Red-Black pentru gestionarea contactelor dintr-o agenda telefonica.
 */
public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    /**
     * Constructor pentru initializarea unui arbore Red-Black.
     */
    public RedBlackTree() {
        this.root = null;
    }

    /**
     * Verifica daca un contact cu un anumit nume exista in arbore.
     *
     * @param name Numele contactului cautat.
     * @return `true` daca contactul exista, `false` altfel.
     */
    public boolean search(String name) {
        return getNode(root, name) != null;
    }

    /**
     * Clasa interna pentru reprezentarea unui nod in arborele Red-Black.
     */
    private class Node {
        String name;
        String phoneNumber;
        Node left, right;
        boolean color;

        /**
         * Constructor pentru initializarea unui nod.
         *
         * @param name         Numele contactului.
         * @param phoneNumber  Numarul de telefon asociat contactului.
         * @param color        Culoarea nodului (true = rosu, false = negru).
         */
        Node(String name, String phoneNumber, boolean color) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.color = color;
        }
    }

    /**
     * Insereaza un contact in arborele Red-Black.
     *
     * @param name         Numele contactului.
     * @param phoneNumber  Numarul de telefon asociat contactului.
     */
    public void insert(String name, String phoneNumber) {
        root = insert(root, name, phoneNumber);
        root.color = BLACK; // Asigura că rădăcina rămâne neagră
    }

    private Node insert(Node node, String name, String phoneNumber) {
        if (node == null) {
            return new Node(name, phoneNumber, RED);
        }

        int cmp = name.compareTo(node.name);

        if (cmp < 0) {
            node.left = insert(node.left, name, phoneNumber);
        } else if (cmp > 0) {
            node.right = insert(node.right, name, phoneNumber);
        } else {
            node.phoneNumber = phoneNumber;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    /**
     * Sterge un contact din arborele Red-Black.
     *
     * @param name Numele contactului de sters.
     */
    public void delete(String name) {
        root = delete(root, name);
        if (root != null) {
            root.color = BLACK; // Asigura că rădăcina rămâne neagră
        }
    }

    private Node delete(Node node, String name) {
        if (node == null) {
            return null;
        }

        int cmp = name.compareTo(node.name);

        if (cmp < 0) {
            node.left = delete(node.left, name);
        } else if (cmp > 0) {
            node.right = delete(node.right, name);
        } else {
            if (node.right == null) {
                return node.left;
            } else if (node.left == null) {
                return node.right;
            }

            Node minNode = findMin(node.right);
            node.name = minNode.name;
            node.phoneNumber = minNode.phoneNumber;
            node.right = deleteMin(node.right);
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    /**
     * Obtine numarul de telefon asociat unui contact.
     *
     * @param name Numele contactului.
     * @return Numarul de telefon asociat contactului sau `null` daca contactul nu exista.
     */
    public String getPhoneNumber(String name) {
        Node node = getNode(root, name);
        return (node != null) ? node.phoneNumber : null;
    }

    private Node getNode(Node node, String name) {
        while (node != null) {
            int cmp = name.compareTo(node.name);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    /**
     * Afiseaza continutul arborelui Red-Black.
     */
    public void display() {
        System.out.println("Red-Black Tree:");
        display(root);
        System.out.println();
    }

    private void display(Node node) {
        if (node != null) {
            display(node.left);
            System.out.println(node.name + ": " + node.phoneNumber);
            display(node.right);
        }
    }

    /**
     * Returneaza un set de toate numele contactelor din arbore, sortate.
     *
     * @return Setul de nume al contactelor.
     */
    public Iterable<String> getAllNames() {
        TreeMap<String, String> treeMap = new TreeMap<>();
        getAllNames(root, treeMap);
        return treeMap.keySet();
    }

    private void getAllNames(Node node, Map<String, String> result) {
        if (node != null) {
            getAllNames(node.left, result);
            result.put(node.name, node.phoneNumber);
            getAllNames(node.right, result);
        }
    }
}
