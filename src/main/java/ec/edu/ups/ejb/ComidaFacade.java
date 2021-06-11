package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.modelo.Comida;

@Stateless
public class ComidaFacade extends AbstractFacade<Comida> {

	@PersistenceContext(unitName = "JadanCorte-AngelMesias-ExamenPersistenceUnit")
	private EntityManager em;
	
	public ComidaFacade() {
		super(Comida.class);
	}
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}	
	public Comida search(String nombre) {
		
		/*String sql = "SELECT i FROM Interes i"
				+ " WHERE int_tinicial <=:day and ini_tfin >=:day2 and int_tipo=:tip";
		
		interes = (Interes) em.createQuery(sql, Interes.class)
				.setParameter("day", day).setParameter("day2", day)
				.setParameter("tip", tipo).getSingleResult();			
		*/
		
		Comida comida = new Comida();
		String sql = ("Select c From Comida c Where com_nombre =:nombre");
		comida = (Comida) em.createQuery(sql,Comida.class).setParameter("nombre", nombre).getSingleResult();
		return comida; 
	}
}
