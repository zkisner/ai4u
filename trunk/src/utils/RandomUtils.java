package utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 * This is an extension for the {@link Random} file.
 * 
 * @author kreich
 */
public class RandomUtils extends Random {

	/**
	 * Picks a random element from the given {@link Collection}.
	 * @param <T> The type of the elements in the {@link Collection}.
	 * @param collection The {@link Collection} to pick a random element from.
	 * @return A random element from the {@link Collection}.
	 */
	public <T> T pickRandom(Collection<T> collection) {
		Iterator<T> iter = collection.iterator();
		int randIndex = nextInt(collection.size());
		
		for (int i = 0; i < randIndex - 1; i++) iter.next();
		return iter.next();
	}
	
	/**
	 * @param <E> The type of the Enum.
	 * @param e The enum's class.
	 * @return A random instance of the Enum.
	 */
	@SuppressWarnings("unchecked")
	public <E extends Enum<E>> E pickRandom(Class<E> e) {
		try {
			E[] values = (E[]) e.getMethod("values").invoke(null);
			return values[nextInt(values.length)];
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
