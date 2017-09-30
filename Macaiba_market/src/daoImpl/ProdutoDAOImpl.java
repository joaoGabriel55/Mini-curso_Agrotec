package daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Produto;
import util.HibernateUtil;

public class ProdutoDAOImpl {

	private Produto produto;

	public ProdutoDAOImpl(Produto produto) {
		this.produto = produto;
	}

	public void salvar(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;

		try {
			t = sessao.beginTransaction();
			sessao.save(produto);
			t.commit();
		} catch (RuntimeException erro) {
			if (t != null) {
				t.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Produto> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Produto.class);
			List<Produto> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	public void deletar(Produto produto) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction t = null;
		try {
			t = sessao.beginTransaction();
			sessao.delete(produto);
			t.commit();
		} catch (RuntimeException erro) {
			if (t != null) {
				t.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	/*
	 * public void editar(T entidade) { Session sessao =
	 * HibernateUtil.getSessionFactory().openSession(); Transaction t = null; try {
	 * t = sessao.beginTransaction(); sessao.update(entidade); t.commit();
	 * 
	 * } catch (RuntimeException erro) { if (t != null) { t.rollback(); }
	 * 
	 * throw erro; } finally { sessao.close(); } }
	 */

	/*
	 * public void merge(T entidade) { Session sessao =
	 * HibernateUtil.getSessionFactory().openSession(); Transaction t = null; try {
	 * t = sessao.beginTransaction(); sessao.merge(entidade); t.commit();
	 * 
	 * } catch (RuntimeException erro) { if (t != null) { t.rollback(); } throw
	 * erro; } finally { sessao.close(); } }
	 */

}
