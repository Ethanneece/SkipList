
public class SkipNode<K extends Comparable<K>, V> {
	private K key;
	private V value; 
	private SkipNode<K, V>[] forward; 
	
	public K key()
	{
		return key;
	}
	
	public V value()
	{
		return value;
	}
	
	public SkipNode<K, V>[] getForward()
	{
		return forward;
	}
	
	@SuppressWarnings("unchecked")
	public SkipNode(K key, V value, int level)
	{
		this.key = key; 
		this.value = value; 
		forward = new SkipNode[level + 1];
		for(int i = 0; i < level; i++)
		{
			forward[i] = null; 
		}
	}
	
	public String toString() {
		return "Node has depth " + forward.length + ", " + "Value " + value.toString(); 
	}
}
