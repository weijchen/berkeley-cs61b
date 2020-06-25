class Planet {
    double xxPos;   // current x position
    double yyPos;   // current y position
    double xxVel;   // current velocity in the x direction
    double yyVel;   // current velocity in the y direction
    double mass;
    String imgFileName; // the name of the file that corresponds to the image that depicts the planet
    public final double gForce = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt(Math.pow(xxPos - p.xxPos, 2) + Math.pow(yyPos - p.yyPos, 2));
    }

    public double calcForceExertedBy(Planet p) {
        return gForce * mass * p.mass / Math.pow(calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p) {
        double total_force = calcForceExertedBy(p);
        double xxDis = xxPos - p.xxPos;
        boolean isBigger = mass > p.mass;
        if (isBigger) return total_force * xxDis / calcDistance(p);
        else return -1 * total_force * xxDis / calcDistance(p);

    }

    public double calcForceExertedByY(Planet p) {
        double total_force = calcForceExertedBy(p);
        double yyDis = yyPos - p.yyPos;
        boolean isBigger = mass > p.mass;
        if (isBigger) return total_force * yyDis / calcDistance(p);
        else return -1 * total_force * yyDis / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netForce = 0;
        for (Planet p : planets) {
            if (!this.equals(p)) netForce += calcForceExertedByX(p);
        }
        return netForce;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForce = 0;
        for (Planet p : planets) {
            if (!this.equals(p)) netForce += calcForceExertedByY(p);
        }
        return netForce;
    }

    public void update(double time, double xForce, double yForce) {
        double accX = xForce / mass;
        double accY = yForce / mass;
        double newXXVel = xxVel + time * accX;
        double newYYVel = yyVel + time * accY;
        xxVel = newXXVel;
        yyVel = newYYVel;
        double newXXPos = xxPos + time * newXXVel;
        double newYYPos = yyPos + time * newYYVel;
        xxPos = newXXPos;
        yyPos = newYYPos;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}