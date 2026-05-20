public class Aluno {
    private Matricula mat;
    private String nome;
    private String cpf;
    //String endereco;

    // O vetor (disciplinas matriculadas + Nota)
    //Matricula[] disciplinasMatriculadas = new Matricula[10]; 
    
    // Controle de quantas disciplinas matriculadas
    //int qtdDisciplinas = 0;

    public Matricula getMat() {
        return mat;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
}
