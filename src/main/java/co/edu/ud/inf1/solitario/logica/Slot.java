/**
 * 
 */
package co.edu.ud.inf1.solitario.logica;

import java.util.List;

/**
 * @author RaspuWIN7
 *
 */
public class Slot {
	
	private int numSlot;
	
	private List<Carta> lstCartasXSlot;

	/**
	 * Obtiene
	 * @return the numSlot
	 */
	public int getNumSlot() {
		return numSlot;
	}

	/**
	 * Asigna
	 * @param numSlot the numSlot to set
	 */
	public void setNumSlot(int numSlot) {
		this.numSlot = numSlot;
	}

	/**
	 * Obtiene
	 * @return the lstCartasXSlot
	 */
	public List<Carta> getLstCartasXSlot() {
		return lstCartasXSlot;
	}

	/**
	 * Asigna
	 * @param lstCartasXSlot the lstCartasXSlot to set
	 */
	public void setLstCartasXSlot(List<Carta> lstCartasXSlot) {
		this.lstCartasXSlot = lstCartasXSlot;
	}

}
