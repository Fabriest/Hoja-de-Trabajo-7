import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTest {

    private BinaryTree<Association<String, String>> buildTree() {
        BinaryTree<Association<String, String>> tree = new BinaryTree<>();
        tree.insert(new Association<>("house", "casa"));
        tree.insert(new Association<>("dog", "perro"));
        tree.insert(new Association<>("woman", "mujer"));
        tree.insert(new Association<>("homework", "tarea"));
        tree.insert(new Association<>("town", "pueblo"));
        return tree;
    }


    @Test
    void insertOneElement_foundAfterInsert() {
        BinaryTree<Association<String, String>> tree = new BinaryTree<>();
        tree.insert(new Association<>("house", "casa"));

        Association<String, String> result = tree.search(new Association<>("house", ""));
        assertNotNull(result);
    }

    @Test
    void insertOneElement_correctValue() {
        BinaryTree<Association<String, String>> tree = new BinaryTree<>();
        tree.insert(new Association<>("house", "casa"));

        Association<String, String> result = tree.search(new Association<>("house", ""));
        assertEquals("casa", result.getValue());
    }

    @Test
    void insertMultipleElements_allFound() {
        BinaryTree<Association<String, String>> tree = buildTree();

        assertNotNull(tree.search(new Association<>("dog", "")));
        assertNotNull(tree.search(new Association<>("woman", "")));
        assertNotNull(tree.search(new Association<>("homework", "")));
        assertNotNull(tree.search(new Association<>("town", "")));
    }

    @Test
    void insertDuplicate_keepsOriginalValue() {
        BinaryTree<Association<String, String>> tree = new BinaryTree<>();
        tree.insert(new Association<>("dog", "perro"));
        tree.insert(new Association<>("dog", "can")); // duplicate key

        Association<String, String> result = tree.search(new Association<>("dog", ""));
        assertEquals("perro", result.getValue());
    }


    @Test
    void searchExistingElement_returnsCorrectValue() {
        BinaryTree<Association<String, String>> tree = buildTree();

        Association<String, String> result = tree.search(new Association<>("town", ""));
        assertNotNull(result);
        assertEquals("pueblo", result.getValue());
    }

    @Test
    void searchMissingElement_returnsNull() {
        BinaryTree<Association<String, String>> tree = buildTree();

        Association<String, String> result = tree.search(new Association<>("car", ""));
        assertNull(result);
    }

    @Test
    void searchEmptyTree_returnsNull() {
        BinaryTree<Association<String, String>> tree = new BinaryTree<>();

        Association<String, String> result = tree.search(new Association<>("hello", ""));
        assertNull(result);
    }

    @Test
    void searchUppercase_notFoundBecauseCaseSensitive() {
        BinaryTree<Association<String, String>> tree = buildTree();

        Association<String, String> result = tree.search(new Association<>("House", ""));
        assertNull(result);
    }
}