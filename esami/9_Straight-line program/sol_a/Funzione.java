public interface Funzione {
    int numeroArgomenti();
    void argomento(Funzione funzione, int posizione);
    int valuta();

    static Funzione costante(final int valore) {
        return new Funzione() {
          public int numeroArgomenti() { return 0; }
          public void argomento(Funzione funzione, int posizione) {
            throw new UnsupportedOperationException();
          }
          public int valuta() { return valore; }
          public String toString(){return "Costante "+valore;}
        };
    }

  }