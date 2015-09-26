package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.List;

public class Leilao {

    private String descricao;
    private List<Lance> lances;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<Lance>();
    }

    public void propoe(Lance lance) {
        if (lances.isEmpty() || podeDarLance(lance.getUsuario())) {
            lances.add(lance);
        }
    }

    private boolean podeDarLance(Usuario usuario) {
        return !ultimoLance().getUsuario().equals(usuario) && quantidadeLancesUsuario(usuario) < 5;
    }

    private int quantidadeLancesUsuario(Usuario usuario) {
        int contador = 0;
        for (Lance l : lances) {
            if (l.getUsuario().equals(usuario)) {
                contador++;
            }
        }
        return contador;
    }

    private Lance ultimoLance() {
        return lances.get(lances.size() - 1);
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> getLances() {
        return lances;
    }

    public void dobroLance(Usuario usuario) {
        percorreUltimoLance(usuario);
    }

    private void percorreUltimoLance(Usuario usuario) {
        int i = 1;
        for (int j = 0; j < lances.size(); j++) {
            if (i <= lances.size()) {
                Lance lance = lances.get(lances.size() - i);
                if (lance.getUsuario().equals(usuario)) {
                    propoe(new Lance(usuario, (lance.getValor() * 2)));
                }
            }
            i++;
        }
    }

}
