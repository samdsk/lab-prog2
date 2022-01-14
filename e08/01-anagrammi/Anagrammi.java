import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Anagrammi {
    //Overview Anagrammi is a mutable class which given a list of words regroups them into a list of anagrams
    //Es. Map<Word_Signature,Anagrams of Word_Signature>

    /**
     * AF(m) = 
     *  { 
     *      word_sig_i : [anagramma_i,...]
     *      .
     *      .
     *      .
     *      word_sig_j : [anagramma_j,...]
     *  }
     * 
     *  where anagramma_i is the list of anagrams corrisponds to word_signature_i
     * 
     * RI(m) = m != null && for each m.key_i != m.key_j | i != j
     */


    //A map which maps a Word_Signature -> List of it's anagrams
    private Map<String,List<String>> m;
    
    /**Initialize this as empty map */
    public Anagrammi(){
        m = new HashMap<>();
    }
    
    /**
     * Modifies: this
     * Effects: Process a list l of words mapping them according to word_signature
     *          Throws NullPointerException if l == null
     */
    public void ProcessWords(List<Parola> l){
        if(l == null) throw new NullPointerException();
        for (Parola p : l) {
            String key = p.getSig();
            if(m.containsKey(p.getSig())){
                m.get(key).add(p.toString());
            }else{
                List<String> temp = new LinkedList<>();
                temp.add(p.toString());
                m.put(key, temp);
            }
        }
    }

    /**
     * Effects: Returns a list of lists of anagrammi contained in this.m 
     */
    public List<List<String>> getAnagrammi(){
        if(m.isEmpty()) throw new EmptyMapException();        
        List<List<String>> anagrammi = new LinkedList<>();

        for (List<String> temp : m.values()) {
            if(temp.size()>1) anagrammi.add(temp);
        }

        return anagrammi;
    }
    
    /**RI(m) = m != null && for each m.key_i != m.key_j | i != j */
    public boolean repOk(){
        if(m == null) return false;
        
        for(String key1 :m.keySet()){
            int count = 0;
            for (String key2 :m.keySet()) {
                if(key1.equals(key2)) count++;
            }
            if(count>1) return false;
        }

        return true;

    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(o == this) return true;

        Anagrammi a = (Anagrammi) o;

        for(Entry<String,List<String>> temp : a.m.entrySet()){
            String key = temp.getKey();
            if(m.containsKey(key)){
                List<String> l1 = temp.getValue();
                List<String> l2 = m.get(key);
                for (String s: l1) {
                    if(!l2.contains(s)) return false;
                }
            }else return false;
        }

        return true;
    }

    @Override
    public String toString(){
        if(m.isEmpty()){
            return "{}";
        }

        String output = "{\n";
        for(Entry<String,List<String>> temp : m.entrySet()){
            output += temp.getKey();
            output += " : ";
            output += temp.getValue().toString();
            output += "\n";
        }

        return output+="}";
    }

}
