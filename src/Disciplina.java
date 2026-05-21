public class Disciplina {
    private long id;
    private String nome;
    //String sigla;
    //int ano;
    private String nomeprofessor;

    private static long totalDeDisc = 0;

    //construtor
    private Disciplina(String nome, String nomeprofessor) {
        totalDeDisc++; //incremento
        id = totalDeDisc;
        this.nome = nome;
        this.nomeprofessor = nomeprofessor;
    }

    //metodo de fabrica
    public static Disciplina getInstance(String nome, String nomeprofessor) {
        if (nome != null && !nome.isEmpty() && nomeprofessor != null && !nomeprofessor.isEmpty() ) {
            return new Disciplina(nome, nomeprofessor);
        } else {
            return null;
        }
    }

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
