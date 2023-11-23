import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class MusicaDAO {
    public void cadastrar(Musica musica) throws Exception{
        //1 - especificar  o camando sql 
        String sql = "INSERT INTO tb_musica(titulo, ativo) VALUES(?,?)"; 
        //2 - estabelecer uma conexao com o SGBD (PostgresSQL)
        var conexao = ConnectionFactory.conectar(); 
        // 3 - preparar o comando 
        PreparedStatement ps = conexao.prepareStatement(sql); 
        //4 - substituir os eventuais placeholders 
        ps.setString(1, musica.getTitulo());
        ps.setInt(2, 1);
        //5 - executar o comando
        ps.execute(); 
        //6 - fechar os recursos
        ps.close(); 
        conexao.close(); 
    }
    public void avaliar(Musica musica) throws Exception{
        //1 - especificar  o camando sql 
        String sql = "UPDATE tb_musica SET avaliacao=? WHERE titulo=?;";
        //2 - estabelecer uma conexao com o SGBD (PostgresSQL)
        //try-with-resources
        try(
            var conexao = ConnectionFactory.conectar();
        // 3 - preparar o comando 
            var ps = conexao.prepareStatement(sql); 
        ){
            //4 - substituir os eventuais placeholders
            ps.setInt(1, musica.getAvaliacao());
            ps.setString(2, musica.getTitulo());
            //5 - executar o comando
            ps.execute();  
        }
    }
    public void listar() throws Exception{
        //1 - Especificar o comando sql
        String sql = " SELECT titulo, avaliacao FROM tb_musica;";
        //2 - estabelecer uma conexao com o SGBD (PostgresSQL)
        try(
            var conexao = ConnectionFactory.conectar();
        // 3 - preparar o comando 
            var ps = conexao.prepareStatement(sql);            
        ){
            //5 - executar o comando
            try(
                ResultSet rs = ps.executeQuery();
            ){
                //6 - Manipular os dados da tabela resultante
                while(rs.next()){
                    int avaliacao = rs.getInt("avaliacao");
                    String titulo = rs.getString("titulo");
                    var musica = new Musica(titulo, avaliacao);
                    JOptionPane.showMessageDialog(null, musica);
                }
            }

        }
    }
    public void remover(Musica musica) throws Exception{
        //1. Especificar o comando SQL (update)
        var sql = "UPDATE tb_musica SET ativo=? WHERE titulo=?;";
        //2. Estabelecer uma conexão com o banco
        //try-with-resources
        try(
        var conexao = ConnectionFactory.conectar();
        //3. Preparar o comando
        var ps = conexao.prepareStatement(sql);
        ){      
        //4. Substituir os eventuais placeholders
        ps.setInt(1, musica.getAtivo());
        ps.setString(2, musica.getTitulo());
        //5. Executar
        ps.execute();
        }
    }
}

