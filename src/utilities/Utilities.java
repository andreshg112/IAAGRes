package utilities;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

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

    public static double funcionAptitud(ArrayList<Float> individuo, ArrayList<Integer> entradas) {
        double resultado = 0;
        for (int j = 1; j < entradas.size(); j++) {
            resultado += entradas.get(j) * individuo.get(j);
        }
        resultado -= individuo.get(0);
        return resultado;
    }

    public static ArrayList<Integer> arrayStringToInt(List<String> datos) {
        ArrayList<Integer> enteros = new ArrayList();
        for (String dato : datos) {
            enteros.add(Integer.parseInt(dato));
        }
        return enteros;
    }

    public static int evaluarIndividuo(ArrayList<Float> individuo, ArrayList<Integer> entradas) {
        return escalon(funcionAptitud(individuo, entradas));
    }

    public static int escalon(double valor) {
        return valor >= 0 ? 1 : 0;
    }
}
