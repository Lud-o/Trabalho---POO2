public class Turma{
    private int ano;
    private int vagas;
    private Disciplina[] disciplinas;
    private Matricula[] matriculas;

    //construtor
    private Turma(int ano, int vagas) {
        this.ano = ano;
        this.vagas = vagas;
        disciplinas = new Disciplina[vagas];
        matriculas = new Matricula[vagas];
    }

    //metodo de fabrica
    public static Turma getInstance(int ano, int vagas) {
        if (ano > 2000 && vagas > 0 ) {
            return new Turma(ano, vagas);
        } else {
            return null;
        }
    }

    public boolean adiciona(Matricula mat) {
        for (int i = 0; i < matriculas.length; i++) {
            if (matriculas[i] == null) {
                matriculas[i] = mat;
                return true;
            }
        }
        return false;
    }

    public boolean adiciona(Disciplina disc) {
        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] == null) {
                disciplinas[i] = disc;
                return true;
            }
        }
        return false;
    }

    public Disciplina[] getDisciplinas() {
        return disciplinas;
    }

    public Matricula[] getMatriculas() {
        return matriculas;
    }

    public int getAno() {
        return ano;
    }

    public int getVagas() {
        return vagas;
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

    
    

    
}