public class TestRunner {
    public static void main(String[] args) {
        BoolvectSparsa b = new BoolvectSparsa();
        BoolvectDenso d = new BoolvectDenso();
        String s = "TTTTFFFFTTF";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'T') {
                
                b.scrivi(i, true);
                d.scrivi(i, true);   
            }
        }
        System.out.println(b.getDimensione() + " : " + b.toString());
        System.out.println(d.getDimensione() + " : " + d.toString());
    }
}
