package br.com.caelum.leilao.desafio;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roberto
 */
public class MatematicaMalucaTest {

    public MatematicaMalucaTest() {
    }

    /**
     * Teste da conta maluca maior que 30.
     */
    @Test
    public void testContaMalucaMaior30() {
        int numero = 40;
        MatematicaMaluca mat = new MatematicaMaluca();
        int resultadoEsperado = 160;
        int resultado = mat.contaMaluca(numero);
        assertEquals(resultadoEsperado, resultado);
    }

    /**
     * Teste da conta maluca maior que 10 mas menor que 30.
     */
    @Test
    public void testContaMalucaMaior10() {
        int numero = 13;
        MatematicaMaluca mat = new MatematicaMaluca();
        int resultadoEsperado = 39;
        int resultado = mat.contaMaluca(numero);
        assertEquals(resultadoEsperado, resultado);
    }

    /**
     * Teste da conta maluca menor que 10.
     */
    @Test
    public void testContaMalucaMenor10() {
        int numero = 6;
        MatematicaMaluca mat = new MatematicaMaluca();
        int resultadoEsperado = 12;
        int resultado = mat.contaMaluca(numero);
        assertEquals(resultadoEsperado, resultado);
    }

}
