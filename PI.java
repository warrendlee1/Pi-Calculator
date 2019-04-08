import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Subclass of the GameDriverV4. Creates and ArrayList of DARTS and displays
 * them at random locations on a Graphics window. Utilizing a mathematical
 * equation to approximate the value of Pi.
 * 
 * @author Warren Lee
 *
 */
public class PI extends GameDriverV4 {

	DART dart;
	ArrayList<DART> darts = new ArrayList<DART>();
	int numIn, radius, gamestate;
	double result;
	boolean isRunning;
	Font font;

	/**
	 * Class constructor initializing field variables.
	 */
	public PI() {
		numIn = 0;
		radius = 200;
		result = 0;
		gamestate = 0;
		isRunning = true;
	}

	/**
	 * Creates a splash page with name of project, author, and functionalities
	 * within the program. Sets different fonts for different texts within the
	 * splash page. When user presses enter, game state changes to 1, and animation
	 * begins
	 * 
	 * @param win is a Graphics2D object
	 */
	public void HomeScreen(Graphics2D win) {
		win.setColor(Color.BLACK);
		font = new Font("Arial", Font.BOLD, 70);
		win.setFont(font);
		win.drawString("Calculate Pi", 50, 100);
		font = new Font("Arial", Font.BOLD, 20);
		win.setFont(font);
		win.drawString("Warren Lee", 60, 130);
		font = new Font("Arial", Font.ITALIC, 30);
		win.setFont(font);
		win.drawString("press enter", 155, 400);
		font = new Font("Arial", Font.PLAIN, 15);
		win.setFont(font);
		win.drawString("How to Use: watch animation, press esc to pause, press r to restart", 30, 450);
		if (GameDriverV4.Keys[KeyEvent.VK_ENTER]) {
			gamestate = 1;
		}
	}

	/**
	 * Reset allows user to press 'r' to bring screen back to home page and restart
	 * the program animation. All values are reset to default settings, and game
	 * state is set to 0.
	 */
	public void reset() {
		if (GameDriverV4.Keys[KeyEvent.VK_R]) {
			gamestate = 0;
			darts = new ArrayList<DART>();
			numIn = 0;
		}
	}

	/**
	 * Pause method changes the value of global variable 'isRunning' to opposite
	 * value when the ESC key is pressed. Utilizes GameDriver methods.
	 */
	public void pause() {
		if (GameDriverV4.Keys[KeyEvent.VK_ESCAPE]) {
			isRunning = !isRunning;
		}
	}

	/**
	 * addDart adds a new object of the DART class into the ArrayList darts, setting
	 * it with a random x and y coordinates with a maximum of 500.
	 * 
	 * @param win is a Graphics2D object
	 */
	public void addDart(Graphics2D win) {
		Random r = new Random();
		dart = new DART(r.nextInt(500), r.nextInt(500));
		darts.add(dart);
	}

	/**
	 * draws every dart in the ArrayList darts, using the moveAndDraw method from
	 * the DART class.
	 * 
	 * @param win is a Graphics2D object
	 */
	public void displayDarts(Graphics2D win) {
		for (DART d : darts) {
			d.moveAndDraw(win);
		}
	}

	/**
	 * Calculates the value of pi from different numeric values. Calculates pi as
	 * the height of the window * width of window * probability of n * s. Places the
	 * approximated value in the global variable result.
	 * 
	 * @param n is an integer that is the probability that a randomly generated
	 *          point is less than radius of a circle.
	 * @param s is an integer that is the size of the ArrayList darts.
	 */
	public void calcPi(int n, int s) {
		result = ((500 * 500) / Math.pow(200, 2)) * ((double) n / s);
	}

	/**
	 * Retrieves the value of global variable result and prints in on the top left
	 * of the Graphics window. Utilizes different fonts and a black rectangle as a
	 * contrasting background.
	 * 
	 * @param win is a Graphics2D object
	 */
	public void displayPi(Graphics2D win) {
		win.setColor(Color.black);
		win.fillRect(0, 0, 146, 30);
		win.setColor(Color.WHITE);
		font = new Font("Arial", Font.PLAIN, 15);
		win.setFont(font);
		win.drawString("Pi: " + result, 4, 20);
	}

	/**
	 * Runs the reset and pause methods every iteration. Checks the game state
	 * values and runs HomeScreen if the value is 0, the animation if game state is
	 * 1, and also checks if the ESC button is pressed to pause the program.
	 */
	public void draw(Graphics2D win) {

		reset();
		pause();
		if (gamestate == 0) {
			HomeScreen(win);
		}
		if (gamestate == 1) {
			if (isRunning) {
				this.addDart(win);
				if (dart.isIn(radius)) {
					numIn++;
				}
			}
			this.displayDarts(win);
			this.calcPi(numIn, darts.size());
			this.displayPi(win);
		}
	}

	public static void main(String[] args) {
		PI pi = new PI();
		pi.start();
	}
}