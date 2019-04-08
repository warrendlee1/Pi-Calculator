import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * Dart is a subclass Rectangle that holds a Color, a point, x coordinates, and
 * y coordinates. It draws a point of 3x3 and keeps its own location and keep
 * track if it lies within a cirle's radius.
 * 
 * @author Warren Lee
 *
 */
public class DART extends Rectangle {
	Color col;

	/**
	 * Class constructor depicting number of parameters
	 * 
	 * @param x is a value for an x coordinate.
	 * @param y is a value for an y coordinate.
	 */
	public DART(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Checks if distance from (250,250) to a randomly generated point is less than
	 * an integer r, a radius value. If the distance value is less than r, then it
	 * changes color to green, otherwise it remains black.
	 * 
	 * @param r is an integer radius value
	 * @return true if distance from (250,250) to most recently added dart's
	 *         coordinates is less than r.
	 */
	public boolean isIn(int r) {
		if (Math.hypot(x - 250, y - 250) < r) {
			col = Color.GREEN;
			return true;
		} else {
			col = Color.BLACK;
			return false;
		}
	}

	/**
	 * Draws a colored rectangle of dimensions 3 by 3.
	 * 
	 * @param win is a Graphics2D object
	 */
	public void moveAndDraw(Graphics2D win) {
		win.setColor(col);
		win.fillRect(this.x - 1, this.y - 1, 3, 3);

	}
}
