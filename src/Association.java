public class Association<K extends Comparable <K>, V> implements Comparable<Association<K, V>>{
    private K key;
    private V value;

    public Association(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getkey(){
        return key;
    }

    public V getvalue(){
        return value;
    }

    @Override
    public int compareTo(Association<K, V> other){
        return this.key.compareTo(other.key);
    }

    @Override
    public String toString(){
        return "(" + key + ", " + value + ")";
    }
    
}
