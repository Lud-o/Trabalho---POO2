public class Disciplina {
    private long id;
    private String nome;
    private String nomeprofessor;

    private static long totalDeDisc = 0;

    private Disciplina(String nome, String nomeprofessor) {
        totalDeDisc++;
        id = totalDeDisc;
        this.nome = nome;
        this.nomeprofessor = nomeprofessor;
    }

    private Disciplina(Disciplina outraDisciplina) {
        this.id = outraDisciplina.getId();
        this.nome = outraDisciplina.getNome();
        this.nomeprofessor = outraDisciplina.getNomeprofessor();
    }

    public static Disciplina getInstance(String nome, String nomeprofessor) {
        if (nome != null && !nome.isEmpty() && nomeprofessor != null && !nomeprofessor.isEmpty() ) {
            return new Disciplina(nome, nomeprofessor);
        } else {
            return null;
        }
    }

    public static Disciplina criarCopia(Disciplina outraDisciplina) {
        if (outraDisciplina != null) {
            return new Disciplina(outraDisciplina);
        }
        return null;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public String getNomeprofessor() {
        return nomeprofessor;
    }

    public void setNomeprofessor(String nomeprofessor) {
        if (nomeprofessor != null && !nomeprofessor.isEmpty()) {
            this.nomeprofessor = nomeprofessor;
        }
    }
}