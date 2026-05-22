public class Aluno {
    private long numero;
    private String nome;
    private String cpf;
    private Matricula mat;

    private static long totalDeAlunos = 0;
    //String endereco;

    // O vetor (disciplinas matriculadas + Nota)
    //Matricula[] disciplinasMatriculadas = new Matricula[10]; 
    
    // Controle de quantas disciplinas matriculadas
    //int qtdDisciplinas = 0;

    //construtor
    private Aluno(String nome, String cpf) {
        totalDeAlunos++; //incremento
        numero = totalDeAlunos;
        this.nome = nome;
        this.cpf = cpf;
        //this.mat = mat;
    }

    //metodo de fabrica
    public static Aluno getInstance(String nome, String cpf, Matricula mat) {
        if (nome != null && !nome.isEmpty() && cpf != null && cpf.length() == 11) {
            return new Aluno(nome, cpf);
        } else {
            return null;
        }
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
        return;
    }

    public String getCpf() {
        return cpf;
    }

    public Matricula getMat() {
        return mat;
    }
    
}
