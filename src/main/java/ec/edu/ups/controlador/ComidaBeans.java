package ec.edu.ups.controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.ComidaFacade;
import ec.edu.ups.modelo.Comida;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ComidaBeans implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ComidaFacade ejbComida;
	
	private String nombre;
	private double precio;
	private Comida comida;
	
	public ComidaBeans() {
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
	public void init() {
		comida = new Comida();
	}
	
	public void saveComida() {
		try {
			ejbComida.create(this.comida);
			System.out.println("Comida guardada");
		} catch (Exception e) {
			System.out.println("No se puede guardar comida: "+e.getMessage());
		}
	}
	public void searchComida() {
		try {
			this.comida = ejbComida.search(this.nombre);
			this.nombre =this.comida.getNombre();
			this.precio = this.comida.getPrecio();
		}catch (Exception e) {
			System.out.println("Error busqueda beans: "+e.getMessage());
		}
	}
	public ComidaFacade getEjbComida() {
		return ejbComida;
	}
	public void setEjbComida(ComidaFacade ejbComida) {
		this.ejbComida = ejbComida;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Comida getComida() {
		return comida;
	}
	public void setComida(Comida comida) {
		this.comida = comida;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
