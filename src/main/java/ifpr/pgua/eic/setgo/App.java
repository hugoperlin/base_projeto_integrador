package ifpr.pgua.eic.setgo;


import ifpr.pgua.eic.setgo.controllers.JanelaPedido;
import ifpr.pgua.eic.setgo.controllers.JanelaPrincipal;
import ifpr.pgua.eic.setgo.controllers.JanelaProduto;
import ifpr.pgua.eic.setgo.controllers.ViewModels.JanelaPedidosViewModel;
import ifpr.pgua.eic.setgo.controllers.ViewModels.JanelaProdutosViewModel;
import ifpr.pgua.eic.setgo.models.FabricaConexoes;
import ifpr.pgua.eic.setgo.models.daos.ItensPedidoDAO;
import ifpr.pgua.eic.setgo.models.daos.JDBCItensPedidoDAO;
import ifpr.pgua.eic.setgo.models.daos.JDBCPedidoDAO;
import ifpr.pgua.eic.setgo.models.daos.JDBCProdutoDAO;
import ifpr.pgua.eic.setgo.models.daos.PedidoDAO;
import ifpr.pgua.eic.setgo.models.daos.ProdutoDAO;
import ifpr.pgua.eic.setgo.models.entities.Estoque;
import ifpr.pgua.eic.setgo.models.repositories.GerenciadorEstoque;
import ifpr.pgua.eic.setgo.models.repositories.ItensPedidoRepository;
import ifpr.pgua.eic.setgo.models.repositories.PedidosRepository;
import ifpr.pgua.eic.setgo.models.repositories.ProdutoRepository;
import ifpr.pgua.eic.setgo.utils.BaseAppNavigator;
import ifpr.pgua.eic.setgo.utils.ScreenRegistryFXML;

public class App extends BaseAppNavigator {
    private GerenciadorEstoque gerenciador;
    private Estoque estoque;
    
    private ProdutoDAO produtoDAO;
    private ItensPedidoDAO itensPedidoDAO;
    private PedidoDAO pedidoDAO;
    private ProdutoRepository produtoRepository;
    private ItensPedidoRepository itensRepository;
    private PedidosRepository pedidosRepository;

    @Override
    public void init() throws Exception {
        super.init();
        gerenciador = new GerenciadorEstoque(FabricaConexoes.getInstance());
        
        produtoDAO = new JDBCProdutoDAO(FabricaConexoes.getInstance());
        produtoRepository = new ProdutoRepository(produtoDAO);

        itensPedidoDAO = new JDBCItensPedidoDAO(FabricaConexoes.getInstance());
        itensRepository = new ItensPedidoRepository(itensPedidoDAO);

        pedidoDAO = new JDBCPedidoDAO(FabricaConexoes.getInstance());
        pedidosRepository = new PedidosRepository(pedidoDAO);

        estoque = new Estoque();
    }

    @Override
    public String getHome() {
        return "PRINCIPAL";
    }

    @Override
    public String getAppTitle() {
        return "Set & Go";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL", new ScreenRegistryFXML(App.class, "fxml/principal.fxml", o->new JanelaPrincipal()));
        registraTela("PRODUTOS", new ScreenRegistryFXML(App.class, "fxml/produtos.fxml", o->new JanelaProduto(new JanelaProdutosViewModel(produtoRepository))));
        registraTela("PEDIDOS", new ScreenRegistryFXML(App.class, "fxml/pedidos.fxml", o->new JanelaPedido(new JanelaPedidosViewModel(produtoRepository, itensRepository, pedidosRepository), estoque)));
    }

    @Override
    public void atualizaEstilo() {
        adicionarArquivoEstilo(getClass().getResource("css/estilo.css").toExternalForm());
    }

}