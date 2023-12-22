/**
 * 
 */
package co.edu.ud.inf1.solitario.logica;

import java.awt.Image;
import java.io.Serializable;

import co.edu.ud.inf1.solitario.presentacion.util.IConstantes;

/**
 * @author RaspuWIN7
 *
 */
public class Carta implements Serializable , Comparable<Carta> {

	private int posX ;
	
	private int posY ;
	
	private Image img;
	
	private int valor;
	
	private String tipo;
	
	private boolean visible;
	
	private boolean colorRojo;
	
	
	public Carta clone() {
		Carta cartaMov = new Carta();
		cartaMov.setVisible(isVisible());
		cartaMov.setImg(getImg());
		cartaMov.setPosX(getPosX());
		cartaMov.setTipo(getTipo());
		cartaMov.setValor(getValor());
		cartaMov.setPosY(getPosY());
		cartaMov.setColorRojo(getTipo().equals(IConstantes.CORAZONES) || getTipo().equals(IConstantes.DIAMANTES));
		
		return cartaMov;
	}

	/**
	 * Obtiene
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Asigna
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * Obtiene
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * Asigna
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}

	/**
	 * Obtiene
	 * @return the img
	 */
	public Image getImg() {
		return img;
	}

	/**
	 * Asigna
	 * @param img the img to set
	 */
	public void setImg(Image img) {
		this.img = img;
	}

	/**
	 * Obtiene
	 * @return the valor
	 */
	public int getValor() {
		return valor;
	}

	/**
	 * Asigna
	 * @param valor the valor to set
	 */
	public void setValor(int valor) {
		this.valor = valor;
	}

	/**
	 * Obtiene
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Asigna
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Obtiene
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Asigna
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Obtiene
	 * @return the colorRojo
	 */
	public boolean isColorRojo() {
		return colorRojo;
	}

	/**
	 * Asigna
	 * @param colorRojo the colorRojo to set
	 */
	public void setColorRojo(boolean colorRojo) {
		this.colorRojo = colorRojo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Carta o) {
		if (tipo.equals(o.getTipo()) && valor == o.getValor()) {
			return 0;
		}
		return -1;
	}

}
