import java.util.Scanner;

public class Sistema {
    static Scanner sc = new Scanner(System.in);

    static Aluno[] bancoAlunos = new Aluno[100];// banco de alunos
    static Disciplina[] bancoDisciplinas = new Disciplina[100];// banco de Disciplinas

    // variavel de controle
    static int totalAlunos = 0; // Quantos alunos existem
    static int totalDisciplinas = 0; // Quantas disciplinas existem

    // codigo (autoincremento)
    static int proximoCodigoAluno = 1;
    static int proximoCodigoDisciplina = 1;

    // varavel de controle da largura das colunas
    static int larguraColuna = 25;

    public static void main(String[] args) throws Exception {
        init();
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
            sc.nextLine(); // limpar o buffer

            switch (opcao) {
                case 1:
                    fichaDisciplina();
                    break;

                case 2:
                    fichaAluno();
                    break;

                case 3:
                    matricular();
                    break;

                case 4:
                    alterarDados();
                    break;

                case 5:
                    excluirDados();
                    break;

                case 6:
                    listarDisciplinas();
                    break;

                case 7:
                    listarAlunosOC();// listar na ordem de codigo crescente
                    break;

                case 8:
                    listarAlunosOMd();// listar na ordem de media decrescente
                    break;

                case 9:
                    listarMatriculas();
                    break;

                case 10:
                    System.out.print("Digite a nova largura para as colunas de texto (Atual: " + larguraColuna + "): ");
                    larguraColuna = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Largura atualizada com sucesso!");
                    break;

                case 0:
                    // encerrar o loop.
                    break;

                default:
                    System.out.println("ERRO: Opção inválida! Tente novamente.");
                    break;
            }

        } while (opcao != 0);

        System.out.println("--Sistema Finalizado--");
        System.exit(0);

    }

    // inicio do sistema, cadastro automatico
    public static void init() {
        System.out.println("--Sistema iniciado--");

        // disciplinas
        Disciplina d1 = new Disciplina();
        d1.nome = "Calculo I";
        d1.sigla = "CALC1";
        d1.ano = 2026;
        d1.nomeprofessor = "Pericles";
        cadastroDisciplina(d1);

        Disciplina d2 = new Disciplina();
        d2.nome = "Logica de Programacao";
        d2.sigla = "LOGP";
        d2.ano = 2026;
        d2.nomeprofessor = "Luciano Moreira";
        cadastroDisciplina(d2);

        Disciplina d3 = new Disciplina();
        d3.nome = "Estrutura de Dados";
        d3.sigla = "ED";
        d3.ano = 2026;
        d3.nomeprofessor = "Ana Paula";
        cadastroDisciplina(d3);

        // alinos
        Aluno a1 = new Aluno();
        a1.nome = "Ana Beatriz Silva";
        a1.endereco = "Rua das Flores, 123";
        cadastroAluno(a1);

        Aluno a2 = new Aluno();
        a2.nome = "Bruno Costa Santos";
        a2.endereco = "Avenida Brasil, 456";
        cadastroAluno(a2);

        Aluno a3 = new Aluno();
        a3.nome = "Carla Dias Oliveira";
        a3.endereco = "Praca da Liberdade, 789";
        cadastroAluno(a3);

        Aluno a4 = new Aluno();
        a4.nome = "Daniel Gomes Martins";
        a4.endereco = "Rua Sao Jose, 101";
        cadastroAluno(a4);

        Aluno a5 = new Aluno();
        a5.nome = "Eduarda Lima Souza";
        a5.endereco = "Alameda dos Anjos, 303";
        cadastroAluno(a5);

        System.out.println("--Inicialização concluída--");
    }

    // funcões de cadastro
    public static void cadastroDisciplina(Disciplina nova) {
        nova.codigo = proximoCodigoDisciplina;
        proximoCodigoDisciplina++;

        bancoDisciplinas[totalDisciplinas] = nova;
        totalDisciplinas++;

        System.out.println("Disciplina " + nova.sigla + " cadastrada com sucesso - ID: " + nova.codigo);
    }

    public static void cadastroAluno(Aluno novo) {
        novo.codigo = proximoCodigoAluno;
        proximoCodigoAluno++;

        bancoAlunos[totalAlunos] = novo;
        totalAlunos++;

        System.out.println("Aluno " + novo.nome + " cadastrado com sucesso - ID: " + novo.codigo);
    }

    // verifica nomes iguais,aluno,disciplina e sigla
    public static int buscaAluno(String nome) {
        for (int i = 0; i < totalAlunos; i++) {
            if (bancoAlunos[i].nome.equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return -1;
    }

    public static int buscaDisciplina(String nome) {
        for (int i = 0; i < totalDisciplinas; i++) {
            if (bancoDisciplinas[i].nome.equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean buscanmSigla(String sigla) {
        for (int i = 0; i < totalDisciplinas; i++) {
            if (bancoDisciplinas[i].sigla.equalsIgnoreCase(sigla)) {
                return true;
            }
        }
        return false;
    }

    public static String buscacdSigla(int codigo) {
        for (int i = 0; i < totalDisciplinas; i++) {
            if (bancoDisciplinas[i].codigo == codigo) {
                return bancoDisciplinas[i].sigla;
            }
        }
        return "?";
    }

    // verifica se o limite do vetor foi atingido antes de preencher a ficha
    public static boolean bancoAlunolimite() {
        if (totalAlunos >= bancoAlunos.length) {
            System.out.println("ERRO: O limite de alunos (" + bancoAlunos.length + ") foi atingido!");
            return true;
        }
        return false;
    }

    public static boolean bancoDisciplinalimite() {
        if (totalDisciplinas >= bancoDisciplinas.length) {
            System.out.println("ERRO: O limite de Disciplinas (" + bancoDisciplinas.length + ") foi atingido!");
            return true;
        }
        return false;
    }

    // funções de leitura de ficha
    public static void fichaAluno() {
        if (bancoAlunolimite()) {
            return;// volta pro menu
        }

        Aluno a = new Aluno();
        while (true) {
            System.out.print("Digite o nome do Aluno (voltar - 0):)");
            String nome = sc.nextLine();

            if (nome.equalsIgnoreCase("0")) {
                System.out.println("Voltando...");
                return;// volta pro menu
            }

            if (buscaAluno(nome) != -1) {
                System.out.println("ERRO: Já existe um aluno cadastrado com esse nome!, Digite outro nome.");
            } else {
                a.nome = nome;
                System.out.print("Endereço: ");
                a.endereco = sc.nextLine();
                cadastroAluno(a);
                return;
            }
        }
    }

    public static void fichaDisciplina() {
        if (bancoDisciplinalimite()) {
            return;// volta pro menu
        }

        Disciplina d = new Disciplina();
        while (true) {
            System.out.print("Digite o nome da Disciplina (voltar - 0): ");
            String nome = sc.nextLine();

            if (nome.equalsIgnoreCase("0")) {
                System.out.println("Voltando...");
                return;// volta pro menu
            }

            if (buscaDisciplina(nome) != -1) {
                System.out.println("ERRO: Já existe uma disciplina cadastrado com esse nome!");
            } else {
                d.nome = nome;
                while (true) {
                    System.out.print("Digite a sigla da Disciplina (voltar - 0): ");
                    String sigla = sc.nextLine();

                    if (sigla.equalsIgnoreCase("0")) {
                        System.out.println("Voltando...");
                        break;// volta
                    }

                    if (buscanmSigla(sigla)) {
                        System.out.println("ERRO: Já existe uma disciplina cadastrado com essa sigla!");
                    } else {
                        d.sigla = sigla;

                        System.out.println("Ano: ");
                        d.ano = sc.nextInt();

                        sc.nextLine();// limpar o buffer

                        System.out.println("Nome do Professor: ");
                        d.nomeprofessor = sc.nextLine();
                        cadastroDisciplina(d);
                        return;
                    }
                }
            }
        }
    }

    // função de matricular aluno em uma disciplina
    public static void matricular() {

        while (true) {
            System.out.print("Digite o nome do Aluno (voltar - 0): ");
            String nomeAluno = sc.nextLine();

            if (nomeAluno.equalsIgnoreCase("0")) {
                System.out.println("Voltando...");
                return;// volta pro menu
            }

            int indiceAluno = buscaAluno(nomeAluno);
            if (indiceAluno == -1) {
                System.out.println("Aluno não cadastrado!");
                continue;// volta pro começo
            }

            Aluno aluno = bancoAlunos[indiceAluno];// puxando a fixas do aluno

            if (aluno.qtdDisciplinas >= 10) {
                System.out.println("Limite máximo de disciplinas atingido!");
                return;// voltar para o menu
            }

            while (true) {
                listarDisciplinas();// opções de disciplinas
                System.out.print("Digite o nome da Disciplina (voltar - 0): ");
                String nomeDiscp = sc.nextLine();

                if (nomeDiscp.equalsIgnoreCase("0")) {
                    System.out.println("Voltando...");
                    break;// volta pro menu
                }

                int indiceDisciplina = buscaDisciplina(nomeDiscp);
                if (indiceDisciplina == -1) {
                    System.out.println("Disciplina não cadastrada!");
                    continue;
                }

                Disciplina disciplina = bancoDisciplinas[indiceDisciplina]; // puxando a ficha da disciplina

                // verifica disciplina ja matriculada
                boolean jaMatriculado = false;// considera sem matricula
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

                // matricula
                Matricula novaMatricula = new Matricula();
                novaMatricula.codigoDisciplina = disciplina.codigo;// adicionando codigo da disciplina no objeto

                System.out.print("Digite a nota atual do aluno na disciplina: ");
                novaMatricula.nota = sc.nextDouble();// adicionando nota da disciplina no objeto
                sc.nextLine();// limpar o buffer

                // alocando matricula e aumentando qtdDisciplinas
                aluno.disciplinasMatriculadas[aluno.qtdDisciplinas] = novaMatricula;
                aluno.qtdDisciplinas++;

                System.out.println("--Matrícula Realizada--");
                return;

            }
        }

    }

    // calcula a media do aluno
    public static double calcMedia(Aluno a) {
        if (a.qtdDisciplinas == 0) { // disciplinas do aluno = 0
            return 0.0;
        }

        double somanotas = 0.0;
        for (int i = 0; i < a.qtdDisciplinas; i++) {
            somanotas += a.disciplinasMatriculadas[i].nota;
        }
        return somanotas / a.qtdDisciplinas;
    }

    // função para ordenar o vetor media decrescente
    public static Aluno[] ordenaVetorMd() {
        // vetor que será ordenado
        Aluno[] bancoAlunosOR = new Aluno[totalAlunos];
        for (int i = 0; i < totalAlunos; i++) {
            bancoAlunosOR[i] = bancoAlunos[i];
        }

        // ordenando (bubble sort decrescente)
        for (int i = 0; i < totalAlunos - 1; i++) {
            for (int j = 0; j < totalAlunos - 1; j++) {
                double media = calcMedia(bancoAlunosOR[j]);
                double proxmedia = calcMedia(bancoAlunosOR[j + 1]);

                if (proxmedia > media) {
                    Aluno temp = bancoAlunosOR[j];
                    bancoAlunosOR[j] = bancoAlunosOR[j + 1];
                    bancoAlunosOR[j + 1] = temp;
                }
            }
        }
        return bancoAlunosOR;
    }

    // Função listar(tabela)--
    // disciplinas
    public static void listarDisciplinas() {
        if (totalDisciplinas == 0) {
            System.out.println("Nenhuma disciplina foi cadastrada!");
            return;// volta pro menu
        }

        System.out.println();
        String formatoCabecalho = "%-" + larguraColuna + "s | %-10s | %-5s | %-" + larguraColuna + "s\n";
        System.out.printf(formatoCabecalho, "NOME DA DISCIPLINA", "SIGLA", "ID", "PROFESSOR");
        System.out.println();

        for (int i = 0; i < totalDisciplinas; i++) {
            Disciplina d = bancoDisciplinas[i];
            String formatoLinha = "%-" + larguraColuna + "s | %-10s | %-5d | %-" + larguraColuna + "s\n";
            System.out.printf(formatoLinha, d.nome, d.sigla, d.codigo, d.nomeprofessor);
        }
        System.out.println();
    }

    // alunos ordem codigo
    public static void listarAlunosOC() {
        // String traco = "-".repeat(60); // Resultado: "------"
        if (totalAlunos == 0) {
            System.out.println("Nenhum Aluno está cadastrado!");
            return;// volta pro menu
        }

        System.out.println();
        String formatoCabecalho = "%-5s | %-" + larguraColuna + "s | %-12s | %-" + larguraColuna + "s | %-4s | %-"
                + larguraColuna + "s\n";
        System.out.printf(formatoCabecalho, "ID", "ALUNO", "QTD. DISCIP", "DISCIPLINAS", "MÉDIA", "ENDEREÇO");
        System.out.println();

        for (int i = 0; i < totalAlunos; i++) {
            Aluno a = bancoAlunos[i];

            double media = calcMedia(a);
            String listaSiglas = "";

            if (a.qtdDisciplinas == 0) {
                listaSiglas = "Nenhuma";
            } else {
                for (int j = 0; j < a.qtdDisciplinas; j++) {
                    Matricula m = a.disciplinasMatriculadas[j];// m = vetor disciplinasMatriculadas de Alunos
                    String sigla = buscacdSigla(m.codigoDisciplina); // busca a sigla pelo codigo encontrado no objeto
                                                                     // Matricula de 1 aluno
                    listaSiglas += sigla + "(" + m.nota + ") - ";
                }

            }

            String formatoLinha = "%-5s | %-" + larguraColuna + "s | %-12s | %-" + larguraColuna + "s | %-4s | %-"
                    + larguraColuna + "s\n";
            System.out.printf(formatoLinha, a.codigo, a.nome, a.qtdDisciplinas, listaSiglas, media, a.endereco);
        }
        System.out.println();
    }

    // alunos ordem media decrescente
    public static void listarAlunosOMd() {
        // String traco = "-".repeat(60); // Resultado: "------"
        if (totalAlunos == 0) {
            System.out.println("Nenhum Aluno está cadastrado!");
            return;// volta pro menu
        }

        System.out.println();
        String formatoCabecalho = "%-5s | %-" + larguraColuna + "s | %-12s | %-" + larguraColuna + "s | %-4s | %-"
                + larguraColuna + "s\n";
        System.out.printf(formatoCabecalho, "ID", "ALUNO", "QTD. DISCIP", "DISCIPLINAS", "MÉDIA", "ENDEREÇO");
        System.out.println();

        Aluno[] bancoAlunosOR = ordenaVetorMd();

        for (int i = 0; i < totalAlunos; i++) {
            Aluno a = bancoAlunosOR[i];

            double media = calcMedia(a);
            String listaSiglas = "";

            if (a.qtdDisciplinas == 0) {
                listaSiglas = "Nenhuma";
            } else {
                for (int j = 0; j < a.qtdDisciplinas; j++) {
                    Matricula m = a.disciplinasMatriculadas[j];// m = vetor disciplinasMatriculadas de Alunos
                    String sigla = buscacdSigla(m.codigoDisciplina); // busca a sigla pelo codigo encontrado no objeto
                                                                     // Matricula de 1 aluno
                    listaSiglas += sigla + "(" + m.nota + ") - ";
                }

            }

            String formatoLinha = "%-5s | %-" + larguraColuna + "s | %-12s | %-" + larguraColuna + "s | %-4s | %-"
                    + larguraColuna + "s\n";
            System.out.printf(formatoLinha, a.codigo, a.nome, a.qtdDisciplinas, listaSiglas, media, a.endereco);
        }
        System.out.println();
    }

    // matricula por Disciplina
    public static void listarMatriculas() {
        if (totalDisciplinas == 0) {
            System.out.println("Nenhuma disciplina foi cadastrada ainda!");
            return;
        }

        //
        String formatoCabecalho = "%-5s | %-" + larguraColuna + "s | %-7s | %-3s\n";
        System.out.printf(formatoCabecalho, "ID", "DISCIPLINA", "SIGLA", "QTD. DE ALUNOS");
        System.out.println();

        for (int i = 0; i < totalDisciplinas; i++) {
            Disciplina d = bancoDisciplinas[i];
            int contadorAlunos = 0;

            for (int j = 0; j < totalAlunos; j++) {
                Aluno a = bancoAlunos[j];

                for (int k = 0; k < a.qtdDisciplinas; k++) {
                    if (a.disciplinasMatriculadas[k].codigoDisciplina == d.codigo) {
                        contadorAlunos++;
                        break;
                    }
                }
            }

            // Imprime a tabela
            String formatoLinha = "%-5d | %-" + larguraColuna + "s | %-7s | %-3d\n";
            System.out.printf(formatoLinha, d.codigo, d.nome, d.sigla, contadorAlunos);
        }
        System.out.println();

    }

    // Funções de Alterar--
    // menu alterar dados
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
            sc.nextLine(); // limpar o buffer

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

    // Disciplina
    public static void alterarDisciplina() {
        if (totalDisciplinas == 0) {
            System.out.println("Não existem disciplinas cadastradas!");
            return;// volta pro menu
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
            sc.nextLine(); // limpar buffer

            if (op == 0) {
                System.out.println("Voltando...");
                continue;
            } else if (op < 0 || op > 4) {
                System.out.println("ERRO: Opção inválida!");
                continue;
            }

            // busca disciplina
            System.out.print("\nDigite o nome atual da disciplina: ");
            String nomeAtual = sc.nextLine();
            int indice = buscaDisciplina(nomeAtual);

            if (indice == -1) {
                System.out.println("ERRO: Disciplina não encontrada!");
                continue;
            }

            switch (op) {
                case 1:
                    System.out.print("Digite o NOVO Nome para a disciplina: ");
                    bancoDisciplinas[indice].nome = sc.nextLine();
                    System.out.println("SUCESSO: Nome da disciplina atualizado!");
                    break;

                case 2:
                    System.out.print("Digite a NOVA Sigla: ");
                    bancoDisciplinas[indice].sigla = sc.nextLine();
                    System.out.println("SUCESSO: Sigla atualizada!");
                    break;

                case 3:
                    System.out.print("Digite o NOVO Ano: ");
                    bancoDisciplinas[indice].ano = sc.nextInt();
                    sc.nextLine(); // limpar buffer
                    System.out.println("SUCESSO: Ano atualizado!");
                    break;

                case 4:
                    System.out.print("Digite o NOVO Nome do Professor: ");
                    bancoDisciplinas[indice].nomeprofessor = sc.nextLine();
                    System.out.println("SUCESSO: Professor atualizado!");
                    break;
            }

        } while (op != 0);
    }

    // Aluno
    public static void alterarAluno() {
        if (totalAlunos == 0) {
            System.out.println("Não existem alunos cadastrados!");
            return;// volta pro menu
        }

        System.out.print("Digite o nome do aluno que deseja alterar: ");
        String nome = sc.nextLine();

        int indiceAluno = buscaAluno(nome);
        if (indiceAluno == -1) {
            System.out.println("Aluno não cadastrado!");
            return;// volta pro menu principal
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
            sc.nextLine(); // limpar o buffer

            switch (op) {
                case 1:
                    System.out.print("\nDigite o nome atual do aluno: ");
                    String nomeAntigo = sc.nextLine();
                    int indiceNome = buscaAluno(nomeAntigo);

                    if (indiceNome == -1) {
                        System.out.println("ERRO: Aluno não encontrado!");
                    } else {
                        System.out.print("Digite o NOVO Nome: ");
                        bancoAlunos[indiceNome].nome = sc.nextLine();
                        System.out.println("SUCESSO: Nome atualizado!");
                    }
                    break;

                case 2:
                    System.out.print("\nDigite o nome do aluno: ");
                    String nomeEnd = sc.nextLine();
                    int indiceEnd = buscaAluno(nomeEnd);

                    if (indiceEnd == -1) {
                        System.out.println("ERRO: Aluno não encontrado!");
                    } else {
                        System.out.print("Digite o NOVO Endereço: ");
                        bancoAlunos[indiceEnd].endereco = sc.nextLine();
                        System.out.println("SUCESSO: Endereço atualizado!");
                    }
                    break;

                case 3:
                    alterarMatricula(); // Aquela função completinha que já criamos!
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

    // matricula
    public static void alterarMatricula() {
        while (true) {
            System.out.print("Digite o nome do Aluno (voltar - 0): ");
            String nome = sc.nextLine();

            if (nome.equals("0")) {
                System.out.println("Voltando...");
                return;
            }

            int indiceAluno = buscaAluno(nome);
            if (indiceAluno == -1) {
                System.out.println("ERRO: Aluno não encontrado!");
                continue;
            }

            Aluno aluno = bancoAlunos[indiceAluno];
            if (aluno.qtdDisciplinas == 0) {
                System.out.println("Nenhuma Matricula encontrada!");
                continue;
            }

            System.out.println("Matricula de " + aluno.nome);
            for (int i = 0; i < aluno.qtdDisciplinas; i++) {
                Matricula m = aluno.disciplinasMatriculadas[i];
                String sigla = buscacdSigla(m.codigoDisciplina);

                // menu : [1] CALC1 - Nota Atual: 8.5
                System.out.printf("[%d] %s - Nota Atual: %.2f\n", (i + 1), sigla, m.nota);
            }

            while (true) {
                System.out.print("Escolha o numero da materia que deseja alterar, caso queira voltar digite 0: ");
                int escolha = sc.nextInt();
                sc.nextLine(); // limpar buffer

                if (escolha == 0) {
                    System.out.println("Voltando...");
                    break;// volta
                }

                if (escolha < 1 || escolha > aluno.qtdDisciplinas) {
                    System.out.println("ERRO: Opção inválida!");
                    continue;
                }

                System.out.print("Digite a NOVA Nota para a disciplina: ");
                double novaNota = sc.nextDouble();
                sc.nextLine(); // limpar buffer

                aluno.disciplinasMatriculadas[escolha - 1].nota = novaNota;

                System.out.println("SUCESSO: Nota atualizada!");
                return;
            }
        }
    }

    // função excluir aluno
    public static void excluirAluno() {
        while (true) {
            System.out.print("Digite o nome exato do aluno que deseja excluir (ou 0 para voltar): ");
            String nome = sc.nextLine();

            if (nome.equals("0")) {
                System.out.println("Voltando...");
                return; // Sai da função inteira e volta pro menu
            }

            int indice = buscaAluno(nome);

            if (indice == -1) {
                System.out.println("ERRO: Aluno não encontrado! Tente novamente.");
                continue; // 
            }

            // 1ª Trava: Confirmação
            System.out.print("ATENÇÃO: Tem certeza que deseja excluir '" + bancoAlunos[indice].nome + "'? (S/N): ");
            String confirmacao = sc.nextLine();

            if (!confirmacao.equalsIgnoreCase("S")) {
                System.out.println("Exclusão cancelada.");
                return;
            }

            // 2. Puxando a fila para frente
            for (int i = indice; i < totalAlunos - 1; i++) {
                bancoAlunos[i] = bancoAlunos[i + 1];
            }

            totalAlunos--;
            System.out.println("SUCESSO: Aluno excluído permanentemente!");
            return; // 
        }
    }

    // função excluir disciplina
    public static void excluirDisciplina() {
        while (true) {
            System.out.print("Digite o nome exato da disciplina que deseja excluir (ou 0 para voltar): ");
            String nome = sc.nextLine();

            if (nome.equals("0")) {
                System.out.println("Voltando...");
                return;
            }

            int indice = buscaDisciplina(nome);

            if (indice == -1) {
                System.out.println("ERRO: Disciplina não encontrada! Tente novamente.");
                continue; 
            }

            Disciplina d = bancoDisciplinas[indice];

            // 1ª Trava de Segurança: A Verificação de Matrículas
            boolean temGenteMatriculada = false;

            for (int i = 0; i < totalAlunos; i++) {
                Aluno a = bancoAlunos[i];
                for (int j = 0; j < a.qtdDisciplinas; j++) {
                    if (a.disciplinasMatriculadas[j].codigoDisciplina == d.codigo) {
                        temGenteMatriculada = true;
                        break;
                    }
                }
                if (temGenteMatriculada) {
                    break;
                }
            }

            if (temGenteMatriculada) {
                System.out.println("ERRO GRAVE: Não é possível excluir a disciplina '" + d.nome + "'.");
                System.out.println("Motivo: Existem alunos matriculados nela!");
                return; 
            }

            
            System.out.print("ATENÇÃO: Tem certeza que deseja excluir '" + d.nome + "'? (S/N): ");
            String confirmacao = sc.nextLine();

            if (!confirmacao.equalsIgnoreCase("S")) {
                System.out.println("Exclusão cancelada.");
                return; // Sai do loop
            }

            
            for (int i = indice; i < totalDisciplinas - 1; i++) {
                bancoDisciplinas[i] = bancoDisciplinas[i + 1];
            }

            totalDisciplinas--;
            System.out.println("SUCESSO: Disciplina excluída permanentemente!");
            return; 
        }
    }

    // menu excluir dados
    public static void excluirDados() {
        int op = -1;
        do {
            System.out.println("\n┌──────────────────────────────────────────┐");
            System.out.println("│              EXCLUIR DADOS               │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  [1] Excluir Aluno                       │");
            System.out.println("│  [2] Excluir Disciplina                  │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  [0] Voltar ao Menu Principal            │");
            System.out.println("└──────────────────────────────────────────┘");
            System.out.print("Escolha o que deseja excluir: ");

            op = sc.nextInt();
            sc.nextLine(); // limpar buffer

            if (op == 1) {
                excluirAluno();
            } else if (op == 2) {
                excluirDisciplina();
            } else if (op == 0) {
                System.out.println("Voltando...");
            } else {
                System.out.println("ERRO: Opção inválida!");
            }
        } while (op != 0);
    }

}