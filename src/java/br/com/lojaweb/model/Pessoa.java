package br.com.lojaweb.model;

import java.io.Serializable;
import java.util.Calendar;
//import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String numero;
    private String complemento;
    private String pws;
    private boolean ativo = true;

    //@Embedded
    //@ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    private Endereco endereco;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataNasc;

    public Pessoa() {
	this.endereco = new Endereco();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Pessoa)) {
	    return false;
	}
	Pessoa other = (Pessoa) object;
	return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
	return "Pessoa[ id=" + id + " ]";
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getNumero() {
	return numero;
    }

    public void setNumero(String numero) {
	this.numero = numero;
    }

    public String getComplemento() {
	return complemento;
    }

    public void setComplemento(String complemento) {
	this.complemento = complemento;
    }

    public String getPws() {
	return pws;
    }

    public void setPws(String pws) {
	this.pws = pws;
    }

    public Calendar getDataNasc() {
	return dataNasc;
    }

    public void setDataNasc(Calendar dataNasc) {
	this.dataNasc = dataNasc;
    }

    public String getCpf() {
	return cpf;
    }

    public void setCpf(String cpf) {
	this.cpf = cpf;
    }

    public Endereco getEndereco() {
	return endereco;
    }

    public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
    }

    public boolean isAtivo() {
	return ativo;
    }

    public void setAtivo(boolean ativo) {
	this.ativo = ativo;
    }

    //Validação
    public void validar() throws Exception {
	String erros = isDados();

	if (!erros.equals("")) {
	    throw new Exception(erros);
	}

    }

    public void validar(String confPws) throws Exception {
	String erros = isDados();

	if (pws.equals("")) {
	    erros += "Senha em branco.\n";
	} else if (pws.length() < 5) {
	    erros += "Senha muito curta. Minimo de 6 caracteres.\n";
	} else if (!pws.equals(confPws)) {
	    erros += "Senha diferentes.\n";
	}

	if (!erros.equals("")) {
	    throw new Exception(erros);
	}
    }

    private String isDados() {
	String erros = "";
	if (nome.equals("")) {
	    erros += "Nome em branco.\n";
	}

	if (cpf.equals("")) {
	    erros += "CPF em branco.\n";
	} else if (cpf.length() != 11) {
	    erros += "CPF com digitos invalido!\n";
	}

	if (email.equals("")) {
	    erros += "E-mail em branco.\n";
	} else if (email.length() < 5) {
	    erros += "E-mail invalido. E-mail muito curto.\n";
	} else if ((!email.contains("@")) || (email.indexOf(".") == -1)) {
	    erros += "E-mail invalido\n";
	}

	if (numero.equals("")) {
	    erros += "Numero em branco.\n";
	}

	return erros += endereco.validar();
    }

}
