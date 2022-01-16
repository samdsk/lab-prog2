package Algebretta;


public class Matrice {
    private final VettoreDenso[] matrice;
    private final int dim;

    public Matrice(final int size){
        dim = size;
        matrice = new VettoreDenso[dim];

        for(int i=0;i<dim;i++) matrice[i] = new VettoreDenso(dim);
    }

    public Matrice(Matrice m){
        dim = m.dim;
        matrice = new VettoreDenso[dim];

        for (int i = 0; i < matrice.length; i++){
            matrice[i] = new VettoreDenso(m.matrice[i]);
        }
    }

    public int dim() {
        return this.dim;
    }

    public Matrice prodottoScalare(int alpha){
        Matrice output = new Matrice(dim);

        for (int i = 0; i < dim; i++) {
            output.matrice[i] = this.matrice[i].per(alpha);
        }

        return output;
    }

    public VettoreDenso prodottoVettore(VettoreDenso v){
        int[] temp = new int[dim];
        for (int i = 0; i < dim; i++) {                
            temp[i] = this.matrice[i].prodotto(v);
        }
        return new VettoreDenso(temp);
    }

    public Matrice matriceTrasposta(){
        Matrice output = new Matrice(dim);
        int[] temp_array = new int[dim]; 

        for(int i=0;i<dim;i++){
            for (int j = 0; j < dim; j++) {
                temp_array[j] = matrice[j].val(i);
            }
            output.matrice[i] = new VettoreDenso(temp_array);
        }

        return output;
    }

    public Matrice sommaMatriciale(Matrice m){
        Matrice output = new Matrice(dim);

        for(int i=0;i<dim;i++){
            output.matrice[i] = this.matrice[i].piu(m.matrice[i]);
        }

        return output;
    }

    public Matrice prodottoMatriciale(Matrice m){
        if(m==null) throw new NullPointerException();
        if(m.dim!=this.dim) throw new IllegalArgumentException("Matrici sono di dimensione diversa! this:"+this.dim+" m:"+m.dim);

        Matrice t = m.matriceTrasposta();
        Matrice output = new Matrice(dim);
        int[] temp_array = new int[dim];

        for (int i = 0; i < dim; i++) {
            VettoreDenso a1 = this.matrice[i];
            
            for (int j = 0; j < dim; j++) {
                VettoreDenso a2 = t.matrice[j];
                temp_array[j] = a1.prodotto(a2);
            }

            output.matrice[i] = new VettoreDenso(temp_array);
        }

        return output;

    }

}
