import java.util.LinkedList;

public class ArrayOfListsMap<K, V> {

	public class HashNode {
		private K key;
		private V value;

		public HashNode(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private static final int TABLE_SIZE = 20000;

	private LinkedList<HashNode>[] lists;

	@SuppressWarnings("unchecked")
	public ArrayOfListsMap() {
		lists = new LinkedList[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++) {
			lists[i] = new LinkedList<HashNode>();
		}
	}

	public V put(K key, V value) {
		int hash = Math.abs(key.hashCode() % TABLE_SIZE);
		for (HashNode node : lists[hash]) {
			if (node.key.equals(key)) {
				V prevValue = node.value;
				node.value = value;
				return prevValue;
			}
		}
		lists[hash].add(new HashNode(key, value));
		return null;
	}


	public V get(K key) {
		int hash = Math.abs(key.hashCode() % TABLE_SIZE);
		for (HashNode node : lists[hash]) {
			if (node.key.equals(key)) {
				return node.value;
			}
		}
		return null;
	}


	public boolean containsKey(K key) {
		int hash = Math.abs(key.hashCode() % TABLE_SIZE);
		for (HashNode node : lists[hash]) {
			if (node.key.equals(key)) {
				return true;
			}
		}
		return false;
	}
}
