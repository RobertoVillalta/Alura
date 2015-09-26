package br.com.caelum.leilao.desafio;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roberto
 */
public class AnoBissextoTest {

    /**
     * Testar se de 4 em 4 anos é bissexto
     */
    @Test
    public void bissexto4Anos() {
        AnoBissexto ano = new AnoBissexto();
        boolean resultado = ano.ehBissexto(2012);

        assertTrue(resultado);
    }

    /**
     * Testar se de 100 em 100 anos não é bissexto
     */
    @Test
    public void bissexto100Anos() {
        AnoBissexto ano = new AnoBissexto();
        boolean resultado = ano.ehBissexto(2100);

        assertFalse(resultado);
    }

    /**
     * Testar se de 400 em 400 anos é bissexto
     */
    @Test
    public void bissexto400Anos() {
        AnoBissexto ano = new AnoBissexto();
        boolean resultado = ano.ehBissexto(2000);

        assertTrue(resultado);
    }

}
