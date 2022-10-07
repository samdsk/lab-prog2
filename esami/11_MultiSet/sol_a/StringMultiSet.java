/* 
  * Interfaccia che descrive un multiset, ovvero una sorta di insieme in cui ogni elemento  
  * può essere contenuto più di una volta. 
  * La molteplicità di un elemento è il numero di volte che quell'elemento è contenuto nell'insieme. 
  * La cardinalità di un multiset è la somma delle molteplictà dei suoi elementi. 
  * Il supporto di un multiset è l'insieme senza ripetizioni dei suoi elementi. 
  * Un multiset supporta le operazioni di unione ed intersezione. 
 */ 
  
interface StringMultiSet extends Iterable<String> { 
    /* 
     * Aggiunge l'elemento s a this e ne restituisce la molteplicità 
     */ 
    int add(String s); 
 
    /* 
     * Rimuove l'elemento s da this e restituisce la molteplicità di s prima della rimozione 
     */ 
    int remove(String s); 
 
    /* 
     * Restituisce true se this contiene l'elemento s, false altrimenti 
     */ 
    boolean contains(String s); 
 
    /* 
     * Restituisce la molteplicità di s 
     */ 
    int multiplicity(String s); 
 
    /* 
     * Restituisce la cardinalità di this 
     */ 
    int size(); 
     
    /* 
     * Restituisce l'insieme ottenuto come unione tra o e this  
     */ 
    StringMultiSet union(StringMultiSet o); 
 
    /* 
     * Restituisce l'insieme ottenuto come intersezione tra o e this 
     */ 
    StringMultiSet intersection(StringMultiSet o); 
}
