import java.util.HashMap;
import java.util.Map;


//essa classe é dos alunos, onde estão as coisas basicas do aluno, como o nome, matricula e notas pela disciplina, ela faz ligação direta com as classe
//disciplinas e registro_disciplinas pq vc vai atualizar as notas de cada aluno para cada disciplina, o uso do hash map é para guardar as notas
// de cada aluno para cada disciplina e pegar elas.

//atribuição de variaveis
public class alunos {
    private String nome;
    private String matricula;
    private Map<disciplinas, registro_disciplinas> notasPorDisciplina; 

    
    public alunos(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
        this.notasPorDisciplina = new HashMap<>();
    }

   //registro de notas
    public void registrarNotas(disciplinas disciplina, double nota1, double nota2) {
        registro_disciplinas registro = new registro_disciplinas(disciplina, nota1, nota2, -1);
        notasPorDisciplina.put(disciplina, registro); 
    }

   //registro de notas para o prof mudar
    public void alterarNotas(disciplinas disciplina, double novaNota1, double novaNota2) {
        registro_disciplinas registro = notasPorDisciplina.get(disciplina);
        if (registro != null) {
            registro.setNota1(novaNota1);
            registro.setNota2(novaNota2);
        } else {
            System.out.println("Disciplina não encontrada para alterar as notas.");
        }
    }

    //calculo da media
    public double calcularMedia(disciplinas disciplina) {
        registro_disciplinas registro = notasPorDisciplina.get(disciplina);
        if (registro != null) {
            return registro.notaSemestre();
        } else {
            System.out.println("Disciplina não encontrada.");
            return -1;
        }
    }

    //isso aqui vai ser pro boletim, mas como nao foi criado uma classe pro boletim, foi feito esse metodo para mostrar os resultados
    public String verificarAprovacao(disciplinas disciplina) {
        registro_disciplinas registro = notasPorDisciplina.get(disciplina);
        if (registro != null) {
            double media = registro.notaSemestre();
            if (media >= 60) {
                return "Aprovado por média";
            } else if (registro.getNota_final() >= 60) {
                return "Aprovado após a prova final";
            } else {
                return "Reprovado";
            }
        } else {
            return "Disciplina não encontrada.";
        }
    }
    public void adicionarRegistro(registro_disciplinas registro) { 
        this.notasPorDisciplina.put(registro.getDisciplinas(), registro);
    }

    //gets e sets
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Map<disciplinas, registro_disciplinas> getNotasPorDisciplina() {
        return notasPorDisciplina;
    }

    public void setNotasPorDisciplina(Map<disciplinas, registro_disciplinas> notasPorDisciplina) {
        this.notasPorDisciplina = notasPorDisciplina;
    }

}
