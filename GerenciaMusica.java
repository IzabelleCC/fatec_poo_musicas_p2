import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.util.Collections;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import static java.lang.Integer.parseInt;

public class GerenciaMusica{
    public static void main(String[] args) {
        var musicaDAO = new MusicaDAO(); 
        int op = -1; 
        String menu = "1 - Cadastrar musica\n2 - Avaliar musica\n3 - Ver musicas\n4 - Remover musica\n0 - Sair"; 
        do{
            try{
                op = parseInt(showInputDialog(menu)); 
                switch(op){
                    case 1 ->{
                        String titulo = showInputDialog("Titulo?");
                        var musica = new Musica(titulo.toUpperCase(), 0); 
                        musicaDAO.cadastrar(musica); 
                        showMessageDialog(null, "Musica cadastrada");
                    } 
                    case 2->{
                        String titulo = showInputDialog("Titulo?");
                        int nota = parseInt(showInputDialog("Nota?"));
                        musicaDAO.avaliar (new Musica(titulo.toUpperCase(), nota));
                        showMessageDialog(null, "Musica avaliada");
                    }
                    case 3->{
                        var musicas =  musicaDAO.listar();
                        Collections.sort(musicas, new ComparadorPorAvaliacao());
                        String musicaString = !musicas.isEmpty() ? musicas.stream()
                                                                .map(m -> "Titulo - " + m.getTitulo() + " - nota - " + m.getAvaliacao())
                                                                .collect(Collectors.joining("\n")) : "";
                        JOptionPane.showMessageDialog(null, musicaString);
                    }
                    case 4->{
                        String titulo = showInputDialog("Titulo?");
                        musicaDAO.remover (new Musica(titulo.toUpperCase(), 0));
                        showMessageDialog(null, "Musica removida");
                    }
                    case 0->
                        showMessageDialog(null, "Ate logo !!!");
                    default ->
                        showMessageDialog(null, "Opcao invalida");
                }
            }
            catch(Exception e){
                e.printStackTrace(); 
                showInputDialog(null, "Nao rolou"); 
            }
        }while(op!=0);
    }
}
