package com.example.project;

public class Endereco {

    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String municipio;
    private String estado;
    private String cep;

    public Endereco(String logradouro, int numero, String complemento, String bairro, String municipio, String estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.municipio = municipio;
        this.estado = estado;
        this.cep = cep;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public String getBairro() {
        return this.bairro;
    }

    public String getMunicipio() {
        return this.municipio;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getCep() {
        return this.cep;
    }

    public static String Spacer = System.lineSeparator();

    public void validar_dados_obrigatorios() {

        if (Loja.checker(this.getLogradouro()) == false){
			throw new RuntimeException ("O campo logradouro do endereço é obrigatório");
        }

        if (Loja.checker(this.getMunicipio()) == false){
			throw new RuntimeException ("O campo município do endereço é obrigatório");			
        }

        if (Loja.checker(this.getEstado()) == false){
			throw new RuntimeException ("O campo estado do endereço é obrigatório");
        }

    }

    public String dados_endereco() {

        validar_dados_obrigatorios();

        String _logradouro = this.getLogradouro()+ ", ";

		String _numero = "0";

		if (this.getNumero() == 0){
			_numero = "s/n";
		}else{
			_numero = String.format ("%d", this.getNumero());
		}

		String _complemento = (Loja.checker(this.getComplemento()) == false) ? "" : " " + this.getComplemento();

        String _bairro = Loja.checker(this.getBairro()) == false ? "" : this.getBairro() + " - ";

        String _municipio = this.getMunicipio() + " - ";

        String _cep = "";

		if (Loja.checker(this.getCep()) == true){
            _cep = "CEP:" + this.getCep();
        }

        return (_logradouro + _numero + _complemento + Spacer +
         _bairro + _municipio + this.getEstado() + Spacer +
         _cep);
    }

}