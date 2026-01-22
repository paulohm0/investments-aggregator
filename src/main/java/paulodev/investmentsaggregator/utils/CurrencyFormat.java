package paulodev.investmentsaggregator.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormat {
    private static final NumberFormat MOEDA_BR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public static String formactToBRL(double valor) {
        return MOEDA_BR.format(valor);
    }
}
