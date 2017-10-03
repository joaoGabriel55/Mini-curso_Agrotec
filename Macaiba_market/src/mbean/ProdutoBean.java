package mbean;

import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;

import daoImpl.ProdutoDAO;
import model.Produto;

@ManagedBean
@SessionScoped
public class ProdutoBean {
	Produto produto = new Produto();
	List<Produto> listap;

	/*
	 * public ProdutoBean() { this.produto = new Produto(); }
	 */

	public String salvar() {

		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();

			produtoDAO.save(this.produto);

			produto = new Produto();

			// Flash scope
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cadastro realizado com sucesso!"));

			// Essa parte faz com que a mensagem de sucesso seja perpetuada apos o
			// redirecionamento
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao cadastrar");
			erro.printStackTrace();

			return "/cadastrar.xhtml?faces-redirect=true";

		}

		return "/listar.xhtml?faces-redirect=true";

		/*
		 * if (listap.contains(produto)) { listap.add(produto); FacesContext fc =
		 * FacesContext.getCurrentInstance(); FacesMessage messagem = new
		 * FacesMessage("Produto atualizado com sucesso!");
		 * messagem.setSeverity(FacesMessage.SEVERITY_INFO); fc.addMessage(null,
		 * messagem); } else { FacesContext fc = FacesContext.getCurrentInstance();
		 * FacesMessage messagem = new FacesMessage("DEU MERDA com sucesso!" + produto);
		 * messagem.setSeverity(FacesMessage.SEVERITY_INFO); fc.addMessage(null,
		 * messagem);
		 * 
		 * List<Produto> listanova = produtoDao.list(); for (Produto p : listanova) { if
		 * (p.getProdutoId().equals(produto.getProdutoId())) { FacesContext fc2 =
		 * FacesContext.getCurrentInstance(); FacesMessage messagem2 = new
		 * FacesMessage("Produto atualizado com sucesso!");
		 * messagem2.setSeverity(FacesMessage.SEVERITY_INFO); fc2.addMessage(null,
		 * messagem2); produtoDao.update(p); this.produto = new Produto(); this.listap =
		 * produtoDao.list(); break;
		 * 
		 * } else { System.out.println("teste 2:" + produto.getProdutoId());
		 * System.out.println("teste 3:" + p.getProdutoId()); FacesContext fc3 =
		 * FacesContext.getCurrentInstance(); FacesMessage messagem3 = new
		 * FacesMessage("Produto cadastrado com sucesso!");
		 * messagem.setSeverity(FacesMessage.SEVERITY_INFO); fc3.addMessage(null,
		 * messagem3); produtoDao.save(p); this.produto = new Produto(); this.listap =
		 * produtoDao.list(); break; }
		 * 
		 * } }
		 */
	}

	public void remover(Produto produto) {
		ProdutoDAO produtoDao = new ProdutoDAO();
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage messagem = new FacesMessage("Produto deletado!");
		messagem.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null, messagem);
		produto.setDataRemocao(Calendar.getInstance().getTime());
		produtoDao.update(produto);
		this.produto = new Produto();
		this.listap = produtoDao.list();
	}

	public String atualizar(Produto produto) {
		produto.getProdutoId();
		System.out.println("teste: " + produto.getProdutoId());
		this.produto = produto;
		return "cadastrar.xhtml";
	}

	public List<Produto> listar() {
		try {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			listap = produtoDAO.list();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao listar");
			erro.printStackTrace();
		}
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
