package br.com.TrueUnion.TrueUnion.Enums;

public enum PerfilUsuario {
    ADMIN("Administrador"),
    DONO_EVENTO("Dono do evento"),
    USUARIO_COMUM("Usuário comum");

    private final String descricao;

    PerfilUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
