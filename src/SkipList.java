import java.util.Random;

import student.TestableRandom;

public class SkipList<K extends Comparable<K>, V> {
	
	private SkipNode<K, V> head; 
	private int level; 
	private int size; 
	static private Random ran = new TestableRandom();
	
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
			for(int i = 0; i <= level; i++)
			{
				newHead.getForward()[i] = head.getForward()[i];
			}
			head = newHead; 
			level = newLevel;
		}
		
		SkipNode<K, V>[] update = new SkipNode[level + 1];
		SkipNode<K, V> x = head; 
		for(int i = level; i >= 0; i --)
		{
			while((x.getForward()[i] != null) && 
					x.getForward()[i].key().compareTo(key) < 0)
			{
				x = x.getForward()[i];
			}
			update[i] = x;
		} 
		
		x = new SkipNode<K, V>(key, value, newLevel);
		for (int i = 0; i <= newLevel; i++)
		{
			x.getForward()[i] = update[i].getForward()[i];
			update[i].getForward()[i] = x;
		}
		
		size++; 
	}
	
	public V remove(K key)
	{
		
		if(size == 0)
		{
			return null; 
		}
		
		SkipNode<K, V>[] update = new SkipNode[level + 1];
		
		SkipNode<K, V> x = head; 
		for(int i = level; i >= 0; i--)
		{
			while(x.getForward()[i] != null && x.getForward()[i].key().compareTo(key) < 0)
			{
				x = x.getForward()[i];
			}
			update[i] = x; 
		}
		
		if (update[0].getForward()[0].key().compareTo(key) != 0)
		{
			return null; 
		}
		
		x = update[0].getForward()[0];
		for(int i = level; i >= 0; i--)
		{
			update[i].getForward()[i] = x.getForward()[i];
		}
		
		size--; 
		return x.value(); 
	}
	
	public void dump()
	{
		SkipNode<K,V> x = head;
		System.out.println("Node has depth " + x.getForward().length + ", " + "Value (null)");
		x = x.getForward()[0];
		while(x != null)
		{
			System.out.println(x.toString());
			x = x.getForward()[0];
		}
		
		System.out.println("SkipList size is: " + size);
	}
}
