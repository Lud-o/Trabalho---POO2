public class Turma{
    private int ano;
    private int vagas;
    private Disciplina[] disciplinas;
    private Matricula[] matriculas;

    public boolean adiciona(Matricula mat) {
        for (int i = 0; i < matriculas.length; i++) {
            if (mat == null) {
                matriculas[i] = mat;
                return true;
            }
        }
        return false;
    }

    public boolean adiciona(Disciplina disc) {
        for (int i = 0; i < disciplinas.length; i++) {
            if (disc == null) {
                disciplinas[i] = disc;
                return true;
            }
        }
        return false;
    }

    
}