package br.com.lojaweb.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    private String nome;
    private String descricao;
    private int quant;
    private boolean ativo = true;
    private double valor;
    private String foto1;
    private String foto2;
    private String foto3;

    public boolean isAtivo() {
	return ativo;
    }

    public void setAtivo(boolean ativo) {
	this.ativo = ativo;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public int getQuant() {
	return quant;
    }

    public void setQuant(int quant) {
	this.quant = quant;
    }

    public double getValor() {
	return valor;
    }

    public void setValor(double valor) {
	this.valor = valor;
    }

    public String getFoto1() {
	return foto1;
    }

    public void setFoto1(String foto1) {
	this.foto1 = foto1;
    }

    public String getFoto2() {
	return foto2;
    }

    public void setFoto2(String foto2) {
	this.foto2 = foto2;
    }

    public String getFoto3() {
	return foto3;
    }

    public void setFoto3(String foto3) {
	this.foto3 = foto3;
    }

    public void validar() throws Exception {
	String erros = "";
	if (nome.equals("")) {
	    erros += "Nome em branco.\n";
	}

	if (descricao.equals("")) {
	    erros += "Descrição em branco.\n";
	}

	if (quant <= 0) {
	    erros += "Quantidade invalida.\n";
	}

	if (valor <= 0) {
	    erros += "Valor invalida.\n";
	}

	if (foto1.equals("")) {
	    erros += "Selecione ao menos uma foto.\n";
	}

	if (!erros.equals("")) {
	    throw new Exception(erros);
	}
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
	if (!(object instanceof Produto)) {
	    return false;
	}
	Produto other = (Produto) object;
	return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
	return "Produto[ id=" + id + " ]";
    }

}
