import java.util.ArrayList;
import java.util.List;

public class repositorio_turma {
    private List<turmas> listaDeTurmas;

    // Construtor
    public repositorio_turma() {
        this.listaDeTurmas = new ArrayList<>();
    }

    // Adiciona uma nova turma ao repositório
    public void adicionarTurma(turmas turma) {
        if (buscarTurma(turma.getCodigo()) != null) {
            throw new IllegalArgumentException("Turma com o código " + turma.getCodigo() + " já está cadastrada.");
        }
        listaDeTurmas.add(turma);
    }

    // Remove uma turma pelo código
    public void removerTurma(String codigoTurma) {
        turmas turma = buscarTurma(codigoTurma);
        if (turma == null) {
            throw new IllegalArgumentException("Turma com o código " + codigoTurma + " não encontrada.");
        }
        listaDeTurmas.remove(turma);
    }

    // Busca uma turma pelo código
    public turmas buscarTurma(String codigoTurma) {
        for (turmas turma : listaDeTurmas) {
            if (turma.getCodigo().equals(codigoTurma)) {
                return turma;
            }
        }
        return null; // Retorna null se não encontrar a turma
    }

    // Retorna todas as turmas cadastradas
    public List<turmas> listarTurmas() {
        return listaDeTurmas;
    }
}
