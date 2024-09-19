import java.util.HashMap;
import java.util.Map;


//repositorio disciplina, responsavel por armazenar e gerenciar as disciplinas seja para adicionar, remover, buscar e listar
public class repositorio_disciplina {
    private Map<String, disciplinas> disciplinasMap; 

    // construtor
    public repositorio_disciplina() {
        this.disciplinasMap = new HashMap<>();
    }

    // Adiciona disciplina
    public void adicionarDisciplina(disciplinas disciplina) throws IllegalArgumentException {
        if (disciplinasMap.containsKey(disciplina.getCodigo())) {
            throw new IllegalArgumentException("Disciplina com código " + disciplina.getCodigo() + " já existe.");
        }
        disciplinasMap.put(disciplina.getCodigo(), disciplina);
    }

    //remove disciplina 
    public void removerDisciplina(String codigo) throws IllegalArgumentException {
        if (!disciplinasMap.containsKey(codigo)) {
            throw new IllegalArgumentException("Disciplina com código " + codigo + " não encontrada.");
        }
        disciplinasMap.remove(codigo);
    }

    //busca disciplina
    public disciplinas buscarDisciplina(String codigo) throws IllegalArgumentException {
        disciplinas disciplina = disciplinasMap.get(codigo);
        if (disciplina == null) {
            throw new IllegalArgumentException("Disciplina com código " + codigo + " não encontrada.");
        }
        return disciplina;
    }

    // lista as disciplinas
    public void listarDisciplinas() {
        for (disciplinas disciplina : disciplinasMap.values()) {
            System.out.println(disciplina);
        }
    }
    
}