package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.PedidoFacade;
import ec.edu.ups.ejb.TarjetaCreditoFacade;
import ec.edu.ups.modelo.Comida;
import ec.edu.ups.modelo.Pedido;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class PedidoBeans implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private PedidoFacade ejbPedido;
	
	private Pedido pedido;
	
	private Date fecha;
	private String cliente;
	private double subtotal;
	private double iva;
	private double total;
	private String observacion;
	
	private Comida comida;
	private List<Comida> comidas;
	private List<Pedido> pedidos;
	
	public PedidoBeans() {
		
	}
	@PostConstruct
	public void init() {
		pedido = new Pedido();
		fecha = new Date();
		comidas = new ArrayList<Comida>();
		comida = new Comida();
		pedidos = new ArrayList<Pedido>();
	}
	
	public void addComida(int id) {
		comida.setId(id);
		comidas.add(comida);
	}
	
	public double calsub(double precio) {
		this.subtotal= this.subtotal+precio;
		return subtotal;
	}
	public double caliva() {
		this.iva =this.subtotal*0.12;
		return this.iva;
	}
	public double caltotal() {
		this.total = this.subtotal+this.iva;
		return this.total;
	}
	
	public void savePedido() {
		try {
			calsub(12);
			caliva();
			caltotal();
			pedido.setCliente(this.cliente);
			pedido.setFecha(this.fecha);
			pedido.setComida(this.comidas);
			ejbPedido.create(pedido);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void listPedidos() {
		try {
			pedidos = ejbPedido.findAll();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void searchTcNombre() {
		listPedidos();
	}
	
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
