import java.time.LocalDate;

public class Sistema {
    
    private static Sistema instanciaUnica;

    
    private Aluno[] bancoAlunos = new Aluno[100];
    private Disciplina[] bancoDisciplinas = new Disciplina[100];
    private Turma[] bancoTurmas = new Turma[100];
    private Matricula[] bancoMatriculas = new Matricula[100];
    private Nota[] bancoNotas = new Nota[500];

    // Contadores
    private int totalAlunos = 0;
    private int totalDisciplinas = 0;
    private int totalTurmas = 0;
    private int totalMatriculas = 0;
    private int totalNotas = 0;

    private Sistema() {}

    public static Sistema getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new Sistema();
        }
        return instanciaUnica;
    }

    public void init() {
        Disciplina d1 = Disciplina.getInstance("Calculo I", "Pericles");
        if (d1 != null) {
            cadastroDisciplina(d1);
        }

        Disciplina d2 = Disciplina.getInstance("Logica de Programacao", "Luciano Moreira");
        if (d2 != null) {
            cadastroDisciplina(d2);
        }

        Aluno a1 = Aluno.getInstance("Ana Beatriz Silva", "11122233344");
        if (a1 != null) {
            cadastroAluno(a1);
        }

        Aluno a2 = Aluno.getInstance("Bruno Costa Santos", "55566677788");
        if (a2 != null) {
            cadastroAluno(a2);
        }
    }

    // função de busca
    public int buscaAluno(String nome) {
        for (int i = 0; i < totalAlunos; i++) {
            if (bancoAlunos[i].getNome().equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return -1;
    }

    public int buscaDisciplina(String nome) {
        for (int i = 0; i < totalDisciplinas; i++) {
            if (bancoDisciplinas[i].getNome().equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return -1;
    }

    public int buscaTurma(int ano) {
        for (int i = 0; i < totalTurmas; i++) {
            if (bancoTurmas[i].getAno() == ano) {
                return i;
            }
        }
        return -1;
    }

    // cruds
    public boolean cadastroAluno(Aluno novo) {
        if (totalAlunos >= bancoAlunos.length || novo == null) {
            return false;
        }
        
        if (buscaAluno(novo.getNome()) != -1) {
            return false;
        }

        bancoAlunos[totalAlunos] = novo;
        totalAlunos++;
        return true;
    }

    public boolean cadastroDisciplina(Disciplina nova) {
        if (totalDisciplinas >= bancoDisciplinas.length || nova == null) {
            return false;
        }
        
        if (buscaDisciplina(nova.getNome()) != -1) {
            return false;
        }

        bancoDisciplinas[totalDisciplinas] = nova;
        totalDisciplinas++;
        return true;
    }

    public boolean cadastroTurma(Turma nova) {
        if (totalTurmas >= bancoTurmas.length || nova == null) {
            return false;
        }
        
        bancoTurmas[totalTurmas] = nova;
        totalTurmas++;
        return true;
    }

    public boolean matricularAlunoEmTurma(Aluno aluno, Turma turma, LocalDate data) {
        if (aluno == null || turma == null || totalMatriculas >= bancoMatriculas.length) {
            return false;
        }
        
        Matricula novaMat = Matricula.getInstance(data, turma, aluno);
        if (novaMat == null) {
            return false;
        }

        // tenta matricular se não cancela
        if (!aluno.setMat(novaMat)) {
            return false; 
        }

        bancoMatriculas[totalMatriculas] = novaMat;
        totalMatriculas++;

        turma.adiciona(novaMat);
        return true;
    }

    public boolean lancarNota(Disciplina disc, Matricula mat, double valor) {
        if (disc == null || mat == null || totalNotas >= bancoNotas.length) {
            return false;
        }

        Nota novaNota = Nota.getInstance(disc, mat, valor);
        if (novaNota == null) {
            return false;
        }

        bancoNotas[totalNotas] = novaNota;
        totalNotas++;
        return true;
    }

    // calcula a media de aluno
    public double calcularMediaAluno(Aluno aluno) {
        if (aluno == null || aluno.getMat() == null) {
            return 0.0;
        }
        
        Matricula mat = aluno.getMat();
        double soma = 0.0;
        int quantidadeDeNotas = 0;
        
        for (int i = 0; i < totalNotas; i++) {
            if (bancoNotas[i].getMatricula() == mat) {
                soma = soma + bancoNotas[i].getValor();
                quantidadeDeNotas++;
            }
        }
        
        if (quantidadeDeNotas == 0) {
            return 0;
        }
        
        return soma / quantidadeDeNotas;
    }

    // função de excluir
    public boolean excluirAluno(String nome) {
        int indice = buscaAluno(nome);
        if (indice == -1) {
            return false;
        }

        if (bancoAlunos[indice].getMat() != null) {
            return false;
        }

        for (int i = indice; i < totalAlunos - 1; i++) {
            bancoAlunos[i] = bancoAlunos[i + 1];
        }
        bancoAlunos[totalAlunos - 1] = null;
        totalAlunos--;
        return true;
    }

    public boolean excluirDisciplina(String nome) {
        int indice = buscaDisciplina(nome);
        if (indice == -1) {
            return false;
        }

        Disciplina d = bancoDisciplinas[indice];
        for (int i = 0; i < totalTurmas; i++) {
            Disciplina[] disciplinasDaTurma = bancoTurmas[i].getDisciplinas();
            for (int j = 0; j < disciplinasDaTurma.length; j++) {
                Disciplina discTurma = disciplinasDaTurma[j];
                if (discTurma != null && discTurma.getId() == d.getId()) {
                    Matricula[] matriculasDaTurma = bancoTurmas[i].getMatriculas();
                    if (matriculasDaTurma[0] != null) {
                        return false; 
                    }
                }
            }
        }

        for (int i = indice; i < totalDisciplinas - 1; i++) {
            bancoDisciplinas[i] = bancoDisciplinas[i + 1];
        }
        bancoDisciplinas[totalDisciplinas - 1] = null;
        totalDisciplinas--;
        return true;
    }

    public boolean excluirTurma(int ano) {
        int indice = buscaTurma(ano);
        if (indice == -1) {
            return false;
        }

        // não excluir se tiver aluno
        Matricula[] matriculasDaTurma = bancoTurmas[indice].getMatriculas();
        if (matriculasDaTurma[0] != null) {
            return false; 
        }

        for (int i = indice; i < totalTurmas - 1; i++) {
            bancoTurmas[i] = bancoTurmas[i + 1];
        }
        bancoTurmas[totalTurmas - 1] = null;
        totalTurmas--;
        return true;
    }

    
    public Aluno[] getAlunos() { 
        return bancoAlunos; 
    }
    
    public int getTotalAlunos() { 
        return totalAlunos; 
    }
    
    public Disciplina[] getDisciplinas() { 
        return bancoDisciplinas; 
    }
    
    public int getTotalDisciplinas() { 
        return totalDisciplinas; 
    }
    
    public Turma[] getTurmas() { 
        return bancoTurmas; 
    }
    
    public int getTotalTurmas() { 
        return totalTurmas; 
    }

    public Matricula[] getMatriculas() {
        return bancoMatriculas;
    }

    public int getTotalMatriculas() {
        return totalMatriculas;
    }
}