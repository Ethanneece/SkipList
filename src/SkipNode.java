
public class SkipNode<K extends Comparable<K>, V> {
	private K key;
	private V value; 
	private SkipNode<K, V>[] forward;

    /**
     *
     * @return key.
     */
	public K key()
	{
		return key;
	}

    /**
     *
     * @return value.
     */
	public V value()
	{
		return value;
	}

    /**
     *
     * @return forward.
     */
	public SkipNode<K, V>[] getForward()
	{
		return forward;
	}

    /**
     * Creates an object of SkipNode.
     * @param key being put into SkipNode.
     * @param value being put into SkipNode.
     * @param level how many pointers this has.
     */
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


    /**
     *
     * @return String representation of SkipNode.
     */
	public String toString() {
		return "Node has depth " + forward.length + ", " + "Value " + value.toString(); 
	}
}
