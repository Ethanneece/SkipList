import java.util.Random;

public class SkipList<K extends Comparable<K>, V> {
	
	private SkipNode<K, V> head; 
	private int level; 
	private int size; 
	static private Random ran = new Random();
	
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
			while((x.getForward()[i] != null) && 
					x.getForward()[i].key().compareTo(key) < 0)
			{
				x = x.getForward()[i];
			}
		}
		
		x = x.getForward()[0];
		if (x != null && (x.key().compareTo(key) == 0))
		{
			return x.value(); 
		}
		
		return null; 
	}
	
	int randomLevel()
	{
		int level; 
		for(level = 0; Math.abs(ran.nextInt()) % 2 == 0; level++)
		{
			
		}
		return level;
	}
	
	@SuppressWarnings("unchecked")
	public void insert(K key, V value)
	{
		int newLevel = randomLevel(); 
		
		if(newLevel > level)
		{
			SkipNode<K, V> newHead = new SkipNode<K,V>(null, null, newLevel);
			for(int i = 0; i < level; i++)
			{
				newHead.getForward()[i] = head.getForward()[i];
			}
			head = newHead; 
			level = newLevel;
		}
		
		SkipNode<K, V>[] update = new SkipNode[level + 1];
		SkipNode<K, V> x = head; 
		for(int i = level; i > 0; i --)
		{
			while((x.getForward()[i] != null) && 
					x.getForward()[i].key().compareTo(key) < 0)
			{
				x = x.getForward()[i];
			}
			update[i].getForward()[i] = x;
		} 
		
		x = new SkipNode<K, V>(key, value, newLevel)
		for (int i = 0; i <= newLevel; i++)
		{
			x.getForward()[i] = update[i].getForward()[i];
		}
		
		size++; 
	}
	
	public V remove(K key)
	{
		SkipNode<K, V> update = new SkipNode<K, V>[level];
		g
	}
}
