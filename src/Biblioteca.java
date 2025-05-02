import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Biblioteca extends JFrame {
    private Queue<Livro> filaLivros = new LinkedList<>();
    private Stack<Livro> historicoRemovidos = new Stack<>();
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


    public Biblioteca() {
        setContentPane(MainPanel);
        setTitle("Biblioteca");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,478);
        setLocationRelativeTo(null);
        setVisible(true);


        Btn_Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              String autor = Txt_Autor.getText();
              String titulo = Txt_Titulo.getText();
              String ano = Txt_Ano.getText();

                if (autor.isEmpty() || titulo.isEmpty() || ano.isEmpty()) {
                    Pn_Text.setText("Preencha todos os campos!");
                    return;
                }

                Livro livro = new Livro(autor, titulo, ano);
                filaLivros.add(livro);

                Pn_Text.setText("Livro adicionado à biblioteca:\n" + livro.toString());

                
                Txt_Autor.setText("");
                Txt_Titulo.setText("");
                Txt_Ano.setText("");


            }
        });
        Btn_Remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filaLivros.isEmpty()) {
                    Pn_Text.setText("Nenhum livro para remover.");
                    return;
                }

                Livro removido = filaLivros.poll();
                historicoRemovidos.push(removido);
                Pn_Text.setText("Livro removido:\n" + removido.toString());

            }
        });
        Btn_Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String autorBusca = Txt_Autor.getText().toLowerCase();
                String tituloBusca = Txt_Titulo.getText().toLowerCase();
                String anoBusca = Txt_Ano.getText().toLowerCase();

                if (autorBusca.isEmpty() && tituloBusca.isEmpty() && anoBusca.isEmpty()) {
                    Pn_Text.setText("Preencha ao menos um campo para buscar.");
                    return;
                }

                StringBuilder resultado = new StringBuilder("Resultados da busca:\n");
                boolean encontrado = false;

                for (Livro livro : filaLivros) {
                    boolean matchAutor = autorBusca.isEmpty() || livro.autor.toLowerCase().contains(autorBusca);
                    boolean matchTitulo = tituloBusca.isEmpty() || livro.titulo.toLowerCase().contains(tituloBusca);
                    boolean matchAno = anoBusca.isEmpty() || livro.ano.toLowerCase().contains(anoBusca);

                    if (matchAutor && matchTitulo && matchAno) {
                        resultado.append(livro.toString()).append("\n");
                        encontrado = true;
                    }
                }

                if (encontrado) {
                    Pn_Text.setText(resultado.toString());
                } else {
                    Pn_Text.setText("Nenhum livro encontrado com os critérios informados.");
                }

            }
        });
        Btn_Historico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (historicoRemovidos.isEmpty()) {
                    Pn_Text.setText("Histórico vazio.");
                    return;
                }

                StringBuilder historico = new StringBuilder("Histórico de Remoções:\n");
                for (Livro livro : historicoRemovidos) {
                    historico.append(livro.toString()).append("\n");
                }

                Pn_Text.setText(historico.toString());

            }
        });
        Btn_Clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Txt_Autor.setText("");
                Txt_Titulo.setText("");
                Txt_Ano.setText("");
                Pn_Text.setText("");

            }
        });
    }
}
