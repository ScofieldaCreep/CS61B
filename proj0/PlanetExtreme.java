public class PlanetExtreme {
	
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	private static final double G = 6.67e-11;
	// It is good to declare any constants as a ‘static final’ variable in your class, 
	// and to use that variable anytime you wish to use the constant.

	// 构造instance的时候，如果变量名和上面一样，需要使用‘this’
	// https://blog.csdn.net/weixin_42386014/article/details/81138684
	public PlanetExtreme(double xP, double yP, double xV, double yV, double m, String img) {
	/* 	传入Planet的参数 */
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public PlanetExtreme(Planet p) {
	/*	将传入的参数用来构造Planet的实例 */
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
	}

    public double calcDistance(PlanetExtreme supplied) {
		/*	calculates the distance between two planets:
			take in a single planet.	*/
		double xxDiff = this.xxPos - supplied.xxPos;
		double yyDiff = this.yyPos - supplied.yyPos;
		return Math.sqrt(Math.pow(xxDiff, 2) + Math.pow(yyDiff, 2));
	}

	public double calcForceExertedBy(PlanetExtreme supplied) {
		// double G = 6.67e-11;  // 这一行也可以写在class最上面，用private static final
		double force = G * this.mass * supplied.mass / Math.pow(calcDistance(supplied), 2);
		return force;
	}

	public double calcForceExertedByX(PlanetExtreme supplied) {
		double force = calcForceExertedBy(supplied);
		double distance = calcDistance(supplied);
		return (supplied.xxPos - this.xxPos) * force / distance;
	}

	public double calcForceExertedByY(PlanetExtreme supplied) {
		double force = calcForceExertedBy(supplied);
		double distance = calcDistance(supplied);
		return (supplied.yyPos - this.yyPos) * force / distance;
	}

	public double calcNetForceExertedByX(PlanetExtreme[] all) {
		double force_x = 0;  // 不像C默认初始化为0，java必须要进行初始化
		for (PlanetExtreme sample : all) {
			if (sample.equals(this)) {
				continue;
			}
			force_x += calcForceExertedByX(sample);
		}
		return force_x;
	}

	public double calcNetForceExertedByY(PlanetExtreme[] all) {
		double force_y = 0;
		for (PlanetExtreme sample : all) {
			if (sample.equals(this)) {
				continue;
			}
			force_y += calcForceExertedByY(sample);
		}
		return force_y;
	}

	public void update(double dt, double fX, double fY) {
	//	Update the planet's position and velocity instance variables.
	//	Does not return anything.
		double a_x = fX / this.mass;
		double a_y = fY / this.mass;
		this.xxVel += a_x * dt;
		this.yyVel += a_y * dt;
		this.xxPos += this.xxVel * dt;
		this.yyPos += this.yyVel * dt;
	}

	public void update_W(){
		this.yyPos += 10e9;

	}


	public void update_A(){  //LEFT 
		this.xxPos -= 10e9;

	}

	public void update_D(){  // RIGHT
		this.xxPos += 10e9;

	}


	public void update_S(){  //DOWN
		this.yyPos -= 10e9;

	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
	


}
