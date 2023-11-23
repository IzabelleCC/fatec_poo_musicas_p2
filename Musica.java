import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter

@RequiredArgsConstructor

public class Musica {
    private int codigo; 
    private final String titulo; 
    private final int avaliacao; 
    private int ativo;

    @Override
    public String toString() {
        return String.format(
            "A musica se chama %s. A sua nota � %d.\n",
            titulo, avaliacao
        );
    }
}

