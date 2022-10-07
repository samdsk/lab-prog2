import java.util.HashMap; 
 import java.util.Iterator; 
 import java.util.Map; 
 import java.util.Objects; 
  
 /** 
  * Classe concreta che rappresenta un multiset e implementa l'interfaccia 
  * StringMultiSet. 
  */ 
 public class MapStringMultiSet implements StringMultiSet { 
  
     /* 
      * RI: la stringa che rappresenta un elemento non può essere vuota. 
      */ 
  
     // CAMPI 
     private Map<String, Integer> elementi; 
  
     // COSTRUTTORI 
  
     /* 
      * Costruisce un multiset vuoto 
      */ 
     public MapStringMultiSet() { 
         this.elementi = new HashMap<String, Integer>(); 
     } 
  
     // METODI 
  
     /* 
      * Aggiunge l'elemento s a this e ne restituisce la molteplicità 
      * Solleva NullPointerException se s è null 
      */ 
     public int add(String s) { 
         Objects.requireNonNull(s, "Il nome dell'elemento non può essere null"); 
         Integer valoreAttuale = elementi.get(s); 
         if (valoreAttuale == null) { 
             elementi.put(s, 1); 
         } else { 
             elementi.replace(s, valoreAttuale + 1); 
         } 
         return elementi.get(s); 
     } 
  
     /* 
      * Rimuove l'elemento s da this e restituisce la molteplicità di s prima della 
      * rimozione 
      * Solleva NullPointerException se s è null 
      */ 
     public int remove(String s) { 
         Objects.requireNonNull(s, "Il nome dell'elemento non può essere null"); 
         int mult = this.multiplicity(s); 
         elementi.remove(s); 
         return mult; 
     } 
  
     /* 
      * Restituisce true se this contiene l'elemento s, false altrimenti 
      * Solleva NullPointerException se s è null 
      */ 
     public boolean contains(String s) { 
         Objects.requireNonNull(s, "Il nome dell'elemento non può essere null"); 
         return elementi.containsKey(s); 
     } 
  
     /* 
      * Restituisce la molteplicità di s 
      * Solleva NullPointerException se s è null 
      */ 
     public int multiplicity(String s) { 
         Objects.requireNonNull(s, "Il nome dell'elemento non può essere null"); 
         if (elementi.containsKey(s)) { 
             return elementi.get(s); 
         } else { 
             return 0; 
         } 
     } 
  
     /* 
      * Restituisce la cardinalità di this 
      */ 
     public int size() { 
         int cardinalità = 0; 
         for (String elemento : elementi.keySet()) { 
             cardinalità += elementi.get(elemento); 
         } 
         return cardinalità; 
     } 
  
     /* 
      * Restituisce l'insieme ottenuto come unione tra o e this 
      * Solleva NullPointerException se o è null 
      */ 
     public StringMultiSet union(StringMultiSet o) { 
         Objects.requireNonNull(o, "Il multiset o non può essere null"); 
         MapStringMultiSet U = new MapStringMultiSet(); 
  
         // itero tra gli elementi di this 
         for (String elemento : elementi.keySet()) { 
             int molteplicitàMax = this.multiplicity(elemento); 
             if (o.contains(elemento)) { 
                 if (o.multiplicity(elemento) > molteplicitàMax) 
                     molteplicitàMax = o.multiplicity(elemento); 
             } 
             for (int i = 0; i < molteplicitàMax; i++) { 
                 U.add(elemento); 
             } 
         } 
  
         // itero tra gli elementi di o 
         for (String elemento : o) { 
             if (U.contains(elemento)) 
                 continue; 
             int molteplicità = o.multiplicity(elemento); 
             for (int i = 0; i < molteplicità; i++) { 
                 U.add(elemento); 
             } 
         } 
         return U; 
  
     } 
  
     /* 
      * Restituisce l'insieme ottenuto come intersezione tra o e this 
      * Solleva NullPointerException se o è null 
      */ 
     public StringMultiSet intersection(StringMultiSet o) { 
         Objects.requireNonNull(o, "Il multiset o non può essere null"); 
         ArrayStringMultiSet U = new ArrayStringMultiSet(); 
  
         for (String elemento : elementi.keySet()) { 
             int molteplicitàMin = this.multiplicity(elemento); 
             if (o.contains(elemento)) { 
                 if (o.multiplicity(elemento) < this.multiplicity(elemento)) { 
                     molteplicitàMin = o.multiplicity(elemento); 
                 } 
             } 
             for (int i = 0; i < molteplicitàMin; i++) { 
                 U.add(elemento); 
             } 
         } 
         return U; 
     } 
  
     public Iterator<String> iterator() { 
         return elementi.keySet().iterator(); 
     } 
  
     @Override 
     public String toString() { 
         String s = ""; 
         int count = 1; 
         for (String elemento : elementi.keySet()) { 
             s += elemento + ": " + this.multiplicity(elemento); 
             if (count != elementi.size()) { 
                 s += ", "; 
             } else { 
                 s += "}"; 
             } 
             count++; 
  
         } 
         return s; 
     } 
 } 