package br.com.caelum.leilao.desafio;

import br.com.caelum.leilao.builder.LeilaoBuilder;
import static br.com.caelum.leilao.desafio.LeilaoMatcher.temUmLance;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author roberto
 */
public class LeilaoMatcherTest {

    @Test
    public void deveReceberUmLance() {
        Leilao leilao = new LeilaoBuilder().para("Macbook Pro 15").constroi();
        assertEquals(0, leilao.getLances().size());

        Lance lance = new Lance(new Usuario("Steve Jobs"), 2000);
        leilao.propoe(lance);

        assertThat(leilao.getLances().size(), equalTo(1));
        assertThat(leilao, temUmLance(lance));
    }
}
