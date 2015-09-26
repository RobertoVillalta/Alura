package br.com.caelum.leilao.desafio;

/**
 *
 * @author roberto
 */
public class AnoBissexto {

    public boolean ehBissexto(int ano) {
        boolean retorno = false;
        if ((ano % 400) == 0) {
            retorno = true;
        } else if ((ano % 100) == 0) {
            retorno = false;
        } else if ((ano % 4) == 0) {
            retorno = true;
        }
        return retorno;
    }

}
