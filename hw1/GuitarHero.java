import synthesizer.GuitarString;

public class GuitarHero {
    private GuitarString[] stringlist;
    private String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public GuitarHero() {
        stringlist = new GuitarString[37];
        init();
    }
    public void init() {
        for (int i = 0; i < 37; i++) {
            stringlist[i] = new GuitarString(440 * Math.pow(2, (double) (i - 24) / 12.0));
        }
    }
    public void pluck(int i) {
        stringlist[i].pluck();
    }
    public String getKeyboard() {
        return keyboard;
    }
    public double getSample() {
        double sum = 0.0;
        for (int i = 0; i < 37; i++) {
            sum += stringlist[i].sample();
        }
        return sum;
    }
    public void tic() {
        for (int i = 0; i < 37; i++) {
            stringlist[i].tic();
        }
    }
}
