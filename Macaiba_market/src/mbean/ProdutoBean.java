package mbean;


import java.util.List;

import model.Produto;


public class ProdutoBean {
	Produto produto = new Produto();
	List<Produto> listap;



	public String salvar() {
		return null;

		// Flash scope
		// faz com que a mensagem de sucesso seja perpetuada apos o
		// redirecionamento

		

		
	}

	public void remover(Produto produto) {
		
	}

	public String atualizar(Produto produto) {
		
		return null;
	}

	public List<Produto> listar() {
		
		return listap;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListap() {
		return listar();
	}

	public void setListap(List<Produto> listap) {
		this.listap = listap;
	}
}