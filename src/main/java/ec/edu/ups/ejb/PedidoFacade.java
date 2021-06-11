package ec.edu.ups.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.modelo.Pedido;

@Stateless
public class PedidoFacade extends AbstractFacade<Pedido> {

	@PersistenceContext(unitName = "JadanCorte-AngelMesias-ExamenPersistenceUnit")
	private EntityManager em;
	
	public PedidoFacade() {
		super(Pedido.class);
	}
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	public List<Pedido> lista(){
		List<Pedido> lista =  new ArrayList<Pedido>();
		lista = em.createQuery("Select p From Pedido p", Pedido.class).getResultList();
		return lista;
	}
}
