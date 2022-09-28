import java.nio.charset.Charset;
import java.util.zip.Adler32;

public class Soluzione {
    public static void main(String[] args) {

    final int dimensione = Integer.parseInt(args[0]);
    final Charset charset = Charset.forName(args[1]);
    final Parametri parametri = new Parametri(dimensione, charset, new Adler32());

    final DestinatarioConc destinatario = new DestinatarioConc(parametri);
    final CanaleConc canale = new CanaleConc(destinatario);
    final SorgenteConc sorgente = new SorgenteConc(canale);
    sorgente.trasmetti(args[2]);
    
    }
}
