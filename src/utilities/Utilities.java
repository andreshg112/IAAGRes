package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utilities {

    public static String realABinario(double real, int cantidadDecimales) {
        String binario;
        binario = real >= 0 ? "" : "-";
        double positivo = Math.abs(real);
        int parteEntera = (int) Math.floor(positivo);
        double parteDecimal = positivo - parteEntera;
        binario += Integer.toBinaryString(parteEntera);
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

    public static String subcadenaBinaria(double real, int cantidadDecimales) {
        String binario;
        binario = real >= 0 ? "0" : "1";
        double positivo = Math.abs(real);
        int parteEntera = (int) Math.floor(positivo);
        double parteDecimal = positivo - parteEntera;
        binario += Integer.toBinaryString(parteEntera);
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

    public static List<String> individuoBinario(List<Float> individuo, int cantidadDecimales) {
        List<String> cadenaBinaria = new ArrayList();
        for (Float subcadena : individuo) {
            String binario = subcadenaBinaria(subcadena, cantidadDecimales);
            cadenaBinaria.add(binario);
        }
        return cadenaBinaria;
    }

    public static List<List<Integer>> getDiagnosticos() {
        List<List<Integer>> diagnosticos = new ArrayList();
        File archivo;
        FileReader fr = null;
        BufferedReader br;
        try {
            archivo = new File("resfriado.csv");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                diagnosticos.add(arrayStringToInt(Arrays.asList(linea.split(";"))));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                System.out.println(e2);
            }
        }
        return diagnosticos;
    }
}
