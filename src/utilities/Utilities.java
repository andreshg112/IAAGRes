package utilities;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class Utilities {

    public static String realABinario(double real, int cantidadDecimales) {
        String binario;
        int parteEntera = (int) Math.floor(real);
        double parteDecimal = real - parteEntera;
        binario = Integer.toBinaryString(parteEntera);
        binario += cantidadDecimales > 0 ? "." : "";
        double parteDecimalCiclo = parteDecimal;
        for (int i = 0; i < cantidadDecimales; i++) {
            double parteRealCiclo = parteDecimalCiclo * 2;
            int parteEnteraCiclo = (int) Math.floor(parteRealCiclo);
            binario += Integer.toString(parteEnteraCiclo);
            parteDecimalCiclo = parteRealCiclo - parteEnteraCiclo;
        }
        return binario;
    }
    
    public static ArrayList<Float> generarIndividuo(int subcadenas) {
        ArrayList<Float> individuo = new ArrayList();
        DecimalFormatSymbols punto = DecimalFormatSymbols.getInstance();
        punto.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.#", punto);
        df.setRoundingMode(RoundingMode.CEILING);
        for (int i = 0; i < subcadenas; i++) {
            float numero = (float) (Math.random() - Math.random());
            float numeroGenerado = Float.parseFloat(df.format(numero));
            individuo.add(numeroGenerado);
        }
        return individuo;
    }
}
