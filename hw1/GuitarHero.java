import edu.princeton.cs.algs4.StdAudio;
import synthesizer.GuitarString;

public class GuitarHero {

    private static final String KEYBOARD =  "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {

        GuitarString[] concert = new GuitarString[37];
        for (int i = 0; i < concert.length; i++) {
            concert[i] = new GuitarString(440.0 * Math.pow(2, (double) (i - 24) / 12));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                if (index != -1) {
                    concert[index].pluck();
                }
            }

            double sample = 0;
            for (int i = 0; i < concert.length; i++) {
                sample += concert[i].sample();
            }

            StdAudio.play(sample);

            for (GuitarString s : concert) {
                s.tic();
            }
        }
    }
}
