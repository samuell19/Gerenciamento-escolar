import java.util.Map;
import java.util.Scanner;


//interface para o sistema escolar, onde as coisas irão ser implementadas, conectadas e controladas pelo usuário

public class SistemaEscolar {  //aqui eu criei repositórios para cada classe, para que eles possam ser usados e manipulados
    private repositorio_aluno alunosRepo;
    private repositorio_disciplina disciplinasRepo;
    private repositorio_prof professoresRepo;
    private repositorio_turma turmasRepo;  
    private repositorio_registro_disciplinas registroRepo;
    private Scanner scanner;

    public SistemaEscolar() {
        this.alunosRepo = new repositorio_aluno();
        this.disciplinasRepo = new repositorio_disciplina();
        this.professoresRepo = new repositorio_prof();
        this.turmasRepo = new repositorio_turma(); 
        this.registroRepo = new repositorio_registro_disciplinas();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() { /*aqui eu criei um método para iniciar o sistema, além de criar um scanner para que o usuário possa digitar as informações, junto 
com switchs e cases para que o usuário possa escolher as opções do menu*/
        while (true) {
            exibirMenu();
            int opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    cadastrarDisciplina();
                    break;
                case 3:
                    cadastrarProfessor();
                    break;
                case 4:
                    cadastrarTurma();
                    break;
                case 5:
                    registrarNota();
                    break;
                case 6:
                    emitirBoletim();
                    break;
                case 7:
                     comandos_prof();
                     break;
                case 8:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void exibirMenu() { //apenas foi colocado strings para guiar o usuário
        System.out.println("\nSistema de Gerenciamento Escolar");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Cadastrar Disciplina");
        System.out.println("3. Cadastrar Professor");
        System.out.println("4. Cadastrar Turma");
        System.out.println("5. Registrar Nota");
        System.out.println("6. Emitir Boletim");
        System.out.println("7. Comandos do Professor");
        System.out.println("8. Sair");
    }

    private void cadastrarAluno() { //aqui eu criei um método para cadastrar alunos, onde é conectado com o repositório de alunos
        String nome = lerString("Nome do aluno: ");
        String matricula = lerString("Matrícula do aluno: ");
        alunos aluno = new alunos(nome, matricula);
        try {
            alunosRepo.adicionarAluno(aluno);
            System.out.println("Aluno cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cadastrarDisciplina() { //tal qual na de alunos, aqui foi criado um método para cadastrar disciplinas, conectada ao repositório de disciplinas
        String nome = lerString("Nome da disciplina: ");
        String codigo = lerString("Código da disciplina: ");
        disciplinas disciplina = new disciplinas(nome, codigo);
        try {
            disciplinasRepo.adicionarDisciplina(disciplina);
            System.out.println("Disciplina cadastrada com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cadastrarProfessor() { //do mesmo modo foi criado um método para cadastrar professores, conectado ao repositório de professores.
        String nome = lerString("Nome do professor: ");
        String id = lerString("ID do professor: ");
        professores professor = new professores(nome, id);
        try {
            professoresRepo.adicionarProfessor(professor);
            System.out.println("Professor cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cadastrarTurma() {//a classe de turmas foi criada para que o usuário possa cadastrar as turmas, onde será colocadas nela os alunos, profs, e disciplinas
        System.out.println("Alunos, disciplinas e professores cadastrados:");
        alunosRepo.listarAlunos();
        disciplinasRepo.listarDisciplinas();
        professoresRepo.listarProfessores();

        String codigoTurma = lerString("Código da turma: ");
        String codigoDisciplina = lerString("Código da disciplina: ");

        disciplinas disciplina;
        try {
            disciplina = disciplinasRepo.buscarDisciplina(codigoDisciplina);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        String idProfessor = lerString("ID do professor: ");

        professores professor;
        try {
            professor = professoresRepo.buscarProfessor(idProfessor);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        turmas novaTurma = new turmas(codigoTurma, professor, disciplina);

        int numAlunos = lerInteiro("Digite o número de alunos para adicionar na turma: ");

        for (int i = 0; i < numAlunos; i++) {
            String matricula = lerString("Matrícula do aluno " + (i + 1) + ": "); //for para colocar a quantidade de alunos
            try {
                alunos aluno = alunosRepo.buscarAluno(matricula);
                novaTurma.adicionarAluno(aluno);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        turmasRepo.adicionarTurma(novaTurma);
        System.out.println("Turma cadastrada com sucesso.");
    }

    private void registrarNota() {/*método de notas, onde será listado para o usuário os alunos e disciplinas cadastrados, para que o usuário possa escolher,
        além disso será feito uma condição que se a nota for menor que 60, seré necessário colocar a nota da prova final*/
        System.out.println("Alunos e disciplinas cadastrados:");
        alunosRepo.listarAlunos();
        disciplinasRepo.listarDisciplinas();

        String matricula = lerString("Matrícula do aluno: ");
        alunos aluno;
        try {
            aluno = alunosRepo.buscarAluno(matricula);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        String codigoDisciplina = lerString("Código da disciplina: ");
        disciplinas disciplina;
        try {
            disciplina = disciplinasRepo.buscarDisciplina(codigoDisciplina);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        double nota1 = lerDouble("Nota 1: ");
        double nota2 = lerDouble("Nota 2: ");
        double notaSemestre = (nota1 + nota2) / 2;

        registro_disciplinas registro = new registro_disciplinas(disciplina, nota1, nota2, -1);
        if (notaSemestre < 60) { //if para verificar se vai ser necessario provas
            double notaFinal = lerDouble("Nota da prova final: ");
            registro.setNota_final(notaFinal);
        }

        try {
            registroRepo.adicionarRegistro(registro); //é registrado a nota no sistema
            System.out.println("Registro de notas cadastrado com sucesso.");
            aluno.adicionarRegistro(registro);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void emitirBoletim() { /*metodo para emitir o boletim, onde será listado os alunos cadastrados para o usuário escolher
         é um método simples que irá printar o que está armazenado e irá indicar a situação do aluno*/
        System.out.println("Alunos cadastrados:");
        alunosRepo.listarAlunos();

        String matricula = lerString("Matrícula do aluno: ");

        alunos aluno;
        try {
            aluno = alunosRepo.buscarAluno(matricula);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("\nBoletim do aluno: " + aluno.getNome());
        System.out.println("Matrícula: " + aluno.getMatricula());
        System.out.println("---------------");

        for (Map.Entry<disciplinas, registro_disciplinas> entry : aluno.getNotasPorDisciplina().entrySet()) {
            disciplinas disciplina = entry.getKey();
            registro_disciplinas registro = entry.getValue();
            
            System.out.println("Disciplina: " + disciplina.getNome());
            System.out.println("Nota 1: " + registro.getNota1());
            System.out.println("Nota 2: " + registro.getNota2());
            if (registro.getNota_final() >= 0) {
                System.out.println("Nota Final: " + registro.getNota_final());
            }
            System.out.println("Média do Semestre: " + registro.notaSemestre());
            System.out.println("Status: " + aluno.verificarAprovacao(disciplina));
            System.out.println("---------------");
        }
    } /*um dos métodos mais trabalhosos, primeiramente foi pedido o id do professor para só o professor ter acesso a esse comando, depois, foram criados cases
    para metodos dentro desse metodo, com ele sendo praticamente um menu, onde o professor pode escolher o que deseja fazer;
     */
    private void comandos_prof(){
            System.out.println("Professores cadastrados: ");
            professoresRepo.listarProfessores();
        
            System.out.println("\nID do professor:");
            String identificacao = lerString("Digite o ID do professor: ");
            professores professor;
            try {
                professor = professoresRepo.buscarProfessor(identificacao);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        
            while (true) {
                System.out.println("\nComandos do Professor:");
                System.out.println("1. Remover Aluno");
                System.out.println("2. Alterar Nota ");
                System.out.println("3. Remover Turma");
                System.out.println("4. Voltar ao Menu Principal");
        
                int opcao = lerInteiro("Escolha uma opção: ");
        
                switch (opcao) { //switch e cases para armazenar os comandos que o professor pode fazer, como remover alunos ou alterar notas
                    case 1:
                        removerAluno(professor);
                        break;
                    case 2:
                        alterar_as_notas(professor);
                        break;
                    case 3:
                        removerTurma(professor);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                }
            }
        }
        
        private void removerAluno(professores professor) { //método que acessa o repositorio dos alunos para pegar o metodo de remover a matricula do aluno
            System.out.println("Alunos cadastrados:");
            alunosRepo.listarAlunos();
        
            String matricula = lerString("Digite a matrícula do aluno a ser removido: ");
            try {
                
                alunosRepo.removerAluno(matricula);
                System.out.println("Aluno removido com sucesso.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        
        private void removerTurma(professores professor) {//metodo que acessa o repositorio de turmas pora remover as turmas
            System.out.println("Turmas cadastradas:");
            for (turmas turma : turmasRepo.listarTurmas()) {
                System.out.println("Código: " + turma.getCodigo() + ", Disciplina: " + turma.getDisciplina().getNome());
            }
        
            String codigoTurma = lerString("Digite o código da turma a ser removida: ");
            try {
                
                turmasRepo.removerTurma(codigoTurma);
                System.out.println("Turma removida com sucesso.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        private void alterar_as_notas(professores professor) { //metodo que acessa o repositorio de alunos para pegar o metodo de alterar as notas
            System.out.println("Alunos cadastrados:");
            alunosRepo.listarAlunos();
            
            String matricula = lerString("Digite a matrícula do aluno: ");
            
            try {
                alunos aluno = alunosRepo.buscarAluno(matricula);
                System.out.println("Disciplinas do aluno " + aluno.getNome() + ":");
                
                for (Map.Entry<disciplinas, registro_disciplinas> entry : aluno.getNotasPorDisciplina().entrySet()) {
                    System.out.println(entry.getKey().getCodigo() + " - " + entry.getKey().getNome());
                }
                
                String codigoDisciplina = lerString("Digite o código da disciplina: ");
                
                double novaNota1 = lerDouble("Digite a nova Nota 1: ");
                double novaNota2 = lerDouble("Digite a nova Nota 2: ");
                
                alunosRepo.alterarNotas(matricula, codigoDisciplina, novaNota1, novaNota2);
                System.out.println("Notas alteradas com sucesso.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }


    //esses métodos servem para simplificar e centralizar a leitura de diferentes tipos de dados (string, int e double) usando o Scanner

    private String lerString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int lerInteiro(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número válido.");
            }
        }
    }

    private double lerDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número válido.");
            }
        }
    }
}