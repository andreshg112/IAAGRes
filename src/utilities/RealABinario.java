package utilities;

public class RealABinario {

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
}
