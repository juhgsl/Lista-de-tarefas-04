package Parte02;

public enum NivelAcesso {
    ADMINISTRADOR("Criar, editar, excluir e consultar registros; gerenciar usuários e configurações"),
    GERENTE("Criar, editar e consultar registros; emitir relatórios"),
    USUARIO("Apenas consultar registros");

    private final String permissoes;

    NivelAcesso(String permissoes) { this.permissoes = permissoes; }

    public String getPermissoes() { return permissoes; }
}
