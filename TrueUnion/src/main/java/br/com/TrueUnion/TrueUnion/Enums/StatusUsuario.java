package br.com.TrueUnion.TrueUnion.Enums;

public enum StatusUsuario {
    ATIVO("Usuário ativo"),
    INATIVO("Usuário inativo"),
    BLOQUEADO("Usuário bloqueado");

    private final String descricao;

    StatusUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
