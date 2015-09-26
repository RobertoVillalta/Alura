package br.com.caelum.leilao.dominio;

import br.com.caelum.leilao.builder.LeilaoBuilder;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author roberto
 */
public class AvaliadorTest {

    public Leilao l = null;
    Avaliador avaliador = null;
    Usuario roberto = null;
    Usuario cesar = null;
    Usuario tadeu = null;
    Usuario daniela = null;
    Usuario jose = null;
    Usuario rosa = null;

    @BeforeClass
    public static void testandoBeforeClass() {
        System.out.println("before class");
    }

    @AfterClass
    public static void testandoAfterClass() {
        System.out.println("after class");
    }

    @Before
    public void preparacao() {
        roberto = new Usuario("Roberto");
        cesar = new Usuario("Cesar");
        tadeu = new Usuario("Tadeu");
        daniela = new Usuario("Daniela");
        jose = new Usuario("Jose");
        rosa = new Usuario("Rosa");

        l = new LeilaoBuilder().para("XBOX")
                .lance(roberto, 300.00)
                .lance(cesar, 250.00)
                .lance(tadeu, 600.00)
                .lance(roberto, 100.00)
                .lance(cesar, 450.00)
                .constroi();
        avaliador = new Avaliador();
        System.out.println("inicializa teste");
    }

    /**
     * Teste do maior valor.
     */
    @Test
    public void testMaiorDeTodos() {
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
        double resultadoEsperado = 340.0;
        double resultado = avaliador.calcularValorMedio(l);
        assertEquals(resultadoEsperado, resultado, 0.1);
    }

    /**
     * Teste do maior e menor valor com apenas um lance.
     */
    @Test
    public void testValorMaiorEMenorComUmLance() {
        Leilao le = new LeilaoBuilder().para("Computador")
                .lance(roberto, 200.00)
                .constroi();

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
        Leilao le = new LeilaoBuilder().para("Impressora")
                .lance(roberto, 200.00)
                .lance(daniela, 450.00)
                .lance(jose, 120.00)
                .lance(rosa, 630.00)
                .lance(daniela, 700.00)
                .lance(roberto, 230.00)
                .constroi();

        avaliador.avalia(le);

        assertThat(avaliador.getMaiorDeTodos(), equalTo(700.0));
        assertThat(avaliador.getMenorDeTodos(), equalTo(120.0));       
    }

    /**
     * Teste do maior e menor valor com valores decrescentes.
     */
    @Test
    public void testValorMaiorEMenorComValoresDecrescentes() {
        Leilao le = new LeilaoBuilder().para("Impressora")
                .lance(roberto, 400.00)
                .lance(daniela, 300.00)
                .lance(jose, 200.00)
                .lance(rosa, 100.00)
                .constroi();

        avaliador.avalia(le);

        assertThat(avaliador.getMaiorDeTodos(), equalTo(400.0));
        assertThat(avaliador.getMenorDeTodos(), equalTo(100.0));
    }

    /**
     * Teste dos três maiores valores com 5 lances.
     */
    @Test
    public void testTresMaioresValores() {
        List<Lance> lance = avaliador.getMaioresValores(l);

        assertEquals(3, lance.size());
        assertThat(lance, hasItems(
                new Lance(tadeu, 600.0),
                new Lance(cesar, 450.0),
                new Lance(roberto, 300.0)
        ));

    }

    /**
     * Teste dos dois maiores valores com 2 lances.
     */
    @Test
    public void testDoisMaioresValores() {
        Leilao le = new LeilaoBuilder().para("Impressora")
                .lance(roberto, 400.00)
                .lance(daniela, 300.00)
                .constroi();

        List<Lance> lance = avaliador.getMaioresValores(le);

        assertEquals(2, lance.size());

        assertThat(lance, hasItems(
                new Lance(roberto, 400.0),
                new Lance(daniela, 300.0)
        ));

    }

    /**
     * Teste sem nenhum lance.
     */
    @Test
    public void testSemLances() {
        Leilao le = new LeilaoBuilder().para("XBOX").constroi();
        List<Lance> lance = avaliador.getMaioresValores(le);

        assertTrue(lance.isEmpty());
        assertEquals(0, lance.size());

    }

    /**
     * Teste de exceção quando leilão não possui nenhum lance.
     */
    @Test(expected = RuntimeException.class)
    public void testExcecaoSemLances() {
        Leilao le = new LeilaoBuilder().para("XBOX").constroi();
        avaliador.avalia(le);

    }

}
