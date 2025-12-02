import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class ProdutoView extends JFrame {

    private ProdutoController controller = new ProdutoController();

    private JTextField txtNome;
    private JTextField txtPreco;
    private JTextField txtQuantidade;
    private JTextArea txtAreaLista;

    public ProdutoView() {
        setTitle("CRUD de Produtos");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("CRUD de Produtos", SwingConstants.CENTER);
        lblTitulo.setBounds(120, 10, 250, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 60, 100, 20);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(110, 60, 200, 20);
        add(txtNome);

        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(30, 90, 100, 20);
        add(lblPreco);

        txtPreco = new JTextField();
        txtPreco.setBounds(110, 90, 200, 20);
        add(txtPreco);

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(30, 120, 100, 20);
        add(lblQuantidade);

        txtQuantidade = new JTextField();
        txtQuantidade.setBounds(110, 120, 200, 20);
        add(txtQuantidade);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(330, 60, 120, 30);
        add(btnCadastrar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(330, 100, 120, 30);
        add(btnAtualizar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(330, 140, 120, 30);
        add(btnExcluir);

        JButton btnListar = new JButton("Listar Produtos");
        btnListar.setBounds(160, 160, 150, 30);
        add(btnListar);

        txtAreaLista = new JTextArea();
        JScrollPane scroll = new JScrollPane(txtAreaLista);
        scroll.setBounds(30, 210, 420, 180);
        add(scroll);

        // ---------------- EVENTOS ---------------- //

        // Cadastrar
        btnCadastrar.addActionListener(e -> cadastrar());

        // Atualizar
        btnAtualizar.addActionListener(e -> atualizar());

        // Excluir
        btnExcluir.addActionListener(e -> excluir());

        // Listar
        btnListar.addActionListener(e -> listar());
    }

    private void cadastrar() {
        String nome = txtNome.getText();
        String preco = txtPreco.getText().replace(",", ".");
        String quant = txtQuantidade.getText();

        if (!ProdutoController.isNumeric(preco) || !ProdutoController.isNumeric(quant)) {
            JOptionPane.showMessageDialog(this, "Preço e quantidade devem ser numéricos!");
            return;
        }

        controller.colocarProduto(nome, Double.parseDouble(preco), Integer.parseInt(quant));
        JOptionPane.showMessageDialog(this, "Produto cadastrado!");
        limparCampos();
    }

    private void atualizar() {
        String idStr = JOptionPane.showInputDialog("Digite o ID do produto:");
        if (!ProdutoController.isNumeric(idStr)) {
            JOptionPane.showMessageDialog(this, "ID inválido!");
            return;
        }

        String antigoNome = controller.listaProdutos.get(Integer.parseInt(idStr)).getNome();
        String antigoPreco = Double.toString(controller.listaProdutos.get(Integer.parseInt(idStr)).getPreco());
        String antigoQuant = Integer.toString(controller.listaProdutos.get(Integer.parseInt(idStr)).getQuantidade());

        int id = Integer.parseInt(idStr);
        String nome = txtNome.getText();
        String preco = txtPreco.getText().replace(",", ".");
        String quant = txtQuantidade.getText();

        if (Objects.equals(nome, "")){
            nome = antigoNome;
        }

        if (!Objects.equals(preco, "") && ProdutoController.isNumeric(preco)) {
            preco = preco;
        }else if (Objects.equals(preco, "")){
            preco = antigoPreco;
        } else if (!ProdutoController.isNumeric(preco)) {
            JOptionPane.showMessageDialog(this, "Preço e quantidade devem ser numéricos!");
            return;
        }

        if (!Objects.equals(quant, "") && ProdutoController.isNumeric(quant)) {
            quant = quant;
        }else if (Objects.equals(quant, "")){
            quant = antigoQuant;
        } else if (!ProdutoController.isNumeric(quant)) {
            JOptionPane.showMessageDialog(this, "Preço e quantidade devem ser numéricos!");
            return;
        }

        controller.atualizarProduto(id, nome, Double.parseDouble(preco), Integer.parseInt(quant));
        JOptionPane.showMessageDialog(this, "Produto atualizado!");
    }

    private void excluir() {
        String idStr = JOptionPane.showInputDialog("Digite o ID do produto a excluir:");
        if (!ProdutoController.isNumeric(idStr)) {
            JOptionPane.showMessageDialog(this, "ID inválido!");
            return;
        }

        controller.excluirProduto(Integer.parseInt(idStr));
        JOptionPane.showMessageDialog(this, "Produto excluído!");
    }

    private void listar() {
        txtAreaLista.setText(controller.retornaProdutos());
    }

    private void limparCampos() {
        txtNome.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
    }

    public static void main(String[] args) {
        new ProdutoView().setVisible(true);
    }
}
