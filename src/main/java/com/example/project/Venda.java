package com.example.project;

import java.util.ArrayList;
 
public class Venda {
    
    private Loja loja;
    private String dataHora;
    private int ccf;
    private int coo;
    private ArrayList <ItensVenda> itens = new ArrayList <ItensVenda>();

    public Venda(Loja loja, String dataHora, int ccf, int coo) {
        this.loja = loja;
        this.ccf = ccf;
        this.coo = coo;
        this.dataHora = dataHora;
    }

	public Loja getLoja() {
		return this.loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public int getCcf() {
		return this.ccf;
	}

	public void setCcf(int ccf) {
		this.ccf = ccf;
	}

	public int getCoo() {
		return this.coo;
	}

	public void setCoo(int coo) {
		this.coo = coo;
	}

	public String getdataHora() {
		return this.dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
    }

    public static String Spacer = System.lineSeparator();

    
    public void validar_dados_obrigatorios() {

        if (this.loja == null){
            throw new RuntimeException ("Informe uma loja válida");
        }

        if (this.ccf == 0){
            throw new RuntimeException ("O campo ccf é obrigatório");
        }

        if (this.coo == 0){
            throw new RuntimeException ("O campo coo é obrigatório");
        }

        if (this.dataHora == null || this.dataHora == "") {
            throw new RuntimeException ("A data e a hora são obrigatórias");
        }

        if (this.itens.isEmpty()){
            throw new RuntimeException("A lista de itens da venda está vazia");
        }        
    }

    public void checarItens (int item, Produto produto, int quantidade) {

        if (quantidade <= 0) {
            throw new RuntimeException("Quantidade de itens inválida");
        }  
        
        if (produto.getValorUnitario() <= 0) {
            throw new RuntimeException("Valor do produto inválido");
        }

        for (ItensVenda i : this.itens){
            if (i.getItem() != item && i.getProduto().getCodigo() == produto.getCodigo()){
                throw new RuntimeException("Dois itens não podem conter o mesmo produto");
            }
        }
    }

    public void montarCompra (int item, Produto produto, int quantidade){
        
        checarItens(item, produto, quantidade);

        ItensVenda itensCompra = new ItensVenda(this, item, produto, quantidade);
        this.itens.add(itensCompra);
    }

    public String dadosCompra(){

        String cabecalho = "ITEM  CODIGO  DESCRICAO  QTD  UN  VL.UNIT(R$) ST VL.ITEM(R$)" + Spacer;
        for (ItensVenda k : this.itens) {
            cabecalho += k.dadosItem() + Spacer;
        }
        return cabecalho;
    }

    public double totalCompra(){
        double prejuizo = 0;
        for (ItensVenda k : this.itens){
            prejuizo += k.valor_final_item();
        }
        return prejuizo;
    }

    public String dadosVenda() {

        this.validar_dados_obrigatorios();

        String _dataHora = "25/11/2020 10:30:40V";

        String _ccf = " CCF:" + this.ccf;

        String _coo = " COO:" + this.coo;

        return ( _dataHora + _ccf + _coo); 
    }

    public String imprimeCupom(){
        
        String loja = this.loja.dadosLoja();
        String venda = this.dadosVenda();
        String compra = this.dadosCompra();
        double conta = this.totalCompra();

        return (loja + Spacer + "--------------------" + Spacer + venda + "   CUPOM FISCAL   " +
        Spacer + compra + "--------------------" + Spacer + String.format("%.2f", conta));

    }
}
