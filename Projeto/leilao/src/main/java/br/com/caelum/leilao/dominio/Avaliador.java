package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author roberto
 */
public class Avaliador {

    public double maiorDeTodos = Double.NEGATIVE_INFINITY;
    public double menorDeTodos = Double.POSITIVE_INFINITY;

    public void avalia(Leilao l) {
        for (Lance lances : l.getLances()) {
            if (lances.getValor() > maiorDeTodos) {
                maiorDeTodos = lances.getValor();
            }

            if (lances.getValor() < menorDeTodos) {
                menorDeTodos = lances.getValor();
            }

        }
    }

    public double calcularValorMedio(Leilao l) {
        int contador = 0;
        double somaValor = 0;
        double valorMedio = 0;
        for (Lance lances : l.getLances()) {
            contador++;
            somaValor += lances.getValor();
        }
        if (contador > 0) {
            return valorMedio = somaValor / contador;
        }
        return valorMedio;
    }

    public List<Lance> getMaioresValores(Leilao l) {
        List<Lance> maiores = new ArrayList<>(l.getLances());
        Collections.sort(maiores, new Comparator<Lance>() {
            @Override
            public int compare(Lance o1, Lance o2) {
                if (o1.getValor() < o2.getValor()) {
                    return 1;
                }
                if (o1.getValor() > o2.getValor()) {
                    return -1;
                }
                return 0;
            }
        });
        return maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());

    }

    public double getMaiorDeTodos() {
        return maiorDeTodos;
    }

    public double getMenorDeTodos() {
        return menorDeTodos;
    }

}
