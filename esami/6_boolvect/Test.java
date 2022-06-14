import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNext()){
            String input = s.nextLine();
            String[] split = input.split(" ");
            switch (split[0]) {
                case "S":
                    BoolvectSparsa d = new BoolvectSparsa();
                    for (int i = 0; i < split[3].length(); i++){
                        if (split[3].charAt(i) == 'T'){
                            d.scrivi(i, true);
                        }
                    }
                    if (split[2].equals("T")){
                        d.scrivi(Integer.parseInt(split[1]), true);
                    }else{
                        d.scrivi(Integer.parseInt(split[1]), false);
                    }
                    System.out.println(d.toString());
                    break;
                case "G":
                    break;
                case "&":
                    break;
                case "|":
                    break;
                case "^":
                    break;
                default:
                    System.out.println("Istruzione non riconosciuta, riprovare");
                    break;
            }
        }

    }
}
