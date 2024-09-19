import java.util.HashMap;
import java.util.Map;

//repoisitório da classe professores, reponsável por gerenciar professores, além de usar hash map para armazenar os professores por id e fazer a busca por id
//posteriormente.

public class repositorio_prof {
    private Map<String, professores> professoresMap; // Armazena professores por id

    // construtor
    public repositorio_prof() {
        this.professoresMap = new HashMap<>();
    }

    //adiciona um novo professor ao repositório
    public void adicionarProfessor(professores professor) throws IllegalArgumentException {
        if (professoresMap.containsKey(professor.getIdentificação())) {
            throw new IllegalArgumentException("Professor com ID " + professor.getIdentificação() + " já existe.");
        }
        professoresMap.put(professor.getIdentificação(), professor);
    }

    //remove um professor do repositório
    public void removerProfessor(String identificação) throws IllegalArgumentException {
        if (!professoresMap.containsKey(identificação)) {
            throw new IllegalArgumentException("Professor com ID " + identificação + " não encontrado.");
        }
        professoresMap.remove(identificação);
    }

    //busca um professor pelo id
    public professores buscarProfessor(String identificação) throws IllegalArgumentException {
        professores professor = professoresMap.get(identificação);
        if (professor == null) {
            throw new IllegalArgumentException("Professor com ID " + identificação + " não encontrado.");
        }
        return professor;
    }

    // Lista todos os professores
    public void listarProfessores() {
        for (professores professor : professoresMap.values()) {
            System.out.println(professor);
        }
    }
}