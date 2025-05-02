public class Livro {
    String autor;
    String titulo;
    String ano;

    public Livro (String autor, String titulo, String ano){
        this.autor = autor;
        this.titulo= titulo;
        this.ano= ano;
    }

    public String toString(){
        return autor + " | " +  titulo + " | " + ano;
    }
}
