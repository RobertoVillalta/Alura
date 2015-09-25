package br.com.caelum.leilao.desafio;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roberto
 */
public class PalindromoTest {

    /**
     * Teste metodo Palindromo para frase verdadeira.
     */
    @Test
    public void testPalindromoVerdadeiro() {
        String frase = "Socorram-me subi no onibus em Marrocos";
        Palindromo instance = new Palindromo();
        boolean resultado = instance.ehPalindromo(frase);
        assertTrue(resultado);
    }

    /**
     * Teste metodo Palindromo para frase falsa.
     */
    @Test
    public void testPalindromoFalso() {
        String frase = "Nao vai dar certo";
        Palindromo instance = new Palindromo();
        boolean resultado = instance.ehPalindromo(frase);
        assertFalse(resultado);
    }

}
