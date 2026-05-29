import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    
    private Scanner sc;
    private int larguraColuna;

    public Main() {
        this.sc = new Scanner(System.in);
        this.larguraColuna = 25; 
    }

    public static void main(String[] args) {
        Main interfaceUsuario = new Main();
        interfaceUsuario.iniciar();
    }

    public void iniciar() {
        Sistema sistema = Sistema.getInstance();
        sistema.init();

        int opcao = -1;
        do {
            System.out.println("\n┌──────────────────────────────────────────┐");
            System.out.println("│        SISTEMA DE GESTÃO ESCOLAR         │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  --- CADASTROS E DADOS ---               │");
            System.out.println("│  [1] Cadastrar Nova Disciplina           │");
            System.out.println("│  [2] Cadastrar Novo Aluno                │");
            System.out.println("│  [3] Cadastrar Nova Turma                │");
            System.out.println("│  [4] Alterar Dados                       │");
            System.out.println("│  [5] Excluir Dados                       │");
            System.out.println("│                                          │");
            System.out.println("│  --- OPERAÇÕES ---                       │");
            System.out.println("│  [6] Matricular Aluno em Turma           │");
            System.out.println("│  [7] Adicionar Disciplina em Turma       │");
            System.out.println("│  [8] Lançar Nota para Aluno              │");
            System.out.println("│                                          │");
            System.out.println("│  --- LISTAGENS ---                       │");
            System.out.println("│  [9] Listar Disciplinas                  │");
            System.out.println("│  [10] Listar Turmas                      │");
            System.out.println("│  [11] Listar Alunos (Ordem de Cadastro)  │");
            System.out.println("│  [12] Listar Alunos (Ordem de Media)     │");
            System.out.println("│  [13] Listar Matrículas                  │");
            System.out.println("│  [14] Listar Notas do Aluno              │");
            System.out.println("│  [15] Configurar Largura das Colunas     │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  [0] Sair do Sistema                     │");
            System.out.println("└──────────────────────────────────────────┘");
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1: 
                    fichaDisciplina(sistema); 
                    break;
                case 2: 
                    fichaAluno(sistema); 
                    break;
                case 3: 
                    fichaTurma(sistema); 
                    break;
                case 4: 
                    alterarDados(sistema); 
                    break;
                case 5: 
                    excluirDados(sistema); 
                    break;
                case 6: 
                    matricularAluno(sistema); 
                    break;
                case 7: 
                    adicionarDisciplinaNaTurma(sistema); 
                    break;
                case 8: 
                    lancarNota(sistema); 
                    break;
                case 9: 
                    listarDisciplinas(sistema); 
                    break;
                case 10: 
                    listarTurmas(sistema); 
                    break;
                case 11: 
                    listarAlunosOC(sistema); 
                    break;
                case 12: 
                    listarAlunosOMd(sistema); 
                    break;
                case 13: 
                    listarMatriculas(sistema); 
                    break;
                case 14: 
                    listarNotasAluno(sistema); 
                    break;
                case 15: 
                    configurarLargura(); 
                    break;
                case 0: 
                    System.out.println("-- Sistema Finalizado --"); 
                    break;
                default: 
                    System.out.println("ERRO: Opção inválida!"); 
                    break;
            }
        } while (opcao != 0);

        sc.close();
    }

    private void fichaAluno(Sistema sistema) {
        System.out.print("Digite o nome do Aluno (ou 0 para voltar): ");
        String nome = sc.nextLine();
        
        if (nome.equals("0")) {
            return;
        }

        System.out.print("Digite o CPF (11 dígitos): ");
        String cpf = sc.nextLine();

        Aluno novo = Aluno.getInstance(nome, cpf);
        
        if (novo != null && sistema.cadastroAluno(novo)) {
            System.out.println("Aluno cadastrado! id: " + novo.getNumero());
        } else {
            System.out.println("ERRO: Falha ao cadastrar!");
        }
    }

    private void fichaDisciplina(Sistema sistema) {
        System.out.print("Digite o nome da Disciplina: ");
        String nome = sc.nextLine();
        System.out.print("Nome do Professor: ");
        String professor = sc.nextLine();

        Disciplina nova = Disciplina.getInstance(nome, professor);
        
        if (nova != null && sistema.cadastroDisciplina(nova)) {
            System.out.println("Disciplina cadastrada! id: " + nova.getId());
        } else {
            System.out.println("ERRO: Falha ao cadastrar!");
        }
    }

    private void fichaTurma(Sistema sistema) {
        System.out.print("Ano da Turma: ");
        int ano = sc.nextInt();
        System.out.print("Quantidade de Vagas: ");
        int vagas = sc.nextInt();
        sc.nextLine();

        Turma nova = Turma.getInstance(ano, vagas);
        
        if (nova != null && sistema.cadastroTurma(nova)) {
            System.out.println("Turma cadastrada!");
        } else {
            System.out.println("ERRO: Falha ao cadastrar!");
        }
    }

    private void adicionarDisciplinaNaTurma(Sistema sistema) {
        System.out.print("Ano da Turma: ");
        int ano = sc.nextInt();
        sc.nextLine();
        int idxTurma = sistema.buscaTurma(ano);

        if (idxTurma == -1) {
            System.out.println("ERRO: Turma nao encontrada!");
            return;
        }

        System.out.print("Nome da Disciplina a adicionar: ");
        String nomeDisc = sc.nextLine();
        int idxDisc = sistema.buscaDisciplina(nomeDisc);

        if (idxDisc == -1) {
            System.out.println("ERRO: Disciplina nao encontrada!");
            return;
        }

        Disciplina discEncontrada = sistema.getDisciplina(idxDisc);
        Turma turmaEncontrada = sistema.getTurma(idxTurma);

        boolean sucesso = sistema.adicionarDisciplinaNaTurma(discEncontrada, turmaEncontrada);
        if (sucesso) {
            System.out.println("Disciplina vinculada a turma!");
        } else {
            System.out.println("ERRO: Falha ao vincular!");
        }
    }

    private void matricularAluno(Sistema sistema) {
        System.out.print("Nome do Aluno: ");
        String nome = sc.nextLine();
        int idxAluno = sistema.buscaAluno(nome);

        System.out.print("Ano da Turma: ");
        int ano = sc.nextInt();
        sc.nextLine();
        int idxTurma = sistema.buscaTurma(ano);

        if (idxAluno == -1 || idxTurma == -1) {
            System.out.println("ERRO: Falha ao matricular!");
            return;
        }

        System.out.print("Dia da matricula: ");
        int dia = sc.nextInt();
        System.out.print("Mes da matricula: ");
        int mes = sc.nextInt();
        System.out.print("Ano da matricula: ");
        int anoMat = sc.nextInt();
        sc.nextLine();

        LocalDate dataDigitada = LocalDate.of(anoMat, mes, dia);

        Aluno alunoEncontrado = sistema.getAluno(idxAluno);
        Turma turmaEncontrada = sistema.getTurma(idxTurma);

        boolean sucesso = sistema.matricularAlunoEmTurma(alunoEncontrado, turmaEncontrada, dataDigitada);

        if (sucesso) {
            System.out.println("Matrícula efetuada com sucesso!");
        } else {
            System.out.println("ERRO: Falha ao matricular!");
        }
    }

    private void lancarNota(Sistema sistema) {
        System.out.print("Nome do Aluno: ");
        String nomeAluno = sc.nextLine();
        int idxAluno = sistema.buscaAluno(nomeAluno);
        if (idxAluno == -1) {
            System.out.println("ERRO: Aluno não encontrado!");
            return;
        }

        Aluno alunoEncontrado = sistema.getAluno(idxAluno);
        Matricula matDoAluno = alunoEncontrado.getMat();
        
        if (matDoAluno == null) {
            System.out.println("ERRO: aluno não matriculado em turma!");
            return;
        }

        System.out.print("Nome da Disciplina: ");
        String nomeDisc = sc.nextLine();
        int idxDisc = sistema.buscaDisciplina(nomeDisc);
        if (idxDisc == -1) {
            System.out.println("ERRO: Disciplina não encontrada!");
            return;
        }

        Disciplina discEncontrada = sistema.getDisciplina(idxDisc);

        System.out.print("Digite o valor da nota: ");
        double valorNota = sc.nextDouble();
        sc.nextLine();

        boolean sucesso = sistema.lancarNota(discEncontrada, matDoAluno, valorNota);
        if (sucesso) {
            System.out.println("Nota lançada!");
        } else {
            System.out.println("ERRO: Falha ao lançar nota!");
        }
    }

    private void alterarDados(Sistema sistema) {
        System.out.println("\n[1] Alterar Nome de Aluno");
        System.out.println("[2] Alterar Nome de Disciplina");
        System.out.println("[3] Alterar Nota de Aluno");
        System.out.print("Escolha: ");
        int op = sc.nextInt();
        sc.nextLine();

        if (op == 1) {
            System.out.print("Nome atual do Aluno: ");
            String nome = sc.nextLine();
            System.out.print("Novo nome: ");
            String novoNome = sc.nextLine();
            
            if (sistema.alterarNomeAluno(nome, novoNome)) {
                System.out.println("Sucesso!");
            } else {
                System.out.println("Aluno não encontrado.");
            }
            
        } else if (op == 2) {
            System.out.print("Nome atual da Disciplina: ");
            String nome = sc.nextLine();
            System.out.print("Novo nome: ");
            String novoNome = sc.nextLine();
            
            if (sistema.alterarNomeDisciplina(nome, novoNome)) {
                System.out.println("Sucesso!");
            } else {
                System.out.println("Disciplina não encontrada.");
            }
            
        } else if (op == 3) {
            System.out.print("Nome do Aluno: ");
            String nomeAluno = sc.nextLine();
            System.out.print("Nome da Disciplina: ");
            String nomeDisc = sc.nextLine();
            System.out.print("Novo valor da nota: ");
            double novaNota = sc.nextDouble();
            sc.nextLine();
            
            if (sistema.alterarNota(nomeAluno, nomeDisc, novaNota)) {
                System.out.println("Sucesso!");
            } else {
                System.out.println("ERRO: Nota não encontrada ou dados inválidos.");
            }
        }
    }

    private void excluirDados(Sistema sistema) {
        System.out.println("\n[1] Excluir Aluno");
        System.out.println("[2] Excluir Disciplina");
        System.out.println("[3] Excluir Turma");
        System.out.print("Escolha: ");
        int op = sc.nextInt();
        sc.nextLine();

        if (op == 1) {
            System.out.print("Nome do Aluno a remover: ");
            String nome = sc.nextLine();
            
            if (sistema.excluirAluno(nome)) {
                System.out.println("Aluno excluído com sucesso.");
            } else {
                System.out.println("ERRO: Falha ao excluir!");
            }
            
        } else if (op == 2) {
            System.out.print("Nome da Disciplina a remover: ");
            String nome = sc.nextLine();
            
            if (sistema.excluirDisciplina(nome)) {
                System.out.println("Disciplina excluída com sucesso.");
            } else {
                System.out.println("ERRO: Falha ao excluir!");
            }
            
        } else if (op == 3) {
            System.out.print("Ano da Turma a remover: ");
            int ano = sc.nextInt();
            sc.nextLine();
            
            if (sistema.excluirTurma(ano)) {
                System.out.println("Turma excluida com sucesso.");
            } else {
                System.out.println("ERRO: Falha ao excluir! (Verifique se não existem alunos matriculados nela)");
            }
        }
    }

    private void listarTurmas(Sistema sistema) {
        Turma[] vetorTurmas = sistema.getTurmas();
        if (vetorTurmas.length == 0) {
            System.out.println("Nenhuma turma cadastrada.");
            return;
        }

        String formato = "%-10s | %-10s\n";
        System.out.printf(formato, "ANO", "VAGAS");

        for (int i = 0; i < vetorTurmas.length; i++) {
            Turma t = vetorTurmas[i];
            System.out.printf(formato, t.getAno(), t.getVagas());
        }
    }

    private void listarMatriculas(Sistema sistema) {
        Matricula[] vetorMatriculas = sistema.getMatriculas();
        if (vetorMatriculas.length == 0) {
            System.out.println("Nenhuma matricula realizada.");
            return;
        }

        String formato = "%-12s | %-" + larguraColuna + "s | %-15s\n";
        System.out.printf(formato, "DATA", "NOME DO ALUNO", "ANO DA TURMA");

        for (int i = 0; i < vetorMatriculas.length; i++) {
            Matricula m = vetorMatriculas[i];
            System.out.printf(formato, m.getData().toString(), m.getAluno().getNome(), m.getTurma().getAno());
        }
    }

    private void listarAlunosOC(Sistema sistema) {
        Aluno[] vetorAlunos = sistema.getAlunos();
        if (vetorAlunos.length == 0) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        
        String formato = "%-5s | %-" + larguraColuna + "s | %-15s | %-6s\n";
        System.out.printf(formato, "ID", "NOME DO ALUNO", "CPF", "MEDIA");
        
        for (int i = 0; i < vetorAlunos.length; i++) {
            Aluno a = vetorAlunos[i];
            double media = sistema.calcularMediaAluno(a);
            System.out.printf(formato, a.getNumero(), a.getNome(), a.getCpf(), media);
        }
    }

    private void listarAlunosOMd(Sistema sistema) {
        Aluno[] vetorAlunos = sistema.getAlunos();
        if (vetorAlunos.length == 0) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        int total = vetorAlunos.length;
        
        for (int i = 0; i < total - 1; i++) {
            for (int j = 0; j < total - 1 - i; j++) {
                double media1 = sistema.calcularMediaAluno(vetorAlunos[j]);
                double media2 = sistema.calcularMediaAluno(vetorAlunos[j + 1]);
                
                if (media2 > media1) {
                    Aluno temporario = vetorAlunos[j];
                    vetorAlunos[j] = vetorAlunos[j + 1];
                    vetorAlunos[j + 1] = temporario;
                }
            }
        }

        String formato = "%-5s | %-" + larguraColuna + "s | %-15s | %-6s\n";
        System.out.printf(formato, "ID", "NOME DO ALUNO", "CPF", "MEDIA");

        for (int i = 0; i < total; i++) {
            Aluno a = vetorAlunos[i];
            double media = sistema.calcularMediaAluno(a);
            System.out.printf(formato, a.getNumero(), a.getNome(), a.getCpf(), media);
        }
    }

    private void listarDisciplinas(Sistema sistema) {
        Disciplina[] vetorDisciplinas = sistema.getDisciplinas();
        if (vetorDisciplinas.length == 0) {
            System.out.println("Nenhuma disciplina cadastrada.");
            return;
        }
        
        String formato = "%-5s | %-" + larguraColuna + "s | %-" + larguraColuna + "s\n";
        System.out.printf(formato, "ID", "DISCIPLINA", "PROFESSOR");
        
        for (int i = 0; i < vetorDisciplinas.length; i++) {
            Disciplina d = vetorDisciplinas[i];
            System.out.printf(formato, d.getId(), d.getNome(), d.getNomeprofessor());
        }
    }

    private void listarNotasAluno(Sistema sistema) {
        System.out.print("Nome do Aluno: ");
        String nome = sc.nextLine();
        int idxAluno = sistema.buscaAluno(nome);

        if (idxAluno == -1) {
            System.out.println("ERRO: Aluno não encontrado.");
            return;
        }

        Aluno aluno = sistema.getAluno(idxAluno);
        Matricula mat = aluno.getMat();

        if (mat == null) {
            System.out.println("ERRO: Aluno não está matriculado em nenhuma turma.");
            return;
        }

        Turma turma = mat.getTurma();
        Disciplina[] disciplinasTurma = turma.getDisciplinas();

        System.out.println("\n--- BOLETIM DE NOTAS ---");
        System.out.println("ALUNO: " + aluno.getNome());
        System.out.println("TURMA: " + turma.getAno());
        System.out.println("------------------------");

        String formato = "%-" + larguraColuna + "s | %-10s\n";
        System.out.printf(formato, "DISCIPLINA", "NOTA");

        for (int i = 0; i < disciplinasTurma.length; i++) {
            Disciplina d = disciplinasTurma[i];
            if (d != null) {
                double valorNota = sistema.getNotaDoAluno(mat, d);
                if (valorNota == -1.0) {
                    System.out.printf(formato, d.getNome(), "Sem nota");
                } else {
                    System.out.printf(formato, d.getNome(), valorNota);
                }
            }
        }
        System.out.println("------------------------");
        System.out.println("MEDIA FINAL: " + sistema.calcularMediaAluno(aluno));
    }

    private void configurarLargura() {
        System.out.print("Digite a nova largura das tabelas (Atual: " + larguraColuna + "): ");
        larguraColuna = sc.nextInt();
        sc.nextLine();
        System.out.println("Largura atualizada!");
    }
}