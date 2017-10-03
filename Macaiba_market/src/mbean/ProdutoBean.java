package mbean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import daoImpl.ProdutoDAO;
import model.Produto;

@ManagedBean(name = "produtoBean")
@RequestScoped
public class ProdutoBean {
	Produto produto = new Produto() ;
	ProdutoDAO produtoDao = new ProdutoDAO();
	List<Produto> listap = new ArrayList<Produto>();
	
	public ProdutoBean() {
		this.listap = produtoDao.list();
	}

	public void salvar(){
//		if(listap.contains(produto)){         
//			listap.add(produto);
//			FacesContext fc = FacesContext.getCurrentInstance();
//			FacesMessage messagem = new FacesMessage("Produto atualizado com sucesso!");
//			messagem.setSeverity(FacesMessage.SEVERITY_INFO);
//			fc.addMessage(null, messagem);	
//		}else{
//			FacesContext fc = FacesContext.getCurrentInstance();
//			FacesMessage messagem = new FacesMessage("DEU MERDA com sucesso!"+ produto);
//			messagem.setSeverity(FacesMessage.SEVERITY_INFO);
//			fc.addMessage(null, messagem);	
//		}
		List<Produto> listanova = produtoDao.list();
		for (Produto p : listanova) {			
			if(p.getProdutoId().equals(produto.getProdutoId())){				
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage messagem = new FacesMessage("Produto atualizado com sucesso!");
				messagem.setSeverity(FacesMessage.SEVERITY_INFO);
				fc.addMessage(null, messagem);						
				produtoDao.update(p);		
		    	this.produto = new Produto();			    	
		    	this.listap = produtoDao.list();
		    	break;
			}else{
				System.out.println("teste 2:" + produto.getProdutoId());
				System.out.println("teste 3:" + p.getProdutoId());
				FacesContext fc = FacesContext.getCurrentInstance();
				FacesMessage messagem = new FacesMessage("Produto cadastrado com sucesso!");
				messagem.setSeverity(FacesMessage.SEVERITY_INFO);
				fc.addMessage(null, messagem);						
				produtoDao.save(p);				
		    	this.produto = new Produto();			    	
		    	this.listap = produtoDao.list();
		    	break;
			}
		}				
	}
	
	public void remover(Produto produto){
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage messagem = new FacesMessage("Produto deletado!");
		messagem.setSeverity(FacesMessage.SEVERITY_INFO);
		fc.addMessage(null, messagem);	
		produto.setDataRemocao(Calendar.getInstance().getTime());   	
		produtoDao.update(produto);	
		this.produto = new Produto();
		this.listap = produtoDao.list();
	}
	
	public String atualizar(Produto produto){
		produto.getProdutoId();
		System.out.println("teste: "+produto.getProdutoId());
		this.produto = produto;
		return "cadastrar.xhtml";
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoDAO getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(ProdutoDAO produtoDao) {
		this.produtoDao = produtoDao;
	}

	public List<Produto> getListap() {
		return listap;
	}

	public void setListap(List<Produto> listap) {
		this.listap = listap;
	}
}
