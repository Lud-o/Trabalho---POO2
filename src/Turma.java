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

    

    
}