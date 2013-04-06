/**
 * 
 */
package org.gamelib;

import java.awt.Color;
import java.awt.Image;

/**
 * @author pwnedary
 * 
 */
public interface Graphics {

	public void setColor(Color c);

	/**
	 * Draws the specified image to the backend.
	 * @param img the image to be drawn
	 * @param dx1
	 * @param dy1
	 * @param dx2
	 * @param dy2
	 * @param sx1
	 * @param sy1
	 * @param sx2
	 * @param sy2
	 */
	public void drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2);

	public void drawRect(int x, int y, int width, int height);
	public void fillRect(int x, int y, int width, int height);
	
	public void drawString(String str, int x, int y);

}
