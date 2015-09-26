package br.com.caelum.leilao.dominio;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author roberto
 */
public class LanceTest {

    private Usuario roberto;

    @Before
    public void preparacao() {
        roberto = new Usuario("Roberto");
    }

    /**
     * Teste de exceção para valor de lançe igual a zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void lanceValorZero() {
        Lance lance = new Lance(roberto, 0);
    }

    /**
     * Teste de exceção para valor de lançe menor que zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void lanceValorMenorZero() {
        Lance lance = new Lance(roberto, -1);
    }

    /**
     * Teste para valor de lançe maoior que zero.
     */
    @Test
    public void lanceValorMaiorZero() {
        Lance lance = new Lance(roberto, 20);
        double valor = lance.getValor();

        assertEquals(20, valor, 0.1);
    }

}
