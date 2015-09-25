package br.com.caelum.leilao.dominio;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author roberto
 */
public class AvaliadorTest {

    public Leilao l = null;

    @Before
    public void preparacao() {
        l = new Leilao("XBOX ONE");

        Usuario roberto = new Usuario("Roberto");
        Usuario cesar = new Usuario("Cesar");
        Usuario tadeu = new Usuario("Tadeu");

        l.propoe(new Lance(roberto, 300.00));
        l.propoe(new Lance(cesar, 250.00));
        l.propoe(new Lance(tadeu, 600.00));
        l.propoe(new Lance(tadeu, 100.00));
        l.propoe(new Lance(tadeu, 450.00));

    }

    /**
     * Teste do maior valor.
     */
    @Test
    public void testMaiorDeTodos() {
        Avaliador avaliador = new Avaliador();
        avaliador.avalia(l);
        double resultadoEsperado = 600.0;
        double resultado = avaliador.getMaiorDeTodos();
        assertEquals(resultadoEsperado, resultado, 0.00001);

    }

    /**
     * Teste do menor valor.
     */
    @Test
    public void testMenorDeTodos() {
        Avaliador avaliador = new Avaliador();
        avaliador.avalia(l);
        double resultadoEsperado = 100.0;
        double resultado = avaliador.getMenorDeTodos();
        assertEquals(resultadoEsperado, resultado, 0.00001);
    }

    /**
     * Teste do valor medio dos lances.
     */
    @Test
    public void testValorMedioLance() {
        Avaliador avaliador = new Avaliador();
        double resultadoEsperado = 340.0;
        double resultado = avaliador.calcularValorMedio(l);
        assertEquals(resultadoEsperado, resultado, 0.1);
    }

    /**
     * Teste do maior e menor valor com apenas um lance.
     */
    @Test
    public void testValorMaiorEMenorComUmLance() {
        Leilao le = new Leilao("Computador");

        Usuario roberto = new Usuario("Roberto");

        Lance lan = new Lance(roberto, 200.0);
        le.propoe(lan);

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(le);

        double resultadoEsperado = 200.0;

        assertEquals(resultadoEsperado, avaliador.getMaiorDeTodos(), 0.1);
        assertEquals(resultadoEsperado, avaliador.getMenorDeTodos(), 0.1);
    }

    /**
     * Teste do maior e menor valor com valores de lances aleatórios.
     */
    @Test
    public void testValorMaiorEMenorComValoresAleatorios() {
        Leilao le = new Leilao("Impressora");

        Usuario roberto = new Usuario("Roberto");
        Usuario daniela = new Usuario("Daniela");
        Usuario jose = new Usuario("Jose");
        Usuario rosa = new Usuario("Rosa");

        le.propoe(new Lance(roberto, 200.0));
        le.propoe(new Lance(daniela, 450.0));
        le.propoe(new Lance(jose, 120.0));
        le.propoe(new Lance(rosa, 630.0));
        le.propoe(new Lance(daniela, 700.0));
        le.propoe(new Lance(roberto, 230.0));

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(le);

        double resultadoMenorEsperado = 120.0;
        double resultadoMaiorEsperado = 700.0;

        assertEquals(resultadoMaiorEsperado, avaliador.getMaiorDeTodos(), 0.1);
        assertEquals(resultadoMenorEsperado, avaliador.getMenorDeTodos(), 0.1);
    }

    /**
     * Teste do maior e menor valor com valores decrescentes.
     */
    @Test
    public void testValorMaiorEMenorComValoresDecrescentes() {
        Leilao le = new Leilao("Impressora");

        Usuario roberto = new Usuario("Roberto");
        Usuario daniela = new Usuario("Daniela");
        Usuario jose = new Usuario("Jose");
        Usuario rosa = new Usuario("Rosa");

        le.propoe(new Lance(roberto, 400.0));
        le.propoe(new Lance(daniela, 300.0));
        le.propoe(new Lance(jose, 200.0));
        le.propoe(new Lance(rosa, 100.0));

        Avaliador avaliador = new Avaliador();
        avaliador.avalia(le);

        double resultadoMenorEsperado = 100.0;
        double resultadoMaiorEsperado = 400.0;

        assertEquals(resultadoMaiorEsperado, avaliador.getMaiorDeTodos(), 0.1);
        assertEquals(resultadoMenorEsperado, avaliador.getMenorDeTodos(), 0.1);
    }

    /**
     * Teste dos três maiores valores com 5 lances.
     */
    @Test
    public void testTresMaioresValores() {
        Avaliador avaliador = new Avaliador();
        List<Lance> lance = avaliador.getMaioresValores(l);

        assertEquals(3, lance.size());
        assertEquals(600.0, lance.get(0).getValor(), 0.00001);
        assertEquals(450.0, lance.get(1).getValor(), 0.00001);
        assertEquals(300.0, lance.get(2).getValor(), 0.00001);

    }

    /**
     * Teste dos dois maiores valores com 2 lances.
     */
    @Test
    public void testDoisMaioresValores() {
        Leilao le = new Leilao("Impressora");

        Usuario roberto = new Usuario("Roberto");
        Usuario daniela = new Usuario("Daniela");

        le.propoe(new Lance(roberto, 400.0));
        le.propoe(new Lance(daniela, 300.0));

        Avaliador avaliador = new Avaliador();
        List<Lance> lance = avaliador.getMaioresValores(le);

        assertEquals(2, lance.size());
        assertEquals(400.0, lance.get(0).getValor(), 0.00001);
        assertEquals(300.0, lance.get(1).getValor(), 0.00001);

    }

    /**
     * Teste sem nenhum lance.
     */
    @Test
    public void testSemLances() {
        Leilao le = new Leilao("Impressora");

        Usuario roberto = new Usuario("Roberto");
        Usuario daniela = new Usuario("Daniela");

        Avaliador avaliador = new Avaliador();
        List<Lance> lance = avaliador.getMaioresValores(le);

        assertTrue(lance.isEmpty());
        assertEquals(0, lance.size());

    }

}
