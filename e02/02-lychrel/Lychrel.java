import java.util.Scanner;

public class Lychrel {
	/*
	*	Pre-condizioni: s e' una stringa che rappresenta un intero in notazione decimale (?) (viene gia' gestito da java.util.Scanner in teoria)
	*	Post-condizioni: restituisce il numero (di tipo long) rappresentato da s
	*/
	public static long fromStringToLong(String s) {
		long n = 0;
		for (int i = 0; i < s.length(); i++) {
            /*
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                throw new IllegalArgumentException();
            }
            */
			n *= 10;
			n += s.charAt(i) - '0';
		}
		return n;
	}

	/*
	*	Post-condizioni: restituisce una rappresentazione,
	*   				 sotto forma di stringa,
	*					 del numero fornito in input
	*/
	public static String fromLongToString(long n) {
		return "" + n;
	}

	/*
	*	ELIMINATA - Pre-condizioni: s non e' un riferimento null
	*	Post-condizioni: restituisce true se s e' palindroma, false altrimenti
	*/
	public static boolean isPalindrome(String s) {
        if (s == null) {
            throw new NullPointerException();
        }
		int len = s.length(); 
		if (len <= 1) return true;
		return s.charAt(0) == s.charAt(len-1) && isPalindrome(s.substring(1, len-1));
	}

	/*
	*	ELIMINATA - Pre-condizioni: s non e' un riferimento null 
	*	Post-condizioni: restituisce il reverse della stringa s (s "capovolta")
	*/
	public static String reverse(String s) {
        if (s == null) {
            throw new NullPointerException();
        }
		int len = s.length(); 
		if (len <= 1) return s;
		return s.charAt(len-1) + reverse(s.substring(1, len-1)) + s.charAt(0);
	}

	/*
	*	ELIMINATA - Pre-condizioni: n e' un numero positivo
	*	Post-condizioni: restituisce il valore successivo nella Sequenza di Lychrel
	*/
	public static long lychrelStep(long n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Il numero inserito deve essere positivo."); //test with 1457097
        }
		return n + fromStringToLong(reverse(fromLongToString(n)));
	}

	/*
	*	ELIMINATA - Pre-condizioni: n non e' un numero di Lychrel
	*	Post-condizioni: stampa la Sequenza di Lychrel a partire da n
	*/
	public static void lychrelSequence(long n) {
        if (isPalindrome(fromLongToString(n))) {
            throw new IllegalArgumentException("n e' gia' un numero di Lychrel.");
        }
		while (!isPalindrome(fromLongToString(n))) {
			System.out.println(n);
			n = lychrelStep(n);
		}	
		System.out.println(n);
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
        lychrelSequence(s.nextLong());
        s.close();
	}
}