import java.util.HashMap;
import java.util.Map;

//classe de repositório de alunos, onde será feito a adição, remoção e busca de alunos(com a busca sendo feita pela matrícula), além de serem usadas
//exceções para quando não for encontrado o aluno desejado ou o aluno for repetido por exemplo, os erros já serão tratados aqui.

public class repositorio_aluno {
    private Map<String, alunos> alunosMap; 

    //construtor
    public repositorio_aluno() {
        this.alunosMap = new HashMap<>();
    }

    //adicionar alunos ao repositório
    public void adicionarAluno(alunos aluno) throws IllegalArgumentException {
        if (alunosMap.containsKey(aluno.getMatricula())) {
            throw new IllegalArgumentException("Aluno com matrícula " + aluno.getMatricula() + " já existe.");
        }
        alunosMap.put(aluno.getMatricula(), aluno);
    }

    //remover alunos do repositório
    public void removerAluno(String matricula) throws IllegalArgumentException {
        if (!alunosMap.containsKey(matricula)) {
            throw new IllegalArgumentException("Aluno com matrícula " + matricula + " não encontrado.");
        }
        alunosMap.remove(matricula);
    }

    //busca pela matrícula
    public alunos buscarAluno(String matricula) throws IllegalArgumentException {
        alunos aluno = alunosMap.get(matricula);
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno com matrícula " + matricula + " não encontrado.");
        }
        return aluno;
    }

    // lista  de todos os alunos
    public void listarAlunos() {
        for (alunos aluno : alunosMap.values()) {
            System.out.println(aluno);
        }
    }

    public void alterarNotas(String matricula, String codigoDisciplina, double novaNota1, double novaNota2) throws IllegalArgumentException { /*metodo para alterar
             as notas de um aluno, no qual é feito as exceções para verificar se é possivel alterar e também ocorre a alteração das notas.*/
      
        alunos aluno = buscarAluno(matricula);
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno com matrícula " + matricula + " não encontrado.");
        }

        disciplinas disciplina = null;
        for (Map.Entry<disciplinas, registro_disciplinas> entry : aluno.getNotasPorDisciplina().entrySet()) {
            if (entry.getKey().getCodigo().equals(codigoDisciplina)) {
                disciplina = entry.getKey();
                break;
            }
        }

        if (disciplina == null) {
            throw new IllegalArgumentException("Disciplina com código " + codigoDisciplina + " não encontrada para este aluno.");
        }

        aluno.alterarNotas(disciplina, novaNota1, novaNota2);
        System.out.println("Notas alteradas com sucesso para o aluno " + aluno.getNome() + " na disciplina " + disciplina.getNome());
    }

    //adicionar registro de notas
    public void adicionarRegistroNotas(String matricula, registro_disciplinas registro) throws IllegalArgumentException {
        alunos aluno = buscarAluno(matricula);
        aluno.adicionarRegistro(registro);
    }
    
}

