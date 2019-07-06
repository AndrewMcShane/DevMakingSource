package DataStructures;

public class SimpleHashing {

	// Number of buckets by default in the list.
	private static final int BUCKETS_DEFAULT = 16;
	// Array of buckets.
	private HashNode[] buckets;
	// The actual number of buckets.
	private int numBuckets;
	// number of elements int he hash table.
	private int size;
	
	// A linked-list type class that holds the keys and values of the hash table.
	private static class HashNode {
		String key;
		String value;
		HashNode next;
		
		public HashNode(String key, String value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
		
		// Bonus constructor that allows for assigning the next hash node.
		public HashNode(String key, String value, HashNode next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
	
	// Parameter-less constructor.
	public SimpleHashing() {
		this(BUCKETS_DEFAULT);
	}
	
	// Constructor with defined number of buckets for the hash table.
	public SimpleHashing(int numBuckets) {
		this.numBuckets = numBuckets;
		buckets = new HashNode[numBuckets];
	}
	
	public boolean isEmpty() {
		return size <= 0;
	}
	
	private int getHash(String key) {
		// Use the hashCode function of String, mod the size of the list.
		// using a shift mask to ensure the value is positive (and faster than Math.abs)
		return (key.hashCode() & 0x7fffffff) % numBuckets;
	}
	
	public void put(String key, String value) {
		int bucket = getHash(key);
		HashNode newNode = new HashNode(key, value);
		if(buckets[bucket] == null) {
			buckets[bucket] = newNode;
			size++;
		} else {
			HashNode tmp = buckets[bucket];
			while(tmp.next != null) {
				if(tmp.key.equals(key)) {
					tmp.value = value;
					return;
				}
				tmp = tmp.next;
			}
			tmp.next = newNode;
			size++;
		}
		return;
	}
	
	public String get(String key) {
		int bucket = getHash(key);
		HashNode tmp = buckets[bucket];
		while(tmp != null) {
			if(tmp.key.equals(key)) {
				return tmp.value;
			}
			tmp = tmp.next;
		}
		return null;
	}
	
	public boolean contains(String key) {
		int bucket = getHash(key);
		HashNode tmp = buckets[bucket];
		while(tmp != null) {
			if(tmp.key.equals(key)) {
				return true;
			}
			tmp = tmp.next;
		}
		return false;
	}
	
	public void remove(String key) {
		int bucket = getHash(key);
		if(buckets[bucket] == null) {
			return;
		}
		HashNode tmp = buckets[bucket];
		if(tmp.key.equals(key)) {
			buckets[bucket] = buckets[bucket].next;
			size--;
			return;
		}
		while(tmp.next != null) {
			if(tmp.next.key.equals(key)) {
				tmp.next = tmp.next.next;
				size--;
				return;
			}
			tmp = tmp.next;
		}
	}
	
	// Clears all the keys and values on the hash table.
	public void clear() {
		buckets = new HashNode[numBuckets];
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		StringBuilder res = new StringBuilder();
		int count = 0;
		res.append("{ ");
		for(int i = 0; i < numBuckets; i++) {
			if(buckets[i] != null) {
				HashNode tmp = buckets[i];
				while(tmp != null) {
					res.append(tmp.key + ": " + tmp.value);
					count++;
					if(count < size) {
						res.append(", ");
					}
					tmp = tmp.next;
				}	
			}
		}	
		res.append(" }");
		return res.toString();
	}
	
	public static void main(String[] args) {
		SimpleHashing hashTable = new SimpleHashing(32);
		hashTable.put("Hello", "World");
		hashTable.put("Goodbye", "Cruel world");
		hashTable.put("Wonderful", "Weather");
		hashTable.put("Who's", "There");
		hashTable.put("It's a'", "Me");
		
		System.out.println(hashTable.toString());
		System.out.println(hashTable.contains("Goodbye"));
		hashTable.remove("Goodbye");
		System.out.println(hashTable.contains("Goodbye"));
		
		System.out.println(hashTable.toString());
	}

}
