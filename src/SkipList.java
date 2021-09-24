import java.util.ArrayList;
import java.util.Random;

import student.TestableRandom;

public class SkipList<K extends Comparable<K>, V extends Comparable<V>> {
	
	private SkipNode<K, V> head; 
	private int level; 
	private int size; 
	static private Random ran = new TestableRandom();

	/**
	 * Creates an Object of SkipList.
	 */
	public SkipList()
	{
		head = new SkipNode<>(null, null, 0);
		level = -1; 
		size = 0; 
	}

	/**
	 * Finds a Value from a given key in the SkipList.
	 * @param key thing being searched for.
	 * @return value if it exist, null if not.
	 */
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
	
	private int randomLevel()
	{
		int level; 
		for(level = 0; Math.abs(ran.nextInt()) % 2 == 0; level++)
		{
			
		}
		return level;
	}

	/**
	 * Inserts SkipNode into the SkipList.
	 * @param key sorts based on this.
	 * @param value thing being inserted.
	 */
	@SuppressWarnings("unchecked")
	public void insert(K key, V value)
	{
		int newLevel = randomLevel(); 
		
		if(newLevel > level)
		{
			SkipNode<K, V> newHead = new SkipNode(null, null, newLevel);
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
		
		x = new SkipNode(key, value, newLevel);
		for (int i = 0; i <= newLevel; i++)
		{
			x.getForward()[i] = update[i].getForward()[i];
			update[i].getForward()[i] = x;
		}
		
		size++; 
	}

	/**
	 * Remoevs a SkipNode from the skipList based on key.
	 * @param key the thing being searched for.
	 * @return the value if the key, value pair exist, null if not.
	 */
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
		
		if (update[0].getForward()[0] == null || update[0].getForward()[0].key().compareTo(key) != 0)
		{
			return null; 
		}
		
		x = update[0].getForward()[0];
		for(int i = x.getForward().length - 1; i >= 0; i--)
		{
			update[i].getForward()[i] = x.getForward()[i];
		}
		
		size--; 
		return x.value(); 
	}

	/**
	 * Removes a key,value pair from the skipList if it exist.
	 * @param value the value being searched for.
	 * @param i i.
	 * @return the value if it exists. If it doesn't exist null.
	 */
	public V remove(V value, int i)
	{
		SkipNode<K, V> leader = head;
		leader = leader.getForward()[0];

		while(leader != null)
		{
			if(leader.value().compareTo(value) == 0)
			{
				return remove(leader.key());
			}
			leader = leader.getForward()[0];
		}

		return null;
	}

	/**
	 * Searches if the values in the SkipList fall into value.
	 * @param value thing that is being searched for.
	 * @return an arraylist containing values that matched well with the param
	 * 	value.
	 */
	public ArrayList<V> regionSearch(V value)
	{
		SkipNode<K, V> x = head;

		x = x.getForward()[0];

		ArrayList<V> rtn = new ArrayList<>();

		while(x != null)
		{
			if ( x.value().compareTo(value) >= 0)
			{
				rtn.add(x.value());
			}
			x = x.getForward()[0];
		}

		return rtn;
	}

	/**
	 * gets all the intersections in the SkipList between the values in it.
	 * @return an ArrayList containing the intersections.
	 */
	public ArrayList<V> intersections()
	{
		ArrayList<V> rtn = new ArrayList<>();

		SkipNode<K, V> x = head;
		x = x.getForward()[0];

		while(x != null)
		{
			SkipNode<K, V> values = x.getForward()[0];
			while(values != null)
			{
				if(x.value().compareTo(values.value()) >= 0)
				{
					rtn.add(x.value());
					rtn.add(values.value());
				}
				values= values.getForward()[0];
			}
			x = x.getForward()[0];
		}

		return rtn;
	}

	/**
	 * Searches for multiple keys by stopping before the first key is found
	 * 	and then keeps going through the SkipList until a key is not found.
	 *
	 * @param key is the thing being searched for.
	 * @return an ArrayList containing all the values of the keys
	 * 	that were found in the Skiplist.
	 */
	public ArrayList<V> search(K key)
	{
		ArrayList<V> rtn = new ArrayList<>();

		if(size == 0)
		{
			return rtn;
		}


		SkipNode<K, V> x = head;
		for(int i = level; i >= 0; i--)
		{
			while(x.getForward()[i] != null && x.getForward()[i].key().compareTo(key) < 0)
			{
				x = x.getForward()[i];
			}
		}

		if(x.getForward()[0] == null || x.getForward()[0].key().compareTo(key) != 0)
		{
			return rtn;
		}

		x = x.getForward()[0];
		while(x != null && x.key().compareTo(key) == 0)
		{
			rtn.add(x.value());
			x = x.getForward()[0];
		}

		return rtn;
	}

	/**
	 * Outputs a print of the SKipList current elements.
	 * Uses the Object toString method to represent both
	 * 	the keys and values of the
	 */
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
