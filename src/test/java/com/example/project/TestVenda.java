package com.example.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestVenda {

    private void verificarCampoObrigatorio(String mensagemEsperada, Venda venda) {
		try {
			venda.imprimeCupom();
		} catch (RuntimeException e) {
			assertEquals(mensagemEsperada, e.getMessage());
		}
    }

    private String NOME_LOJA = "Loja 1";
	private String LOGRADOURO = "Log 1";
	private int NUMERO = 10;
	private String COMPLEMENTO = "C1";
	private String BAIRRO = "Bai 1";
	private String MUNICIPIO = "Mun 1";
	private String ESTADO = "E1";
	private String CEP = "11111-111";
	private String TELEFONE = "(11) 1111-1111";
	private String OBSERVACAO = "Obs 1";
	private String CNPJ = "11.111.111/1111-11";
    private String INSCRICAO_ESTADUAL = "123456789";
    private String DATA_HORA = "25/11/2020 10:30:40V";
    private int CCF = 123456;
    private int COO = 654321;
    
    private Endereco brickEndereco = new Endereco(LOGRADOURO, NUMERO, COMPLEMENTO, BAIRRO, MUNICIPIO, ESTADO, CEP);
    
    private Loja brickLoja = new Loja (NOME_LOJA, brickEndereco, TELEFONE, OBSERVACAO, CNPJ, INSCRICAO_ESTADUAL);

    private Produto exProd1 = new Produto (10, "Banana", "cx", 7.45, "ST");
    private Produto exProd2 = new Produto (20, "Laranja", "cx", 3.32, "ST");
    private Produto exProd3 = new Produto (30, "Chocolate", "un", 0, "");

    @Test
    public void testVendaSemItem(){
        Venda venda = brickLoja.makeVenda(DATA_HORA, CCF, COO);
        verificarCampoObrigatorio("A lista de itens da venda está vazia", venda);
    }

    @Test
    public void testItemValor0(){
        try{
            Venda venda = brickLoja.makeVenda(DATA_HORA, CCF, COO);
            venda.montarCompra(1, exProd3, 5);
        
        } catch (RuntimeException e) {
            assertEquals ("Valor do produto inválido", e.getMessage());
        }        
    }

    @Test
    public void testItemQuantidade0(){
        try{
            Venda venda = brickLoja.makeVenda(DATA_HORA, CCF, COO);
            venda.montarCompra(1, exProd1, 0);

        } catch (RuntimeException e) {
            assertEquals ("Quantidade de itens inválida", e.getMessage());
        }
    }

    @Test
    public void testItemDuplicado(){
        try{
            Venda venda = brickLoja.makeVenda(DATA_HORA, CCF, COO);
            venda.montarCompra(1, exProd2, 3);
            venda.montarCompra(2, exProd2, 5);
        } catch (RuntimeException e) {
            assertEquals ("Dois itens não podem conter o mesmo produto", e.getMessage());
        }
    }

    @Test
    public void validTime() {
        Venda nullTime = new Venda (brickLoja, null, CCF, COO);
        verificarCampoObrigatorio("A data e a hora são obrigatórias", nullTime);

        Venda emptyTime = new Venda (brickLoja, "", CCF, COO);
        verificarCampoObrigatorio("A data e a hora são obrigatórias", emptyTime);
    }

    @Test
    public void validCCF() {
        Venda nullCcf = new Venda(brickLoja, DATA_HORA, 0, COO);
        verificarCampoObrigatorio("O campo ccf é obrigatório", nullCcf);
    }

    @Test
    public void validCOO() {
        Venda nullCoo = new Venda(brickLoja, DATA_HORA, CCF, 0);
        verificarCampoObrigatorio("O campo coo é obrigatório", nullCoo);
    }
}