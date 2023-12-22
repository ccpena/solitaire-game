/**
 * 
 */
package co.edu.ud.inf1.solitario.logica;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import co.edu.ud.inf1.solitario.presentacion.util.IConstantes;

/**
 * @author RaspuWIN7
 *
 */
public class Logica {

	private boolean iniciado;
	
	public Logica(){
		iniciado = true;
	}

	/**
	 * Metodo para obtener todas las cartas de una baraja de solitario.
	 * Almacena las propiedades de la carta como la imagen, el tipo,
	 * el valor, color en una lista
	 * @return List<Carta> Lista de cartas obtenidas
	 */
	public List<Carta> obtenerBaraja() {
		List<Carta> allCards = new ArrayList<Carta>();
		String[] tipoCarta = {IConstantes.CORAZONES,IConstantes.DIAMANTES,IConstantes.PICAS,IConstantes.TREBOLES};
		Image img = null;
		String nombreCarta = null;
		Carta carta = null;
		
		for(int i = 1 ; i <= 13 ; i++) {
			for (String tipo : tipoCarta) {
				nombreCarta = tipo + i;
				img = new ImageIcon(getClass().getResource("/imagenes/"+nombreCarta+".png")).getImage();	
				if (img != null) {
					carta = new Carta();
					carta.setTipo(tipo);
					carta.setImg(img);
					carta.setValor(i);
					carta.setColorRojo(tipo.equals(IConstantes.CORAZONES) || tipo.equals(IConstantes.DIAMANTES));
					allCards.add(carta);
				}
			}
		}
		
		return allCards;
	}
	
	/**
	 * Método que verifica si el movimiento
	 * de arrastrar un listado de cartas a una región
	 * es válido o no, tomando como referencia el primer
	 * objeto de la lista de cartas.
	 * @param regAValidar int Número de región dónde se soltó la carta y se desea validar.
	 * @param pRegSup boolean true región superior, false inferior.
	 * @param cartaMoviendo Objeto Carta con la información de la carta qué se está moviendo hacia la región
	 * @param  lstCartaRegion Lista de cartas de la región hacia dónde se hizo el movimiento.
	 * @return boolean true si es válido el moviemiento, false en otro caso.
	 */
	public boolean isMovimientoValido(int regAValidar , boolean pRegSup , Carta cartaMoviendo , List<Carta> lstCartaRegion) {
		boolean resultado = false;
		
		Carta ultimaCarta = null;
		
		if (lstCartaRegion == null || lstCartaRegion.isEmpty()) {
			if (pRegSup) {
				resultado = cartaMoviendo.getValor() == IConstantes.AS;
			}
			else {
				resultado = cartaMoviendo.getValor() == IConstantes.TOT_CARTA_TIPO;
			}
			
			return resultado;
		}
		
		ultimaCarta = lstCartaRegion.get(lstCartaRegion.size()-1);
		
		if (pRegSup) {
			if (ultimaCarta.getValor() + 1 == cartaMoviendo.getValor()) {
				resultado = ultimaCarta.getTipo().equals(cartaMoviendo.getTipo());
			}
		}
		else {
			if (ultimaCarta.getValor() - 1 == cartaMoviendo.getValor()) {
				resultado = (ultimaCarta.isColorRojo() != cartaMoviendo.isColorRojo());
			}
		}
		return resultado;
	}
	
	/**
	 * Método para validar la asignación de una carta a un slot.
	 * @param mapRegSup Mapa con la lista de cartas por slot.
	 * @param validandoCarta Carta que se va a validar
	 * @return Slot Si el objeto es diferente de nulo, significa qué la validación fue
	 * positiva, y retorna el slot correspondiente con la lista de cartas actuales.
	 */
	public Slot validacionAsignacionASlot(Map<Integer, List<Carta>> mapRegSup , Carta validandoCarta) {
		Carta cartaP = null;
		List<Carta> lstCartasXSlot = null;
		int numSlot = -1;
		Slot slotValidado = null;;
		
		
		for (int i = 3; i <= 6; i++) {
			lstCartasXSlot = mapRegSup.get(i);
			if (lstCartasXSlot == null || lstCartasXSlot.isEmpty()) {
				if (validandoCarta.getValor() == IConstantes.AS) {
					if (slotValidado == null) {
						lstCartasXSlot = new ArrayList<Carta>();
						numSlot = i;
					}
				}
				break;
			} else {
				cartaP = lstCartasXSlot.get(lstCartasXSlot.size() - 1);
				if (cartaP.getTipo().equalsIgnoreCase(validandoCarta.getTipo())) {
					if (cartaP.getValor() + 1 == validandoCarta.getValor()) {
						numSlot = i;
					}
					break;
				}
			}
		}
		
		if (numSlot != -1) {
			slotValidado = new Slot();
			slotValidado.setLstCartasXSlot(lstCartasXSlot);
			slotValidado.setNumSlot(numSlot);
		}
		
		return slotValidado;
	}
	
	// GETTERS && SETTERS
	
	/**
	 * Obtiene
	 * @return the iniciado
	 */
	public boolean isIniciado() {
		return iniciado;
	}


	/**
	 * Asigna
	 * @param iniciado the iniciado to set
	 */
	public void setIniciado(boolean iniciado) {
		this.iniciado = iniciado;
	}
	
}
