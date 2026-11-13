package Parte02;

public class Chamado {
    private String titulo;
    private Prioridade prioridade;

    public Chamado(String titulo, Prioridade prioridade) {
        this.titulo = titulo;
        this.prioridade = prioridade;
    }

    public String mensagem() {
        switch (prioridade) {
            case BAIXA:   return "Chamado de baixa prioridade: será atendido quando houver disponibilidade.";
            case MEDIA:   return "Chamado de média prioridade: atendimento em até 2 dias úteis.";
            case ALTA:    return "Chamado de alta prioridade: atendimento em até 4 horas.";
            case URGENTE: return "ATENÇÃO! Chamado URGENTE: a equipe será acionada imediatamente.";
            default:      return "Prioridade desconhecida.";
        }
    }

    @Override
    public String toString() {
        return "Chamado '" + titulo + "' [" + prioridade + "]";
    }
}
