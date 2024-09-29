

/**
 * Celestial Body class for NBody
 * Modified from original Planet class
 * used at Princeton and Berkeley
 * @author ola
 *
 * If you add code here, add yourself as @author below
 *
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */

	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
		this.myXPos = xp;
		this.myYPos = yp;
		this.myXVel = xv;
		this.myYVel = yv;
		this.myMass = mass;
		this.myFileName = filename;
	}

	/**
	 *
	 * @return
	 */
	public double getX() {
		return myXPos;
	}

	/**
	 *
	 * @return
	 */
	public double getY() {
		return myYPos;
	}

	/**
	 * Accessor for the x-velocity
	 * @return the value of this object's x-velocity
	 */
	public double getXVel() {
		return myXVel;
	}
	/**
	 * Accessor for the y-velocity.
	 * @return value of this object's y-velocity
	 */
	public double getYVel() {
		return myYVel;
	}

	/**
	 *
	 * @return
	 */
	public double getMass() {
		return myMass;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		double deltaX = b.myXPos - this.myXPos;
		double deltaY = b.myYPos - this.myYPos;
		return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
	}

	public double calcForceExertedBy(CelestialBody b) {
		double G = 6.67*1e-11;
		double r = calcDistance(b);
		return (G * b.myMass * this.myMass) / (r*r);
	}

	public double calcForceExertedByX(CelestialBody b) {
		double deltaX = b.myXPos - this.myXPos;
		double F = calcForceExertedBy(b);
		double r = calcDistance(b);
		return (F*deltaX)/r;
	}

	public double calcForceExertedByY(CelestialBody b) {
		double deltaY = b.myYPos - this.myYPos;
		double F = calcForceExertedBy(b);
		double r = calcDistance(b);
		return (F*deltaY)/r;
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;
		for (CelestialBody b : bodies) {
			if (! b.equals(this)) {
				sum += calcForceExertedByX(b);
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for (CelestialBody b : bodies) {
			if (! b.equals(this)) {
				sum += calcForceExertedByY(b);
			}
		}
		return sum;
	}

	/**
	 * This is a mutator method, modifies state of a celestial body
	 * (position and velocity)
	 * @param deltaT the time-step used in updating
	 * @param xforce the force in the x-direction
	 * @param yforce the force in the y-direction
	 */
	public void update(double deltaT, 
			           double xforce, double yforce) {
		double ax = xforce / this.myMass;
		double ay = yforce / this.myMass;

		double nvx = this.myXVel + deltaT*ax;
		double nvy = this.myYVel + deltaT*ay;

		double nx = this.myXPos + deltaT*nvx;
		double ny = this.myYPos + deltaT*nvy;

		this.myXPos = nx;
		this.myYPos = ny;
		this.myXVel = nvx;
		this.myYVel = nvy;
	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
