package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;

import ec.edu.ups.ejb.TarjetaCreditoFacade;
import ec.edu.ups.modelo.TarjetaCredito;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@RequestScoped
public class TarjetaCreditoBeans implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private TarjetaCreditoFacade ejbTarjetaCredito;
	
	private TarjetaCredito tc;
	
	public TarjetaCreditoBeans() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init() {
		System.out.println("Iniciando");
		tc = new TarjetaCredito();
	}
	public TarjetaCredito generar() {
		Random r = new Random();
		int ntitular = r.nextInt(6)+1;
		int valorD = r.nextInt(6)+1;
		int valorD1 = r.nextInt(6)+1;
		int valorD2 = r.nextInt(6)+1;
		
		int num = (int)Math.random()*10+1;
		String titular = "Titular "+ntitular;
		Date caducidad = new Date();
		String cdat = String.valueOf(valorD) +String.valueOf(valorD1)
		+String.valueOf(valorD2);
		int cvv = Integer.parseInt(cdat);
		Calendar calendar = Calendar.getInstance();
		
		calendar.add(Calendar.DAY_OF_YEAR, 100);
		System.out.println("Tarjeta de credito generada: ");
		System.out.println("Titular: "+titular);
		System.out.println("Vencimiento: "+calendar.getTime());
		System.out.println("cvv: "+cvv);
		System.out.println(calendar.getTime());
		
		tc.setTitular(titular);
		tc.setCaducidad(caducidad);
		tc.setCvv(cvv);
		return tc;
	}
	
	public String generarTarjeta() {
		
		try {
			System.out.println("Generado: "+tc);
			generar();
			ejbTarjetaCredito.create(tc);
			System.out.println("Tarjeta guardada");
			return "Targeta generada";
		} catch (Exception e) {
			System.out.println("Datos repetidos: "+e.getMessage());
			return "error";
		}
	}

	public TarjetaCreditoFacade getEjbTarjetaCredito() {
		return ejbTarjetaCredito;
	}

	public void setEjbTarjetaCredito(TarjetaCreditoFacade ejbTarjetaCredito) {
		this.ejbTarjetaCredito = ejbTarjetaCredito;
	}

	public TarjetaCredito getTc() {
		return tc;
	}

	public void setTc(TarjetaCredito tc) {
		this.tc = tc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
