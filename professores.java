import java.util.List;
import java.util.ArrayList;


//classe dos professores que tem como atributos nome, identificação e uma lista de turmas, para verificar as turmas que ele é responsável

public class professores {
    private String nome;
    private String identificação;
    private List <turmas> turma;


    //construtor
    public professores(String nome, String identificação){
        this.nome=nome;
        this.identificação=identificação;
        this.turma=new ArrayList<>();
    }
    //metodo para adicionar turmas
    public void adicionarTurma(turmas turma){
        this.turma.add(turma);
    }
    //metodo para remover turmas
    public void removerTurma(turmas turma){
        this.turma.remove(turma);
    }
    //metodo para registrar as notas dos alunos
    public void registrarNota(alunos alunos, disciplinas disciplinas, double nota1, double nota2) {
       
        alunos.registrarNotas(disciplinas, nota1, nota2);
    }

   //metodo para alterar as notas dos alunos
    public void alterarNota(alunos alunos, disciplinas disciplinas, double novaNota1, double novaNota2) {
        
        alunos.alterarNotas(disciplinas, novaNota1, novaNota2);
    }

    //getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdentificação() {
        return identificação;
    }

    public void setIdentificação(String identificação) {
        this.identificação = identificação;
    }

    public List<turmas> getTurma() {
        return turma;
    }

    public void setTurma(List<turmas> turma) {
        this.turma = turma;
    }

    
    
}
