
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static final double G = 6.67 * 1e-11;

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
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = this.calcDistance(p);
        double F = G * (this.mass * p.mass) / (r * r);
        return F;
    }

    public double calcForceExertedByX(Planet p) {
        double F = this.calcForceExertedBy(p);
        double dx = this.xxPos - p.xxPos;
        double r = this.calcDistance(p);
        double Fx = F * dx / r;

        if (this.mass <= p.mass) {
            Fx = Fx * -1;
        }
        return Fx;
    }

    public double calcForceExertedByY(Planet p) {
        double F = this.calcForceExertedBy(p);
        double dy = this.yyPos - p.yyPos;
        double r = this.calcDistance(p);
        double Fy = F * dy / r;

        if (this.mass <= p.mass) {
            Fy = Fy * -1;
        }
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] p_list) {
        double ret = 0;
        /** if-loop version 1 */
        for (Planet p : p_list) {
            if (!this.equals(p)) ret += this.calcForceExertedByX(p);
        }
        /** if-loop version 2 */
        // for (int i = 0; i < p_list.length; i++) {
        //     if (!this.equals(p_list[i])) ret += this.calcForceExertedByX(p_list[i]);
        // }
        return ret;
    }

    public double calcNetForceExertedByY(Planet[] p_list) {
        double ret = 0;
        /** if-loop version 1 */
        for (Planet p : p_list) {
            if (!this.equals(p)) ret += this.calcForceExertedByY(p);
        }
        /** if-loop version 2 */
        // for (int i = 0; i < p_list.length; i++) {
        //     if (!this.equals(p_list[i])) ret += this.calcForceExertedByY(p_list[i]);
        // }
        return ret;
    }

    public void update(double dt, double Fx, double Fy) {
        double ax = Fx / this.mass;
        double ay = Fy / this.mass;
        this.xxVel += dt * ax;
        this.yyVel += dt * ay;
        this.xxPos += dt * xxVel;
        this.yyPos += dt * yyVel;
    }
}