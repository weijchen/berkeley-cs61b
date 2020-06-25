class NBody{
    private static int numberOfPlanets;
    private static double radiusOfUniverse;
    private static double xxPos;
    private static double yyPos;
    private static double xxVel;
    private static double yyVel;
    private static double mass;
    private static String nameOfFile;
    public static String imageToDraw = "images/starfield.jpg";


    public static double readRadius(String fileName) {
        In in = new In(fileName);

        numberOfPlanets = in.readInt();
        radiusOfUniverse = in.readDouble();
        return radiusOfUniverse;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        numberOfPlanets = in.readInt();
        radiusOfUniverse = in.readDouble();

        Planet[] planets = new Planet[numberOfPlanets];
        for (int i = 0; i < numberOfPlanets; i++) {
            xxPos = in.readDouble();
            yyPos = in.readDouble();
            xxVel = in.readDouble();
            yyVel = in.readDouble();
            mass = in.readDouble();
            nameOfFile = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, nameOfFile);
        }
        return planets;
    }

    public static void Draw() {
        StdDraw.setScale(-radiusOfUniverse, radiusOfUniverse);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);
    }

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();

        double t = 0;
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];
        while (t < T) {
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(t, xForces[i], yForces[i]);
            }

            Draw();
            for (int i = 0; i < planets.length; i++) {
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(150);
            t += dt;
        }
    }
}