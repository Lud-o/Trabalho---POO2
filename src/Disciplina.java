public class Disciplina {
    private long id;
    private String nome;
    //String sigla;
    //int ano;
    private String nomeprofessor;

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeprofessor() {
    return nomeprofessor;
    }

    public void setNomeprofessor(String nomeprofessor) {
        this.nomeprofessor = nomeprofessor;
    }
    
}
