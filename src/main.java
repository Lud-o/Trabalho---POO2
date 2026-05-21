// ARQUIVO: Main.java
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Sistema.init();
        int opcao = -1;

        do {
            System.out.println("\n┌──────────────────────────────────────────┐");
            System.out.println("│        SISTEMA DE GESTÃO ESCOLAR         │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  --- CADASTROS ---                       │");
            System.out.println("│  [1] Cadastrar Nova Disciplina           │");
            System.out.println("│  [2] Cadastrar Novo Aluno                │");
            System.out.println("│                                          │");
            System.out.println("│  --- OPERAÇÕES ---                       │");
            System.out.println("│  [3] Matricular Aluno em Disciplina      │");
            System.out.println("│  [4] Alterar Dados                       │");
            System.out.println("│  [5] Excluir Dados                       │");
            System.out.println("│                                          │");
            System.out.println("│  --- RELATÓRIOS E LISTAGENS ---          │");
            System.out.println("│  [6] Listar Disciplinas                  │");
            System.out.println("│  [7] Listar Alunos (Ordem de Cadastro)   │");
            System.out.println("│  [8] Listar Alunos (Ordem de Médias> )   │");
            System.out.println("│  [9] Listar Matrículas                   │");
            System.out.println("│  [10] Configurar Largura das Colunas     │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  [0] Sair do Sistema                     │");
            System.out.println("└──────────────────────────────────────────┘");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarDisciplina();
                    break;
                case 2:
                    cadastrarAluno();
                    break;
                case 3:
                    realizarMatricula();
                    break;
                case 4:
                    alterarDados();
                    break;
                case 5:
                    excluirDados();
                    break;
                case 6:
                    Sistema.listarDisciplinas();
                    break;
                case 7:
                    Sistema.listarAlunosOC();
                    break;
                case 8:
                    Sistema.listarAlunosOMd();
                    break;
                case 9:
                    Sistema.listarMatriculas();
                    break;
                case 10:
                    System.out.print("Digite a nova largura para as colunas de texto (Atual: " + Sistema.getLarguraColuna() + "): ");
                    int nova = sc.nextInt();
                    sc.nextLine();
                    Sistema.setLarguraColuna(nova);
                    System.out.println("Largura atualizada com sucesso!");
                    break;
                case 0:
                    System.out.println("--Sistema Finalizado--");
                    break;
                default:
                    System.out.println("ERRO: Opção inválida! Tente novamente.");
                    break;
            }
        } while (opcao != 0);

        System.exit(0);
    }

    public static void cadastrarDisciplina() {
        if (Sistema.bancoDisciplinalimite()) {
            return;
        }

        while (true) {
            System.out.print("Digite o nome da Disciplina (voltar - 0): ");
            String nome = sc.nextLine();

            if (nome.equalsIgnoreCase("0")) {
                System.out.println("Voltando...");
                return;
            }

            if (Sistema.buscaDisciplina(nome) != -1) {
                System.out.println("ERRO: Já existe uma disciplina cadastrado com esse nome!");
            } else {
                while (true) {
                    System.out.print("Digite a sigla da Disciplina (voltar - 0): ");
                    String sigla = sc.nextLine();

                    if (sigla.equalsIgnoreCase("0")) {
                        System.out.println("Voltando...");
                        return;
                    }

                    if (Sistema.buscanmSigla(sigla)) {
                        System.out.println("ERRO: Já existe uma disciplina cadastrado com essa sigla!");
                    } else {
                        System.out.print("Ano: ");
                        int ano = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nome do Professor: ");
                        String professor = sc.nextLine();

                        Sistema.fichaDisciplina(nome, sigla, ano, professor);
                        return;
                    }
                }
            }
        }
    }

    public static void cadastrarAluno() {
        if (Sistema.bancoAlunolimite()) {
            return;
        }

        while (true) {
            System.out.print("Digite o nome do Aluno (voltar - 0): ");
            String nome = sc.nextLine();

            if (nome.equalsIgnoreCase("0")) {
                System.out.println("Voltando...");
                return;
            }

            if (Sistema.buscaAluno(nome) != -1) {
                System.out.println("ERRO: Já existe um aluno cadastrado com esse nome!, Digite outro nome.");
            } else {
                System.out.print("Endereço: ");
                String endereco = sc.nextLine();
                Sistema.fichaAluno(nome, endereco);
                return;
            }
        }
    }

    public static void realizarMatricula() {
        while (true) {
            System.out.print("Digite o nome do Aluno (voltar - 0): ");
            String nomeAluno = sc.nextLine();

            if (nomeAluno.equalsIgnoreCase("0")) {
                System.out.println("Voltando...");
                return;
            }

            int indiceAluno = Sistema.buscaAluno(nomeAluno);
            if (indiceAluno == -1) {
                System.out.println("Aluno não cadastrado!");
                continue;
            }

            Aluno aluno = Sistema.getAluno(indiceAluno);

            if (aluno.qtdDisciplinas >= 10) {
                System.out.println("Limite máximo de disciplinas atingido!");
                return;
            }

            while (true) {
                Sistema.listarDisciplinas();
                System.out.print("Digite o nome da Disciplina (voltar - 0): ");
                String nomeDiscp = sc.nextLine();

                if (nomeDiscp.equalsIgnoreCase("0")) {
                    System.out.println("Voltando...");
                    return;
                }

                int indiceDisciplina = Sistema.buscaDisciplina(nomeDiscp);
                if (indiceDisciplina == -1) {
                    System.out.println("Disciplina não cadastrada!");
                    continue;
                }

                Disciplina disciplina = Sistema.getDisciplina(indiceDisciplina);

                boolean jaMatriculado = false;
                for (int i = 0; i < aluno.qtdDisciplinas; i++) {
                    if (aluno.disciplinasMatriculadas[i].codigoDisciplina == disciplina.codigo) {
                        System.out.println("ERRO: O aluno já está matriculado nesta disciplina!");
                        jaMatriculado = true;
                        break;
                    }
                }

                if (jaMatriculado) {
                    continue;
                }

                System.out.print("Digite a nota atual do aluno na disciplina: ");
                double nota = sc.nextDouble();
                sc.nextLine();

                Sistema.matricular(nomeAluno, nomeDiscp, nota);
                return;
            }
        }
    }

    public static void alterarDados() {
        int op = -1;
        do {
            System.out.println("\n┌──────────────────────────────────────────┐");
            System.out.println("│              ALTERAR DADOS               │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  [1] Alterar Disciplina                  │");
            System.out.println("│  [2] Alterar Aluno                       │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  [0] Voltar ao Menu Principal            │");
            System.out.println("└──────────────────────────────────────────┘");
            System.out.print("Escolha o que deseja alterar: ");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    alterarDisciplina();
                    break;
                case 2:
                    alterarAluno();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("ERRO: Opção inválida!");
                    break;
            }
        } while (op != 0);
    }

    public static void alterarDisciplina() {
        if (Sistema.getTotalDisciplinas() == 0) {
            System.out.println("Não existem disciplinas cadastradas!");
            return;
        }

        int op = -1;

        do {
            System.out.println("\n┌──────────────────────────────────────────┐");
            System.out.println("│       ALTERAR DADOS DA DISCIPLINA        │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  [1] Alterar Nome da Disciplina          │");
            System.out.println("│  [2] Alterar Sigla                       │");
            System.out.println("│  [3] Alterar Ano                         │");
            System.out.println("│  [4] Alterar Professor                   │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  [0] Voltar ao Menu Anterior             │");
            System.out.println("└──────────────────────────────────────────┘");
            System.out.print("Escolha o que deseja alterar: ");

            op = sc.nextInt();
            sc.nextLine();

            if (op == 0) {
                System.out.println("Voltando...");
                continue;
            } else if (op < 0 || op > 4) {
                System.out.println("ERRO: Opção inválida!");
                continue;
            }

            System.out.print("\nDigite o nome atual da disciplina: ");
            String nomeAtual = sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Digite o NOVO Nome para a disciplina: ");
                    String novoNome = sc.nextLine();
                    Sistema.alterarDisciplinaNome(nomeAtual, novoNome);
                    break;
                case 2:
                    System.out.print("Digite a NOVA Sigla: ");
                    String novaSigla = sc.nextLine();
                    Sistema.alterarDisciplinaSigla(nomeAtual, novaSigla);
                    break;
                case 3:
                    System.out.print("Digite o NOVO Ano: ");
                    int novoAno = sc.nextInt();
                    sc.nextLine();
                    Sistema.alterarDisciplinaAno(nomeAtual, novoAno);
                    break;
                case 4:
                    System.out.print("Digite o NOVO Nome do Professor: ");
                    String novoProfessor = sc.nextLine();
                    Sistema.alterarDisciplinaProfessor(nomeAtual, novoProfessor);
                    break;
            }
        } while (op != 0);
    }

    public static void alterarAluno() {
        if (Sistema.getTotalAlunos() == 0) {
            System.out.println("Não existem alunos cadastrados!");
            return;
        }

        int op = -1;

        do {
            System.out.println("\n┌──────────────────────────────────────────┐");
            System.out.println("│          ALTERAR DADOS DO ALUNO          │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  [1] Alterar Nome do Aluno               │");
            System.out.println("│  [2] Alterar Endereço do Aluno           │");
            System.out.println("│  [3] Alterar Nota e Matrícula            │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  [0] Voltar ao Menu Principal            │");
            System.out.println("└──────────────────────────────────────────┘");
            System.out.print("Escolha o que deseja alterar: ");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("\nDigite o nome atual do aluno: ");
                    String nomeAntigo = sc.nextLine();
                    System.out.print("Digite o NOVO Nome: ");
                    String novoNome = sc.nextLine();
                    Sistema.alterarAlunoNome(nomeAntigo, novoNome);
                    break;
                case 2:
                    System.out.print("\nDigite o nome do aluno: ");
                    String nomeEnd = sc.nextLine();
                    System.out.print("Digite o NOVO Endereço: ");
                    String novoEndereco = sc.nextLine();
                    Sistema.alterarAlunoEndereco(nomeEnd, novoEndereco);
                    break;
                case 3:
                    alterarMatricula();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("ERRO: Opção inválida!");
                    break;
            }
        } while (op != 0);
    }

    public static void alterarMatricula() {
        while (true) {
            System.out.print("Digite o nome do Aluno (voltar - 0): ");
            String nome = sc.nextLine();

            if (nome.equals("0")) {
                System.out.println("Voltando...");
                return;
            }

            int indiceAluno = Sistema.buscaAluno(nome);
            if (indiceAluno == -1) {
                System.out.println("ERRO: Aluno não encontrado!");
                continue;
            }

            Aluno aluno = Sistema.getAluno(indiceAluno);
            if (aluno.qtdDisciplinas == 0) {
                System.out.println("Nenhuma Matricula encontrada!");
                continue;
            }

            System.out.println("Matricula de " + aluno.nome);
            for (int i = 0; i < aluno.qtdDisciplinas; i++) {
                Matricula m = aluno.disciplinasMatriculadas[i];
                String sigla = Sistema.buscacdSigla(m.codigoDisciplina);
                System.out.printf("[%d] %s - Nota Atual: %.2f\n", (i + 1), sigla, m.nota);
            }

            while (true) {
                System.out.print("Escolha o numero da materia que deseja alterar, caso queira voltar digite 0: ");
                int escolha = sc.nextInt();
                sc.nextLine();

                if (escolha == 0) {
                    System.out.println("Voltando...");
                    return;
                }

                if (escolha < 1 || escolha > aluno.qtdDisciplinas) {
                    System.out.println("ERRO: Opção inválida!");
                }
            }
        }
    }
}
