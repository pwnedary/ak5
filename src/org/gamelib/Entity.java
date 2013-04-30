/**
 * 
 */
package org.gamelib;

import org.gamelib.backend.Graphics;


/**
 * An entity with a coordinate in a 2d space.
 * 
 * @author Axel
 */
public interface Entity {
	
	public void update(float delta);
	public void draw(Graphics g, float delta);
	
	/**
	 * @author Axel
	 * @return the x-axis coordinate.
	 */
	public float getX();

	/**
	 * @author Axel
	 * @param the new x-axis coordinate.
	 */
	public void setX(float x);

	/**
	 * @author Axel
	 * @return the y-axis coordinate.
	 */
	public float getY();

	/**
	 * @author Axel
	 * @param the new y-axis coordinate.
	 */
	public void setY(float y);

	/**
	 * @author Axel
	 * @return the last x-axis coordinate.
	 */
	public float getLastX();

	/**
	 * @author Axel
	 * @param the last new x-axis coordinate.
	 */
	public void setLastX(float lastX);

	/**
	 * @author Axel
	 * @return the last x-axis coordinate.
	 */
	public float getLastY();

	/**
	 * @author Axel
	 * @param the last new x-axis coordinate.
	 */
	public void setLastY(float lastY);

	/**
	 * @author Axel
	 * @return the speed traveling the x-axis coordinate.
	 */
	public float getDX();

	/**
	 * @author Axel
	 * @param the new speed traveling the x-axis coordinate.
	 */
	public void setDX(float hspeed);

	/**
	 * @author Axel
	 * @return the speed traveling the y-axis coordinate.
	 */
	public float getDY();

	/**
	 * @author Axel
	 * @param the new speed traveling the y-axis coordinate.
	 */
	public void setDY(float vspeed);
}
