
import java.util.ArrayList;
import java.util.Iterator;



public class TabelaHashEncadeada {
    private ArrayList<Aluno>[] tabela;
    private final int CAPACIDADED = 20;

    public TabelaHashEncadeada(){
        this.tabela = new ArrayList[CAPACIDADED];
    }

    public TabelaHashEncadeada(int capacidade) {
        this.tabela = new ArrayList[capacidade];
    }

    private int hashMultiplicacao(Integer chave) {
        double a = 0.617648934;
        double hash = chave*a;
        hash = (hash % 1) * this.tabela.length;
        return (int)hash;        
    }


    public Aluno getAluno(int chave){
        int hash = this.hashMultiplicacao(chave);

        ArrayList<Aluno> alunos = tabela[hash];

        if (alunos == null) {
            return null;

        }
        for(Aluno aluno: alunos){
            if(aluno.getMatricula() == chave){
                return aluno;
            }
        }
        return null;

    }


    public void put(int chave, Aluno valor){
        int hash = this.hashMultiplicacao(chave);
        ArrayList<Aluno> alunos = tabela[hash];

        if (alunos == null) {
            alunos = new ArrayList<Aluno>();
            alunos.add(valor);
            tabela[hash] = alunos;
        }else{
            for(Aluno aluno:alunos){
                if (aluno.getMatricula() == chave) {
                    alunos.set(aluno.getMatricula(), valor);
                    return;
                }
            }
            alunos.add(valor);
        }
    }


    public Aluno remove(int chave){
        int hash = hashMultiplicacao(chave);
        ArrayList<Aluno> alunos = tabela[hash];

        if (alunos == null) {
            return null;
        }
        Iterator<Aluno> it = alunos.iterator();
        Aluno atual = null;

        while (it.hasNext()) {
            atual = it.next();
            if(atual.getMatricula().equals(chave)){
                it.remove();
                return atual;

            }
            
        }
        return atual;



    }

    


}




