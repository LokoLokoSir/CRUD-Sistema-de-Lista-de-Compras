import java.util.ArrayList;
import java.util.Scanner;

public class ProdutoController {

    private static int quantIds = 0;
    ArrayList<Produto> listaProdutos = new ArrayList<>();

    public void colocarProduto(String _nome, double _preco, int _quantidade){
        Produto novoProduto = new Produto(quantIds, _nome, _preco, _quantidade);
        listaProdutos.add(novoProduto);
        quantIds += 1;
    }

    public String retornaProdutos(){
        StringBuilder sb = new StringBuilder();
        sb.append("ID | Nome | Preço | Quantidade\n");
        sb.append("---------------------------------\n");
        for (Produto produto : listaProdutos) {
            sb.append(produto.getId())
                    .append(" | ")
                    .append(produto.getNome())
                    .append(" | ")
                    .append(String.format("%.2f", produto.getPreco()))
                    .append(" | ")
                    .append(produto.getQuantidade())
                    .append("\n");
        }
        return sb.toString();
    }

    public boolean atualizarProduto(int id, String nome, double preco, int quantidade){
        if (id < 0 || id >= quantIds) {
            return false;
        }

        Produto produto = listaProdutos.get(id);
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setQuantidade(quantidade);

        return true;
    }

    public boolean excluirProduto(int id){
        if (id < 0 || id >= quantIds) {
            return false;
        }

        listaProdutos.remove(id);
        quantIds -= 1;

        for (int i = 0; i < quantIds; i++) {
            listaProdutos.get(i).setId(i);
        }
        return true;
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
