import java.time.LocalDate;

public class Matricula {
    //int codigoDisciplina;
    //double nota;
    private LocalDate data;
    private Turma turma;
    private Aluno aluno;

    //construtor
    private Matricula(LocalDate data, Turma turma, Aluno aluno) {
        this.data = data;
        this.turma = turma;
        this.aluno = aluno;
    }

    //metodo de fabrica
    public static Matricula getInstance(LocalDate data, Turma turma, Aluno aluno) {
        if (data != null && turma != null && aluno != null) {
            return new Matricula(data, turma, aluno);
        } else {
            return null;
        }
    }
    
    public LocalDate getData() {
        return data;
    }
    
    public Turma getTurma() {
        return turma;
    }
    
    public Aluno getAluno() {
        return aluno;
    }
    

    
}
