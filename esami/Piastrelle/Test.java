package Piastrelle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        List<Piastrella> pavimentazioni = new ArrayList<>();
        int[] casa = new int[2];

        while(reader.hasNext()){
            String[] data = reader.nextLine().split(" ");
            
            switch (data[0]) {
                case "Q":                
                    pavimentazioni.add(new PiastrellaQuadrato(Integer.parseInt(data[1]), Integer.parseInt(data[2])));
                    break;
                case "T":                
                    pavimentazioni.add(new PiastrellaTriangolare(Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3])));
                    break;
                case "R":
                    pavimentazioni.add(new PiastrellaRombo(Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3])));            
                    break; 
                case "P":
                    if(data.length==2){
                        pavimentazioni.add(new Pavimentazione(Integer.parseInt(data[1]), pavimentazioni.get(Integer.parseInt(data[2]))));
                        break;
                    }
                    for(int i=1; i<data.length;i+=2){
                        if(!(pavimentazioni.get(i) instanceof Pavimentazione)) throw new IllegalArgumentException("Indice della pavimentazione non corretto");
                        casa[0] += (Integer.parseInt(data[i]) * pavimentazioni.get(i+1).superficie());
                        casa[1] += (Integer.parseInt(data[i]) * pavimentazioni.get(i+1).costo());
                    }

                default:
                    break;
            }
            
        }

        reader.close();

        for (Piastrella p : pavimentazioni) {
            if(p instanceof Pavimentazione){
                System.out.println(p.superficie()+" "+p.costo());
            }
        }

        System.out.println(casa[0]+" "+casa[1]);
    }
    
}
