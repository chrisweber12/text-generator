/**
 * This class is a map that implements its key-value pairs based on a HashNode
 * Its structure is an Array of LinkedLists of HashNodes.
 */
import java.util.LinkedList;


public class ArrayOfListsMap<K, V> {

	// Hashnode class to contain data
	public class HashNode {
		private K key;
		private V value;

		public HashNode(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	// Instance variables
	private LinkedList<HashNode>[] lists;
	private static final int TABLE_SIZE = 20000;

	// Constructor
	@SuppressWarnings("unchecked")
	public ArrayOfListsMap() {
		lists = new LinkedList[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++) {
			lists[i] = new LinkedList<HashNode>();
		}
	}

	// Add an entry
	public V put(K key, V value) {
		int hash = Math.abs(key.hashCode() % TABLE_SIZE); // Hash function
		for (HashNode node : lists[hash]) { // Loop through nodes in list for hash
			if (node.key.equals(key)) { // Overwrite the HashNode value
				V prevValue = node.value;
				node.value = value;
				return prevValue;
			}
		}
		// Hash key not in list, add new HashNode
		lists[hash].add(new HashNode(key, value));
		return null;
	}

	// Get an entry
	public V get(K key) {
		int hash = Math.abs(key.hashCode() % TABLE_SIZE); // Hash function
		for (HashNode node : lists[hash]) { // Loop through nodes in list for hash
			if (node.key.equals(key)) { // Get value from HashNode
				return node.value;
			}
		}
		// Hash key not in list
		return null;
	}


	// Check if HashNode with key exists
	public boolean containsKey(K key) {
		int hash = Math.abs(key.hashCode() % TABLE_SIZE); // Hashk function
		for (HashNode node : lists[hash]) { // Loop through nodes in list for hash
			if (node.key.equals(key)) { // Hash key exists
				return true;
			}
		}
		// Hash key does not exist
		return false;
	}
}
