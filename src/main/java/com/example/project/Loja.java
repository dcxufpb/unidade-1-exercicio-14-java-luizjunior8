package com.example.project;
import java.util.ArrayList;

public class Loja {

    private String nomeLoja;
    private Endereco endereco;
    private String telefone;
    private String observacao;
    private String cnpj;
    private String inscricaoEstadual;
    private ArrayList <Venda> vendas = new ArrayList <Venda>();

    public void setVendas (Venda venda) {
        this.vendas.add(venda);
    }

    public ArrayList<Venda> getVendas(){
        return this.vendas;
    }

    public Venda makeVenda(String datahora, int ccf, int coo){
        Venda vendas = new Venda(this, datahora,ccf,coo);
        this.setVendas(vendas);
        return vendas;
    } 

    public Loja(String nomeLoja, Endereco endereco, String telefone,
                String observacao, String cnpj, String inscricaoEstadual) {
        this.nomeLoja = nomeLoja;
        this.endereco = endereco;
        this.telefone = telefone;
        this.observacao = observacao;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getNomeLoja() {
        return this.nomeLoja;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getObservacao() {
        return this.observacao;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getInscricaoEstadual() {
        return this.inscricaoEstadual;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public static String Spacer = System.lineSeparator();


    public static boolean checker (String tested){
		String k = "";
		k += tested;
		if (k.equals("null")|| k.isEmpty()){
			return false;
		}	
		return true;	
    }

    public void validar_dados_obrigatorios() {

        if (checker(this.getNomeLoja()) == false){
			throw new RuntimeException ("O campo nome da loja é obrigatório");
        }

        if (checker(this.getCnpj()) == false){
			throw new RuntimeException ("O campo cnpj da loja é obrigatório");
        }

        if (checker(this.getInscricaoEstadual()) == false){
			throw new RuntimeException ("O campo inscrição estadual da loja é obrigatório");
		}
    }

    public String dadosLoja() {
        
        validar_dados_obrigatorios();

		String _telefone = checker(this.getTelefone()) == false? "" : "Tel " + this.getTelefone();

	 	if (checker(endereco.getCep()) && !_telefone.isEmpty()){
			_telefone = " Tel " + this.getTelefone();
		}

        String _observacao = checker(this.getObservacao()) == false ? "" : this.getObservacao();

        String _cnpj = "CNPJ: " + this.getCnpj();

        String _inscricao_estadual = "IE: " + this.getInscricaoEstadual(); 

        return (this.getNomeLoja() + Spacer +
         endereco.dados_endereco()
		 + _telefone + Spacer +
		 _observacao + Spacer +	
		 _cnpj + Spacer +
		 _inscricao_estadual + Spacer);
	}

}