package br.com.TrueUnion.TrueUnion.Enums;

public enum CategoriaDespesa {
    ALIMENTACAO("Alimentação"),
    DECORACAO("Decoração"),
    LOCACAO("Locação"),
    SERVICO("Serviço"),
    OUTROS("Outros");

    private final String descricao;

    CategoriaDespesa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
