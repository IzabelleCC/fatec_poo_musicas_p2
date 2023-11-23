import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
//import java.text.ParseException;
//import lombok.Builder.Default;

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
                    case 1:{
                        String titulo = showInputDialog("Titulo?"); 
                        var musica = new Musica(titulo, 0); 
                        musicaDAO.cadastrar(musica); 
                        showMessageDialog(null, "Musica cadastrada");
                        break;
                    } 
                    case 2:{
                        String titulo = showInputDialog("Titulo?");
                        int nota = parseInt(showInputDialog("Nota?"));
                        musicaDAO.avaliar (new Musica(titulo, nota));
                        showMessageDialog(null, "Musica avaliada");
                        break; 
                    }
                    case 3:
                        musicaDAO.listar();
                        break;
                    case 4:{
                        String titulo = showInputDialog("Titulo?");
                        musicaDAO.remover (new Musica(titulo, 0));
                        showMessageDialog(null, "Musica removida");
                        break; 
                    }
                    case 0: 
                    showMessageDialog(null, "Ate logoo");
                    break; 
                    default: 
                        showMessageDialog(null, "Opcao invalida");
                    break; 
                }
            }
            catch(Exception e){
            e.printStackTrace(); 
            showInputDialog(null, "Nao rolou"); 
            }

        }while(op!=0);
         
    }
}
