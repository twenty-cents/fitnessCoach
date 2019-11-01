package fr.simplon.devweb2019.fitnessCoach.common;

public class FloatHelper {

    /**
     * Méthode utilitaire honteusement pompée sur google pour arrondir un float
     *
     * @param value : valeur à arrondir
     * @param scale : précision
     * @return      : valeur arrrondie
     */
    public static float round(float value, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++) {
            pow *= 10;
        }
        float tmp = value * pow;
        float tmpSub = tmp - (int) tmp;

        return ( (float) ( (int) (
                value >= 0
                        ? (tmpSub >= 0.5f ? tmp + 1 : tmp)
                        : (tmpSub >= -0.5f ? tmp : tmp - 1)
        ) ) ) / pow;

    }
}
