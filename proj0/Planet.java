public class Planet {
    public double xxPos,yyPos,xxVel,yyVel,mass;
    public String imgFileName;
    private static final double G = 6.6743e-11;
    public Planet(double xP, double yP, double xV,double yV, double m, String img) {
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
        return Math.pow((xxPos-p.xxPos)*(xxPos-p.xxPos)+(yyPos-p.yyPos)*(yyPos-p.yyPos),0.5);
    }

    public double calcForceExertedBy(Planet p) {
        double dis = this.calcDistance(p);
        return G*mass*p.mass/(dis*dis);
    }

    public double calcForceExertedByX(Planet p) {
        if (p == this) return 0;
        return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        if (p == this) return 0;
        return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] all) {
        double sum = 0;
        for(Planet i : all) {
            sum += calcForceExertedByX(i);
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] all) {
        double sum = 0;
        for(Planet i : all) {
            sum += calcForceExertedByY(i);
        }
        return sum;
    }

    public void update(double dt , double fx , double fy) {
        double ax = fx/mass;
        double ay = fy/mass;
        xxVel = xxVel + ax*dt;
        yyVel = yyVel + ay*dt;
        xxPos = xxPos + xxVel*dt;
        yyPos = yyPos + yyVel*dt;
    }

    public void draw() {
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }














}