import synthesizer.GuitarString;

public class GuitarHero {
    private GuitarString[] StringList;
    private String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public GuitarHero() {
        StringList = new GuitarString[37];
        init();
    }
    public void init() {
        for (int i = 0; i < 37; i++) {
            StringList[i] = new GuitarString(440 * Math.pow(2, (double) (i - 24) / 12.0));
        }
    }
    public void pluck(int i) {
        StringList[i].pluck();
    }
    public String getKeyboard() {
        return keyboard;
    }
    public double getSample() {
        double sum = 0.0;
        for (int i = 0; i < 37; i++) {
            sum += StringList[i].sample();
        }
        return sum;
    }
    public void tic() {
        for (int i = 0; i < 37; i++) {
            StringList[i].tic();
        }
    }
}
