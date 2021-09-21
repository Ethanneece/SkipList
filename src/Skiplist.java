
public class SkipList<K extends Comparable<K>, V> {
	
	private SkipNode<K, V> head; 
	private int level; 
	private int size; 
	
	public SkipList()
	{
		head = new SkipNode<K,V>(null, null, 0);
		level = -1; 
		size = 0; 
	}
	
	public V find(K key)
	{
		SkipNode<K, V> x = head; 
		for(int i = level; i > 0; i --)
		{
			while((x.getForward[i] != null) && 
					x.getForward()[i].key().compareTo(key) < 0)
			{
				
			}
		}
	}
}
