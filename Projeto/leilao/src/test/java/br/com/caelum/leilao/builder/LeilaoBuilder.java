package br.com.caelum.leilao.builder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

/**
 *
 * @author roberto
 */
public class LeilaoBuilder {

    private Leilao leilao;

    public LeilaoBuilder() {
    }

    public LeilaoBuilder para(String descricao) {
        this.leilao = new Leilao(descricao);
        return this;
    }

    public LeilaoBuilder lance(Usuario usuario, double valor) {
        this.leilao.propoe(new Lance(usuario, valor));
        return this;
    }

    public Leilao constroi() {
        return leilao;
    }

}
