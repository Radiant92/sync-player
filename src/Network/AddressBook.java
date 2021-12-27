package Network;

import java.util.Collection;
import java.util.HashMap;

public class AddressBook {
    private HashMap<String, Node> nodes;

    public AddressBook() {
        this.nodes = new HashMap<>();
    }

    //identical identifiers will reset the node.
    public void addNode(String identifier) {
            nodes.put(identifier, new Node(identifier, 10));
    }

    public void removeNode(String identifier) {
        if (nodes.containsKey(identifier)) {
            nodes.remove(identifier);
        }
    }

    /**
     * @param identifier
     * @return node if node exists in the phonebook, else returns null.
     */
    public Node getNode(String identifier) {
        return nodes.get(identifier);
    }

    public Collection<Node> getAllNodes() {
        return nodes.values();
    }
}
