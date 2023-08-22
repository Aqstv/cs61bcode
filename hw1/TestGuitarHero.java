public class TestGuitarHero {
    public static void main(String[] args) {
        GuitarHero gh = new GuitarHero();
        String keyboard = gh.getKeyboard();
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) <= 36 && keyboard.indexOf(key) >= 0) {
                    gh.pluck(keyboard.indexOf(key));
                }
            }
            StdAudio.play(gh.getSample());
            gh.tic();
        }
    }
}
