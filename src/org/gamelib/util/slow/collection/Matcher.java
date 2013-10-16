/**
 * 
 */
package org.gamelib.util.slow.collection;

/**
 * @author Axel
 */
public interface Matcher<T> {
	/** @return <code>true</code> if <var>obj</var> matches, otherwise <code>false</code>. */
	public boolean matches(Object obj);
}