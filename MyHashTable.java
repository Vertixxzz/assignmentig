public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        this(11);
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int M) {
        this.M = M;
        chainArray = (HashNode<K, V>[]) new MyHashNode[M];
        size = 0;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        for (HashNode<K, V> node = head; node != null; node = node.next) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        HashNode<K, V> node = new HashNode<>(key, value);
        node.next = head;
        chainArray[index] = node;
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        for (HashNode<K, V> node = chainArray[index]; node != null; node = node.next) {
            if (node.key.equals(key)) return node.value;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        HashNode<K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev != null) prev.next = node.next;
                else chainArray[index] = node.next;
                size--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            for (HashNode<K, V> node = chainArray[i]; node != null; node = node.next) {
                if (node.value.equals(value)) return true;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            for (HashNode<K, V> node = chainArray[i]; node != null; node = node.next) {
                if (node.value.equals(value)) return node.key;
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public int[] getBucketSizes() {
        int[] buckets = new int[M];
        for (int i = 0; i < M; i++) {
            int cnt = 0;
            for (HashNode<K, V> node = chainArray[i]; node != null; node = node.next) cnt++;
            buckets[i] = cnt;
        }
        return buckets;
    }
}
