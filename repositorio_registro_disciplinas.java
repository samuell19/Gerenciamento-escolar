import java.util.ArrayList;
import java.util.List;


//repositorio_registro_disciplinas para gerenciar os registros de disciplinas, como adicionar ou buscar.
public class repositorio_registro_disciplinas {
    private List<registro_disciplinas> registros;

    public repositorio_registro_disciplinas() {
        this.registros = new ArrayList<>();
    }

    public void adicionarRegistro(registro_disciplinas registro) {
        registros.add(registro);
    }

    public List<registro_disciplinas> listarRegistros() {
        return registros;
    }

    public registro_disciplinas buscarRegistroPorDisciplina(String codigoDisciplina) {
        for (registro_disciplinas registro : registros) {
            if (registro.getDisciplinas().getCodigo().equals(codigoDisciplina)) {
                return registro;
            }
        }
        throw new IllegalArgumentException("Registro de disciplina não encontrado para o código: " + codigoDisciplina);
    }

    public void removerRegistro(registro_disciplinas registro) {
        registros.remove(registro);
    }
}
