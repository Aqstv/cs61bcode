public class NBody {
    public static double readRadius(String path) {
        In in = new In(path);
        int n = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String path) {
        In in = new In(path);
        int n = in.readInt();
        double d = in.readDouble();
        Planet[] ps = new Planet[n];
        for(int i = 0;i < n;i ++) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String name = "images/"+in.readString();
            ps[i] = new Planet(xp,yp,xv,yv,m,name);
        }
        return ps;
    }
    public static double trans = 2.50e+10;
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double r = readRadius(filename);
        Planet[] p = NBody.readPlanets(filename);
        StdDraw.setXscale(-r,r);
        StdDraw.setYscale(-r,r);
        

        double t = 0;
        int n = p.length;
        while(t < T) {
            StdDraw.enableDoubleBuffering();
            double[] xForces = new double[n];
            double[] yForces = new double[n];
            for(int i = 0 ; i < n ; i ++) {
                xForces[i] = p[i].calcNetForceExertedByX(p);
                yForces[i] = p[i].calcNetForceExertedByY(p);
            }
            for(int i = 0 ; i < n ; i ++) {
                p[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(int i = 0 ; i < n ; i ++) {
                p[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",p[i].xxPos, p[i].yyPos, p[i].xxVel,p[i].yyVel, p[i].mass, p[i].imgFileName);   
        }
    }
}
