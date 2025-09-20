package br.com.TrueUnion.TrueUnion.Enums;

public enum CategoriaFornecedor {
    BUFFET("Buffet e alimentação"),
    DECORACAO("Decoração"),
    SOM_ILUMINACAO("Som e iluminação"),
    ESPACO("Espaço/Local"),
    TRANSPORTE("Transporte"),
    OUTROS("Outros");

    private final String descricao;

    CategoriaFornecedor(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
