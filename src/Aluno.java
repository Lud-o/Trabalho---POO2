public class Aluno {
    int codigo;
    String nome;
    String endereco;

    // O vetor (disciplinas matriculadas + Nota)
    Matricula[] disciplinasMatriculadas = new Matricula[10]; 
    
    // Controle de quantas disciplinas matriculadas
    int qtdDisciplinas = 0;
}
