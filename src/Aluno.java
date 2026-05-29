public class Aluno {
    private long numero;
    private String nome;
    private String cpf;
    private Matricula mat;

    private static long totalDeAlunos = 0;

    private Aluno(String nome, String cpf) {
        totalDeAlunos++;
        numero = totalDeAlunos;
        this.nome = nome;
        this.cpf = cpf;
    }

    private Aluno(Aluno outroAluno) {
        this.numero = outroAluno.getNumero();
        this.nome = outroAluno.getNome();
        this.cpf = outroAluno.getCpf();
        this.mat = outroAluno.getMat();
    }

    public static Aluno getInstance(String nome, String cpf) {
        if (nome != null && !nome.isEmpty() && cpf != null && cpf.length() == 11) {
            return new Aluno(nome, cpf);
        } else {
            return null;
        }
    }

    public static Aluno criarCopia(Aluno outroAluno) {
        if (outroAluno != null) {
            return new Aluno(outroAluno);
        }
        return null;
    }
    
    public long getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public String getCpf() {
        return cpf;
    }

    public Matricula getMat() {
        return mat;
    }

    public boolean setMat(Matricula mat) {
        if (mat != null && this.mat == null) {
            this.mat = mat;
            return true;
        }
        return false;
    }
}