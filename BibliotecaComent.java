// Importações necessárias para a interface gráfica e manipulação de eventos
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// Classe principal que herda de JFrame, representando a janela da aplicação
public class Biblioteca extends JFrame {

    // Fila que representa os livros atualmente na biblioteca
    private Queue<Livro> filaLivros = new LinkedList<>();

    // Pilha que armazena o histórico de livros removidos
    private Stack<Livro> historicoRemovidos = new Stack<>();

    // Componentes da interface gráfica
    private JTextField Txt_Autor;
    private JTextField Txt_Titulo;
    private JTextField Txt_Ano;
    private JButton Btn_Add;
    private JButton Btn_Remove;
    private JButton Btn_Buscar;
    private JButton Btn_Historico;
    private JButton Btn_Clean;
    private JLabel Lbl_Name;
    private JLabel Lbl_Ano;
    private JLabel Lbl_Titulo;
    private JTextPane Pn_Text;
    private JPanel MainPanel;

    // Construtor da classe
    public Biblioteca() {
        // Configurações da janela principal
        setContentPane(MainPanel);
        setTitle("Biblioteca");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 478);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true);

        // Ação do botão "Adicionar"
        Btn_Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String autor = Txt_Autor.getText();
                String titulo = Txt_Titulo.getText();
                String ano = Txt_Ano.getText();

                // Verifica se todos os campos estão preenchidos
                if (autor.isEmpty() || titulo.isEmpty() || ano.isEmpty()) {
                    Pn_Text.setText("Preencha todos os campos!");
                    return;
                }

                // Cria um novo livro e adiciona à fila
                Livro livro = new Livro(autor, titulo, ano);
                filaLivros.add(livro);

                // Exibe mensagem de sucesso
                Pn_Text.setText("Livro adicionado à biblioteca:\n" + livro.toString());

                // Limpa os campos de entrada
                Txt_Autor.setText("");
                Txt_Titulo.setText("");
                Txt_Ano.setText("");
            }
        });

        // Ação do botão "Remover"
        Btn_Remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filaLivros.isEmpty()) {
                    Pn_Text.setText("Nenhum livro para remover.");
                    return;
                }

                // Remove o primeiro livro da fila e o armazena no histórico
                Livro removido = filaLivros.poll();
                historicoRemovidos.push(removido);

                // Exibe mensagem informando o livro removido
                Pn_Text.setText("Livro removido:\n" + removido.toString());
            }
        });

        // Ação do botão "Buscar"
        Btn_Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String autorBusca = Txt_Autor.getText().toLowerCase();
                String tituloBusca = Txt_Titulo.getText().toLowerCase();
                String anoBusca = Txt_Ano.getText().toLowerCase();

                // Verifica se pelo menos um campo foi preenchido
                if (autorBusca.isEmpty() && tituloBusca.isEmpty() && anoBusca.isEmpty()) {
                    Pn_Text.setText("Preencha ao menos um campo para buscar.");
                    return;
                }

                // StringBuilder para armazenar os resultados da busca
                StringBuilder resultado = new StringBuilder("Resultados da busca:\n");
                boolean encontrado = false;

                // Percorre a fila para encontrar livros que correspondem aos critérios
                for (Livro livro : filaLivros) {
                    boolean matchAutor = autorBusca.isEmpty() || livro.autor.toLowerCase().contains(autorBusca);
                    boolean matchTitulo = tituloBusca.isEmpty() || livro.titulo.toLowerCase().contains(tituloBusca);
                    boolean matchAno = anoBusca.isEmpty() || livro.ano.toLowerCase().contains(anoBusca);

                    if (matchAutor && matchTitulo && matchAno) {
                        resultado.append(livro.toString()).append("\n");
                        encontrado = true;
                    }
                }

                // Exibe os resultados ou mensagem de não encontrado
                if (encontrado) {
                    Pn_Text.setText(resultado.toString());
                } else {
                    Pn_Text.setText("Nenhum livro encontrado com os critérios informados.");
                }
            }
        });

        // Ação do botão "Histórico"
        Btn_Historico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (historicoRemovidos.isEmpty()) {
                    Pn_Text.setText("Histórico vazio.");
                    return;
                }

                // Exibe todos os livros removidos armazenados na pilha
                StringBuilder historico = new StringBuilder("Histórico de Remoções:\n");
                for (Livro livro : historicoRemovidos) {
                    historico.append(livro.toString()).append("\n");
                }

                Pn_Text.setText(historico.toString());
            }
        });

        // Ação do botão "Limpar"
        Btn_Clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpa todos os campos de entrada e a área de texto
                Txt_Autor.setText("");
                Txt_Titulo.setText("");
                Txt_Ano.setText("");
                Pn_Text.setText("");
            }
        });
    }
}