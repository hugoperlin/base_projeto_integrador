package ifpr.pgua.eic.setgo.models.repositories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.mysql.cj.xdevapi.PreparableStatement;

import ifpr.pgua.eic.setgo.models.FabricaConexoes;
import ifpr.pgua.eic.setgo.models.entities.Pedido;
import ifpr.pgua.eic.setgo.models.entities.Produto;
import ifpr.pgua.eic.setgo.models.results.FailResult;
import ifpr.pgua.eic.setgo.models.results.Result;
import ifpr.pgua.eic.setgo.models.results.SuccessResult;

public class GerenciadorEstoque{
    private List<Produto> produtos;
    private List<Pedido> pedidos;

    private FabricaConexoes fabricaConexoes;

    public GerenciadorEstoque(FabricaConexoes fabricaConexoes){
        produtos = new ArrayList<>();
        pedidos = new ArrayList<>();

        this.fabricaConexoes = fabricaConexoes;
    }

    // public Venda getVendaAtual(){
    //     return venda;
    // }


    // public Result iniciarVenda(Cliente cliente){
    //     if(venda != null){
    //         return Result.fail("Não foi possível iniciar uma nova venda, já existe uma inicida!");
    //     }

    //     venda = new Venda(cliente,LocalDateTime.now());

    //     return Result.success("Venda iniciada!");
    // }

    // public Result adicionarProdutoVenda(Produto produto, double quantidade){

    //     if(venda == null){
    //         return Result.fail("Venda não iniciada!");
    //     }

    //     venda.adicionarProduto(produto, quantidade);

    //     return Result.success("Produto adicionado!");
    // }

    // public Result removerProdutoVenda(Produto produto, double quantidade){
    //     if(venda == null){
    //         return Result.fail("Venda não iniciada!");
    //     }

    //     if(venda.removerProduto(produto, quantidade)){
    //         return Result.success("Quantidade removida!");
    //     }

    //     return Result.fail("Produto não encontrado!");
    // }

    // public Result inserirDescontoVenda(double desconto){
    //     if(venda == null){
    //         return Result.fail("Venda não iniciada!");
    //     }

    //     venda.setDesconto(desconto);
    //     return Result.success("Desconto registrado!");
    // }

    // public Result finalizarVenda(){
    //     if(venda == null){
    //         return Result.fail("Venda não iniciada!");
    //     }

    //     this.vendas.add(venda);
    //     venda = null;

    //     return Result.success("Venda finalizada com sucesso!");

    // }

    // public List<Venda> getVendas(){
    //     return Collections.unmodifiableList(vendas);
    // }

}
