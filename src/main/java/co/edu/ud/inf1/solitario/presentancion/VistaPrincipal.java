/**
 * 
 */
package co.edu.ud.inf1.solitario.presentancion;

import javax.swing.JFrame;

import co.edu.ud.inf1.solitario.presentacion.util.IConstantes;

/**
 * @author RaspuWIN7
 *
 */
public class VistaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8699220161099370820L;
	private final Modelo modelo;
	private Tablero lienzo;
	private ControladorTablero controladorTablero;

	public VistaPrincipal(Modelo pModelo) {
		modelo = pModelo;
		initComponents();
	}

	/**
	 * Método que agrega los componentes a la ventana
	 * del juego solitario
	 */
	private void initComponents() {
		this.add(getLienzo());
	}
	
	/**
	 * Método que se ejecuta cuando se desea mover
	 * una carta.
	 * @param x int Posicion horizontal en pxl del tablero dónde se produjo el evento.
	 * @param y int Posicion vertical en pxl del tablero dónde se produjo el evento.
	 * @param region int Número de region  dónde se produjo el evento.
	 * @param regSup boolean true si es región superior, false inferior.
	 */
	public void moverCarta(int x, int y, int region , boolean regSup) {
		getModelo().moverCarta(x, y, region, regSup);
	}
	
	/**
	 * Método de asignación rápida de carta producto de un
	 * doble click. Realiza la validación correspondiente
	 * al juego para saber si existe una posible asignación
	 * de la carta que fue clickeada.
	 * @param region int Número de region donde fue producido el evento del click.
	 * @param x int Pixel horizonal donde fue producido el evento del click.
	 * @param y int Pixel vertical donde fue producido el evento del click.
	 * @param regSup boolean true si fue en la región superior, false si fue
	 * región inferior.
	 */
	public void asignacionAutomatica(int region, int x , int y , boolean regSup) {
		getModelo().asignacionAutomatica(region, x, y, regSup);
	}
	
	/**
	 * Método que cambia la carta del stock
	 * de cartas disponibles para jugar
	 * en la parte superior izquierda.
	 */
	public void cambiarCartaStock() {
		getModelo().cambiarCartaStock();
	}
	
	/**
	 * Método dónde permite dibujar la carta soltada
	 * en una región determinada
	 * @param region int número de region donde se desea soltar la carta
	 * @param pRegSUp boolean True region superior, false inferior.
	 */
	public void soltarCarta(int region , boolean pRegSUp) {
		getModelo().soltarCarta(region, pRegSUp);
	}

	/** GETTER & SETTER */

	public Modelo getModelo() {
		return modelo;
	}

	public Tablero getLienzo() {
		if (lienzo == null) {
			lienzo = new Tablero();			
			lienzo.addMouseListener(getControladorTablero());
			lienzo.addMouseMotionListener(getControladorTablero());
		}
		return lienzo;
	}

	/**
	 * Obtiene el controlador del tablero
	 * @return the controladorTablero
	 */
	public ControladorTablero getControladorTablero() {
		if (controladorTablero == null) {
			controladorTablero = new ControladorTablero(this);
		}
		return controladorTablero;
	}

	

}
