/**
 * 
 */
package ak5.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A pool of objects that can be reused to avoid allocation.
 * 
 * @author pwnedary
 * 
 */
public abstract class Pool<T> {

	public final int max;

	private final List<T> freeObjects;

	/** Creates a pool with an initial capacity of 16 and no maximum. */
	public Pool() {
		this(16, Integer.MAX_VALUE);
	}

	/** Creates a pool with the specified initial capacity and no maximum. */
	public Pool(int initialCapacity) {
		this(initialCapacity, Integer.MAX_VALUE);
	}

	/** @param max The maximum number of free objects to store in this pool. */
	public Pool(int initialCapacity, int max) {
		freeObjects = new ArrayList<T>(initialCapacity);
		this.max = max;
	}

	protected abstract T newObject();

	/**
	 * Returns an object from this pool. The object may be new (from
	 * {@link #newObject()}) or reused (previously {@link #free(Object) freed}).
	 */
	public T obtain() {
		return freeObjects.size() == 0 ? newObject() : freeObjects.remove(freeObjects.size());
	}

	/**
	 * Puts the specified object in the pool, making it eligible to be returned
	 * by {@link #obtain()}. If the pool already contains {@link #max} free
	 * objects, the specified object is reset but not added to the pool.
	 */
	public void free(T object) {
		if (object == null)
			throw new IllegalArgumentException("object cannot be null.");
		if (freeObjects.size() < max)
			freeObjects.add(object);
		if (object instanceof Poolable)
			((Poolable) object).reset();
	}

	/**
	 * Puts the specified objects in the pool. Null objects within the array are
	 * silently ignored.
	 * 
	 * @see #free(Object)
	 */
	public void freeAll(List<T> objects) {
		if (objects == null)
			throw new IllegalArgumentException("object cannot be null.");
		for (int i = 0; i < objects.size(); i++) {
			T object = objects.get(i);
			if (object == null)
				continue;
			if (freeObjects.size() < max)
				freeObjects.add(object);
			if (object instanceof Poolable)
				((Poolable) object).reset();
		}
	}

	/** Removes all free objects from this pool. */
	public void clear() {
		freeObjects.clear();
	}

	/**
	 * Objects implementing this interface will have {@link #reset()} called
	 * when passed to {@link #free(Object)}.
	 */
	static public interface Poolable {
		/**
		 * Resets the object for reuse. Object references should be nulled and
		 * fields may be set to default values.
		 */
		public void reset();
	}

}
