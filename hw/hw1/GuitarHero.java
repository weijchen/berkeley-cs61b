import synthesizer.StdAudio;
import synthesizer.StdDraw;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHero {
    private static final double standard = 440.0;
//    private static final double CONCERT_A = 440.0;
//    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    private static synthesizer.GuitarString[] stringList = new synthesizer.GuitarString[37];
//    private double[] CONCERT_LIST = new double[37];
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
//        synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
//        synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);
        for (int i = 0; i < stringList.length; i++) {
            stringList[i] = new synthesizer.GuitarString(standard * Math.pow(2, (i-24)/12));
        }
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int keyID = keyboard.indexOf(key);

                if (keyID == -1) {
                    throw new RuntimeException("Error input key!");
                }
                synthesizer.GuitarString sound = stringList[keyID];
                sound.pluck();
            }
            /* compute the superposition of samples */
            double sample = 0;
            for (synthesizer.GuitarString sound : stringList) {
                sample += sound.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (synthesizer.GuitarString sound : stringList) {
                sound.tic();
            }
        }
    }
}

