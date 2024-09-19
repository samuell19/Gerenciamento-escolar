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

    //adicionar registro de notas
    public void adicionarRegistroNotas(String matricula, registro_disciplinas registro) throws IllegalArgumentException {
        alunos aluno = buscarAluno(matricula);
        aluno.adicionarRegistro(registro);
    }
    
}

