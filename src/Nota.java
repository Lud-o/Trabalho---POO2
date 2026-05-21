public class Nota {
    private Disciplina disc;
    private Matricula matricula;
    private double valor;

    //contrutor
    private Nota(Disciplina disc, Matricula matricula, double valor) {
        this.disc = disc;
        this.matricula = matricula;
        this.valor = valor;
    }

    //metodo de fabrica
    public static Nota getInstance(Disciplina disc, Matricula matricula, double valor) {
        if (disc != null && matricula != null && valor >= 0) {
            return new Nota(disc, matricula, valor);
            
        } else {
            return null;
        }
    }

    public Disciplina getDisc() {
        return disc;
    }
    
    public Matricula getMatricula() {
        return matricula;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        if (valor >= 0) {
            this.valor = valor;
        }
        return;
    }

    
}
