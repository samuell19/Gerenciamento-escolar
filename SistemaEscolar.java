import java.util.Map;
import java.util.Scanner;


//a classe de interface do projeto. aqui terá as instruções de como as classes irão operar e interagir entre si.

public class SistemaEscolar {
    private repositorio_aluno alunosRepo;
    private repositorio_disciplina disciplinasRepo;
    private repositorio_prof professoresRepo;
    private repositorio_turma turmasRepo;  
    private repositorio_registro_disciplinas registroRepo;

    // construtor
    public SistemaEscolar() {
        this.alunosRepo = new repositorio_aluno();
        this.disciplinasRepo = new repositorio_disciplina();
        this.professoresRepo = new repositorio_prof();
        this.turmasRepo = new repositorio_turma(); 
        this.registroRepo = new repositorio_registro_disciplinas();
    }

    // método principal para iniciar o sistema, junto com o uso de switch case para escolher a opção desejada, no qual, mais adiante no código, cada metodo
    //das opções sera implementado e explicado.
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Sistema de Gerenciamento Escolar");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Disciplina");
            System.out.println("3. Cadastrar Professor");
            System.out.println("4. Cadastrar Turma");
            System.out.println("5. Registrar Nota");
            System.out.println("6. Emitir Boletim");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    cadastrarAluno(scanner);
                    break;
                case 2:
                    cadastrarDisciplina(scanner);
                    break;
                case 3:
                    cadastrarProfessor(scanner);
                    break;
                case 4:
                    cadastrarTurma(scanner);
                    break;
                case 5:
                    registrarNota(scanner);
                    break;
                case 6:
                    emitirBoletim(scanner);
                    break;
                case 7:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    // primeiro metodo a ser implementado, onde o usuário irá cadastrar um aluno e sua matrícula, com ligação direta com o repositório de 
    //alunos.
    private void cadastrarAluno(Scanner scanner) {
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();
        alunos aluno = new alunos(nome, matricula);
        try {
            alunosRepo.adicionarAluno(aluno);
            System.out.println("Aluno cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }



    //segundo metodo a ser implementado, onde o usuário irá cadastrar uma disciplina e seu código, com ligação direta com o repositório de disciplinas
    private void cadastrarDisciplina(Scanner scanner) {
        System.out.print("Nome da disciplina: ");
        String nome = scanner.nextLine();
        System.out.print("Código da disciplina: ");
        String codigo = scanner.nextLine();
        disciplinas disciplina = new disciplinas(nome, codigo);
        try {
            disciplinasRepo.adicionarDisciplina(disciplina);
            System.out.println("Disciplina cadastrada com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    //terceiro metodo que adiciona os professores, com ligação direta com o repositório de professores e requisistando que o usuário insira o nome e o id do 
    //professor
    private void cadastrarProfessor(Scanner scanner) {
        System.out.print("Nome do professor: ");
        String nome = scanner.nextLine();
        System.out.print("ID do professor: ");
        String id = scanner.nextLine();
        professores professor = new professores(nome, id);
        try {
            professoresRepo.adicionarProfessor(professor);
            System.out.println("Professor cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    //quarto metodo a ser implementado, onde o usuário irá cadastrar um codigo de turma, o codigo da disciplina, o id do professor responsavel e tambem a quantidade
    // de alunos que esta na turma, através de um for que irá percorrer toda lista de alunos
    private void cadastrarTurma(Scanner scanner) {
        System.out.print("Código da turma: ");
        String codigoTurma = scanner.nextLine();

        System.out.print("Código da disciplina: ");
        String codigoDisciplina = scanner.nextLine();

        disciplinas disciplina;
        try {
            disciplina = disciplinasRepo.buscarDisciplina(codigoDisciplina);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        

        System.out.print("ID do professor: ");
        String idProfessor = scanner.nextLine();

        professores professor;
        try {
            professor = professoresRepo.buscarProfessor(idProfessor); //busca o professor pela id
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        turmas novaTurma = new turmas(codigoTurma, professor, disciplina); //adiciona na nova turma

        System.out.println("Digite o número de alunos para adicionar na turma:");
        int numAlunos = scanner.nextInt();
        scanner.nextLine();  

        //for que irá percorrer toda a lista de alunos e adicionar na turma
        for (int i = 0; i < numAlunos; i++) {
            System.out.print("Matrícula do aluno " + (i + 1) + ": ");
            String matricula = scanner.nextLine();
            alunos aluno;
            try {
                aluno = alunosRepo.buscarAluno(matricula);
                novaTurma.adicionarAluno(aluno);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        turmasRepo.adicionarTurma(novaTurma);
        System.out.println("Turma cadastrada com sucesso.");
    }
    

    //quinto metodo a seer implementado, e um dos mais chatos. primeiramente sera pedida a matricula do aluno, e depois o codigo da disciplina. após isso,
    //o usuário irá inserir as notas do aluno, se o aluno tiver nota menor que 60, terá uma condição que irá pedir a nota da prova final.
    private void registrarNota(Scanner scanner) {
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();
        alunos aluno;
        try {
            aluno = alunosRepo.buscarAluno(matricula);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
    
        System.out.print("Código da disciplina: ");
        String codigoDisciplina = scanner.nextLine();
        disciplinas disciplina;
        try {
            disciplina = disciplinasRepo.buscarDisciplina(codigoDisciplina);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
    
        System.out.print("Nota 1: ");
        double nota1 = scanner.nextDouble();
        System.out.print("Nota 2: ");
        double nota2 = scanner.nextDouble();
        double notaSemestre = (nota1 + nota2) / 2;
    
        //cria o registro com as notas e a disciplina (sem nota final ainda, so se ele tirar menor que 60)
        registro_disciplinas registro = new registro_disciplinas(disciplina, nota1, nota2, -1);
        if (notaSemestre < 60) {
            System.out.print("Nota da prova final: ");
            double notaFinal = scanner.nextDouble();
            registro.setNota_final(notaFinal);
        }
        
    
        //adiciona o registro ao repositório de registros
        try {
            registroRepo.adicionarRegistro(registro);
            System.out.println("Registro de notas cadastrado com sucesso.");
            
            //adiciona o registro de notas ao aluno (se necessário)
            aluno.adicionarRegistro(registro);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    //ultimo metodo, no qual o boletim do aluno será emitido, onde o usuário irá inserir a matricula do aluno, e umaa busca será feita no repositório de alunos,
    //com isso, o boletim do aluno será emitido, mostrando as notas, a matricula e a disciplina. 
    private void emitirBoletim(Scanner scanner) {
    System.out.print("Matrícula do aluno: ");
    String matricula = scanner.nextLine();

    alunos aluno;
    try {
        aluno = alunosRepo.buscarAluno(matricula);  //busca o aluno pela matrícula
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
        return;
    }

    System.out.println("\nBoletim do aluno: " + aluno.getNome());
    System.out.println("Matrícula: " + aluno.getMatricula());
    System.out.println("---------------");

    //mapa de disciplinas e notas
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

    }
}
}
