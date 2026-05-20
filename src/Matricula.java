import java.time.LocalDate;

public class Matricula {
    //int codigoDisciplina;
    //double nota;
    private LocalDate data;
    private Turma turma;
    private Aluno aluno;
    
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public Turma getTurma() {
        return turma;
    }
    public void setTurma(Turma turma) {
        this.turma = turma;
    }
    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    
}
