import java.util.ArrayList;
import java.util.List;


//classe da turma que consiste em armazenar as outras classes aqui, possui um código para acesso e faz uma lista de alunos para usar como contagem


public class turmas {
    private String codigo;
    private List<alunos> alunos; 
    private professores professor;
    private disciplinas disciplina;

    //construtor
    public turmas(String codigo, professores professor, disciplinas disciplina) {
        this.codigo = codigo;
        this.professor = professor;
        this.disciplina = disciplina;
        this.alunos = new ArrayList<>();
    }

    //metodo para adicionar alunos
    public void adicionarAluno(alunos aluno) {
        alunos.add(aluno);
    }

    //metodo para remover alunos
    public void removerAluno(alunos aluno) {
        alunos.remove(aluno);
    }

    // getters e setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<alunos> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<alunos> alunos) {
        this.alunos = alunos;
    }

    public professores getProfessor() {
        return professor;
    }

    public void setProfessor(professores professor) {
        this.professor = professor;
    }

    public disciplinas getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(disciplinas disciplina) {
        this.disciplina = disciplina;
    }
}
