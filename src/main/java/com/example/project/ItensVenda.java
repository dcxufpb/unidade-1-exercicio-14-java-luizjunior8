package com.example.project;

public class ItensVenda {

    private Venda venda;
    private int item;
    private Produto produto;
    private int quantidade;
    private String Spacer = System.lineSeparator();

    public ItensVenda (Venda venda, int item, Produto produto, int quantidade) {

        this.venda = venda;
        this.item = item;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Venda getVenda() {
        return this.venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public int getItem() {
        return this.item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
    } 
    
    public double valor_final_item() {
        double preco = this.produto.getValorUnitario() * this.quantidade;
        return preco;
    }

    public String dadosItem(){

        return this.item + " " + this.produto.getCodigo() + " " + this.produto.getDescricao() + " " + 
        this.quantidade + " " + this.produto.getUnidade() + " " + String.format("%.2f", this.produto.getValorUnitario()) +
        " " + this.produto.getSubstituicaoTributaria() + " " + valor_final_item() + Spacer;
    }    
}