package br.com.caelum.leilao.dominio;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roberto
 */
public class LeilaoTest {

    public LeilaoTest() {
    }

    /**
     * Teste do metodo para não aceitar dois lances seguidos do mesmo usuário.
     */
    @Test
    public void lancesSeguidos() {
        Leilao l = new Leilao("Celular");
        Usuario roberto = new Usuario("Roberto");

        l.propoe(new Lance(roberto, 200.00));
        l.propoe(new Lance(roberto, 500.00));

        assertEquals(1, l.getLances().size());
        assertEquals(200.00, l.getLances().get(0).getValor(), 0.1);
    }

    /**
     * Teste do metodo para limitar em apenas 5 lances por usuário.
     */
    @Test
    public void limiteLancesUsuario() {
        Leilao l = new Leilao("Celular");
        Usuario roberto = new Usuario("Roberto");
        Usuario jose = new Usuario("Jose");

        l.propoe(new Lance(roberto, 200.00));
        l.propoe(new Lance(jose, 700.00));

        l.propoe(new Lance(roberto, 300.00));
        l.propoe(new Lance(jose, 800.00));

        l.propoe(new Lance(roberto, 400.00));
        l.propoe(new Lance(jose, 900.00));

        l.propoe(new Lance(roberto, 500.00));
        l.propoe(new Lance(jose, 1000.00));

        l.propoe(new Lance(roberto, 600.00));
        l.propoe(new Lance(jose, 1100.00));

        l.propoe(new Lance(roberto, 1200.00));

        assertEquals(10, l.getLances().size());
        assertEquals(1100.00, l.getLances().get(l.getLances().size() - 1).getValor(), 0.1);
    }

    /**
     * Teste do dobro do último lance por usuário.
     */
    @Test
    public void dobroLanceUsuario() {
        Leilao l = new Leilao("Celular");
        Usuario roberto = new Usuario("Roberto");
        Usuario jose = new Usuario("Jose");

        l.propoe(new Lance(roberto, 200.00));
        l.propoe(new Lance(jose, 700.00));

        l.propoe(new Lance(roberto, 300.00));
        l.propoe(new Lance(jose, 800.00));

        l.propoe(new Lance(roberto, 400.00));
        l.propoe(new Lance(jose, 900.00));

        l.propoe(new Lance(roberto, 600.00));
        l.propoe(new Lance(jose, 1000.00));

        l.dobroLance(roberto);
        l.dobroLance(jose);
        l.dobroLance(jose);
        l.dobroLance(roberto);
        assertEquals(10, l.getLances().size());
        assertEquals(1200.00, l.getLances().get(l.getLances().size() - 2).getValor(), 0.1);
        assertEquals("Roberto", l.getLances().get(l.getLances().size() - 2).getUsuario().getNome());
        assertEquals(2000.00, l.getLances().get(l.getLances().size() - 1).getValor(), 0.1);
        assertEquals("Jose", l.getLances().get(l.getLances().size() - 1).getUsuario().getNome());
    }

    /**
     * Teste para não dobrar último lance por usuário.
     */
    @Test
    public void naodobrarLanceUsuario() {
        Leilao l = new Leilao("Celular");
        Usuario roberto = new Usuario("Roberto");

        l.dobroLance(roberto);
        assertEquals(0, l.getLances().size());
    }

}
