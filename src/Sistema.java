// ARQUIVO: Sistema.java
public class Sistema {
    static Aluno[] bancoAlunos = new Aluno[100];
    static Disciplina[] bancoDisciplinas = new Disciplina[100];

    static int totalAlunos = 0;
    static int totalDisciplinas = 0;

    static int proximoCodigoAluno = 1;
    static int proximoCodigoDisciplina = 1;

    static int larguraColuna = 25;

    public static void init() {
        System.out.println("--Sistema iniciado--");

        Disciplina d1 = new Disciplina();
        d1.getInstance("Calculo I", "Pericles");
        //d1.setNome("Calculo I");
        //d1.sigla = "CALC1";
        //d1.ano = 2026;
        //d1.setNomeprofessor("Pericles");
        cadastroDisciplina(d1);

        Disciplina d2 = new Disciplina();
        //d2.nome = "Logica de Programacao";
        //d2.sigla = "LOGP";
        //d2.ano = 2026;
        //d2.nomeprofessor = "Luciano Moreira";
        cadastroDisciplina(d2);

        Disciplina d3 = new Disciplina();
        d3.nome = "Estrutura de Dados";
        d3.sigla = "ED";
        d3.ano = 2026;
        d3.nomeprofessor = "Ana Paula";
        cadastroDisciplina(d3);

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

    public static void fichaAluno(String nome, String endereco) {
        if (bancoAlunolimite()) {
            return;
        }

        Aluno a = new Aluno();
        a.nome = nome;
        a.endereco = endereco;
        cadastroAluno(a);
    }

    public static void fichaDisciplina(String nome, String sigla, int ano, String nomeprofessor) {
        if (bancoDisciplinalimite()) {
            return;
        }

        Disciplina d = new Disciplina();
        d.nome = nome;
        d.sigla = sigla;
        d.ano = ano;
        d.nomeprofessor = nomeprofessor;
        cadastroDisciplina(d);
    }

    public static void matricular(String nomeAluno, String nomeDisciplina, double nota) {
        int indiceAluno = buscaAluno(nomeAluno);
        if (indiceAluno == -1) {
            System.out.println("Aluno não cadastrado!");
            return;
        }

        Aluno aluno = bancoAlunos[indiceAluno];

        if (aluno.qtdDisciplinas >= 10) {
            System.out.println("Limite máximo de disciplinas atingido!");
            return;
        }

        int indiceDisciplina = buscaDisciplina(nomeDisciplina);
        if (indiceDisciplina == -1) {
            System.out.println("Disciplina não cadastrada!");
            return;
        }

        Disciplina disciplina = bancoDisciplinas[indiceDisciplina];

        for (int i = 0; i < aluno.qtdDisciplinas; i++) {
            if (aluno.disciplinasMatriculadas[i].codigoDisciplina == disciplina.codigo) {
                System.out.println("ERRO: O aluno já está matriculado nesta disciplina!");
                return;
            }
        }

        Matricula novaMatricula = new Matricula();
        novaMatricula.codigoDisciplina = disciplina.codigo;
        novaMatricula.nota = nota;

        aluno.disciplinasMatriculadas[aluno.qtdDisciplinas] = novaMatricula;
        aluno.qtdDisciplinas++;

        System.out.println("--Matrícula Realizada--");
    }

    public static double calcMedia(Aluno a) {
        if (a.qtdDisciplinas == 0) {
            return 0.0;
        }

        double somanotas = 0.0;
        for (int i = 0; i < a.qtdDisciplinas; i++) {
            somanotas += a.disciplinasMatriculadas[i].nota;
        }
        return somanotas / a.qtdDisciplinas;
    }

    public static Aluno[] ordenaVetorMd() {
        Aluno[] bancoAlunosOR = new Aluno[totalAlunos];
        for (int i = 0; i < totalAlunos; i++) {
            bancoAlunosOR[i] = bancoAlunos[i];
        }

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

    public static void listarDisciplinas() {
        if (totalDisciplinas == 0) {
            System.out.println("Nenhuma disciplina foi cadastrada!");
            return;
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

    public static void listarAlunosOC() {
        if (totalAlunos == 0) {
            System.out.println("Nenhum Aluno está cadastrado!");
            return;
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
                    Matricula m = a.disciplinasMatriculadas[j];
                    String sigla = buscacdSigla(m.codigoDisciplina);
                    listaSiglas += sigla + "(" + m.nota + ") - ";
                }
            }

            String formatoLinha = "%-5s | %-" + larguraColuna + "s | %-12s | %-" + larguraColuna + "s | %-4s | %-"
                    + larguraColuna + "s\n";
            System.out.printf(formatoLinha, a.codigo, a.nome, a.qtdDisciplinas, listaSiglas, media, a.endereco);
        }
        System.out.println();
    }

    public static void listarAlunosOMd() {
        if (totalAlunos == 0) {
            System.out.println("Nenhum Aluno está cadastrado!");
            return;
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
                    Matricula m = a.disciplinasMatriculadas[j];
                    String sigla = buscacdSigla(m.codigoDisciplina);
                    listaSiglas += sigla + "(" + m.nota + ") - ";
                }
            }

            String formatoLinha = "%-5s | %-" + larguraColuna + "s | %-12s | %-" + larguraColuna + "s | %-4s | %-"
                    + larguraColuna + "s\n";
            System.out.printf(formatoLinha, a.codigo, a.nome, a.qtdDisciplinas, listaSiglas, media, a.endereco);
        }
        System.out.println();
    }

    public static void listarMatriculas() {
        if (totalDisciplinas == 0) {
            System.out.println("Nenhuma disciplina foi cadastrada ainda!");
            return;
        }

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

            String formatoLinha = "%-5d | %-" + larguraColuna + "s | %-7s | %-3d\n";
            System.out.printf(formatoLinha, d.codigo, d.nome, d.sigla, contadorAlunos);
        }
        System.out.println();
    }

    public static void alterarDisciplinaNome(String nomeAtual, String novoNome) {
        int indice = buscaDisciplina(nomeAtual);
        if (indice == -1) {
            System.out.println("ERRO: Disciplina não encontrada!");
            return;
        }
        bancoDisciplinas[indice].nome = novoNome;
        System.out.println("SUCESSO: Nome da disciplina atualizado!");
    }

    public static void alterarDisciplinaSigla(String nomeAtual, String novaSigla) {
        int indice = buscaDisciplina(nomeAtual);
        if (indice == -1) {
            System.out.println("ERRO: Disciplina não encontrada!");
            return;
        }
        bancoDisciplinas[indice].sigla = novaSigla;
        System.out.println("SUCESSO: Sigla atualizada!");
    }

    public static void alterarDisciplinaAno(String nomeAtual, int novoAno) {
        int indice = buscaDisciplina(nomeAtual);
        if (indice == -1) {
            System.out.println("ERRO: Disciplina não encontrada!");
            return;
        }
        bancoDisciplinas[indice].ano = novoAno;
        System.out.println("SUCESSO: Ano atualizado!");
    }

    public static void alterarDisciplinaProfessor(String nomeAtual, String novoProfessor) {
        int indice = buscaDisciplina(nomeAtual);
        if (indice == -1) {
            System.out.println("ERRO: Disciplina não encontrada!");
            return;
        }
        bancoDisciplinas[indice].nomeprofessor = novoProfessor;
        System.out.println("SUCESSO: Professor atualizado!");
    }

    public static void alterarAlunoNome(String nomeAtual, String novoNome) {
        int indice = buscaAluno(nomeAtual);
        if (indice == -1) {
            System.out.println("ERRO: Aluno não encontrado!");
            return;
        }
        bancoAlunos[indice].nome = novoNome;
        System.out.println("SUCESSO: Nome atualizado!");
    }

    public static void alterarAlunoEndereco(String nome, String novoEndereco) {
        int indice = buscaAluno(nome);
        if (indice == -1) {
            System.out.println("ERRO: Aluno não encontrado!");
            return;
        }
        bancoAlunos[indice].endereco = novoEndereco;
        System.out.println("SUCESSO: Endereço atualizado!");
    }

    public static void alterarNotaMatricula(String nomeAluno, int indiceDisciplina, double novaNota) {
        int indice = buscaAluno(nomeAluno);
        if (indice == -1) {
            System.out.println("ERRO: Aluno não encontrado!");
            return;
        }

        Aluno aluno = bancoAlunos[indice];
        if (indiceDisciplina < 1 || indiceDisciplina > aluno.qtdDisciplinas) {
            System.out.println("ERRO: Índice de disciplina inválido!");
            return;
        }

        aluno.disciplinasMatriculadas[indiceDisciplina - 1].nota = novaNota;
        System.out.println("SUCESSO: Nota atualizada!");
    }

    public static void excluirAluno(String nome) {
        int indice = buscaAluno(nome);
        if (indice == -1) {
            System.out.println("ERRO: Aluno não encontrado!");
            return;
        }

        for (int i = indice; i < totalAlunos - 1; i++) {
            bancoAlunos[i] = bancoAlunos[i + 1];
        }

        totalAlunos--;
        System.out.println("SUCESSO: Aluno excluído permanentemente!");
    }

    public static boolean excluirDisciplina(String nome) {
        int indice = buscaDisciplina(nome);
        if (indice == -1) {
            System.out.println("ERRO: Disciplina não encontrada!");
            return false;
        }

        Disciplina d = bancoDisciplinas[indice];

        for (int i = 0; i < totalAlunos; i++) {
            Aluno a = bancoAlunos[i];
            for (int j = 0; j < a.qtdDisciplinas; j++) {
                if (a.disciplinasMatriculadas[j].codigoDisciplina == d.codigo) {
                    System.out.println("ERRO GRAVE: Não é possível excluir a disciplina '" + d.nome + "'.");
                    System.out.println("Motivo: Existem alunos matriculados nela!");
                    return false;
                }
            }
        }

        for (int i = indice; i < totalDisciplinas - 1; i++) {
            bancoDisciplinas[i] = bancoDisciplinas[i + 1];
        }

        totalDisciplinas--;
        System.out.println("SUCESSO: Disciplina excluída permanentemente!");
        return true;
    }

    public static String getDisciplinasMatriculadasString(Aluno a) {
        String listaSiglas = "";
        if (a.qtdDisciplinas == 0) {
            listaSiglas = "Nenhuma";
        } else {
            for (int j = 0; j < a.qtdDisciplinas; j++) {
                Matricula m = a.disciplinasMatriculadas[j];
                String sigla = buscacdSigla(m.codigoDisciplina);
                listaSiglas += sigla + "(" + m.nota + ") - ";
            }
        }
        return listaSiglas;
    }

    public static int getTotalAlunos() {
        return totalAlunos;
    }

    public static int getTotalDisciplinas() {
        return totalDisciplinas;
    }

    public static Aluno getAluno(int index) {
        return bancoAlunos[index];
    }

    public static Disciplina getDisciplina(int index) {
        return bancoDisciplinas[index];
    }

    public static Aluno[] getBancoAlunosOrdenadoPorMedia() {
        return ordenaVetorMd();
    }

    public static int getLarguraColuna() {
        return larguraColuna;
    }

    public static void setLarguraColuna(int nova) {
        larguraColuna = nova;
    }
}