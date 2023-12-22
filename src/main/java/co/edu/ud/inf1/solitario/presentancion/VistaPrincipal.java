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
	 * M�todo que agrega los componentes a la ventana
	 * del juego solitario
	 */
	private void initComponents() {
		this.add(getLienzo());
	}
	
	/**
	 * M�todo que se ejecuta cuando se desea mover
	 * una carta.
	 * @param x int Posicion horizontal en pxl del tablero d�nde se produjo el evento.
	 * @param y int Posicion vertical en pxl del tablero d�nde se produjo el evento.
	 * @param region int N�mero de region  d�nde se produjo el evento.
	 * @param regSup boolean true si es regi�n superior, false inferior.
	 */
	public void moverCarta(int x, int y, int region , boolean regSup) {
		getModelo().moverCarta(x, y, region, regSup);
	}
	
	/**
	 * M�todo de asignaci�n r�pida de carta producto de un
	 * doble click. Realiza la validaci�n correspondiente
	 * al juego para saber si existe una posible asignaci�n
	 * de la carta que fue clickeada.
	 * @param region int N�mero de region donde fue producido el evento del click.
	 * @param x int Pixel horizonal donde fue producido el evento del click.
	 * @param y int Pixel vertical donde fue producido el evento del click.
	 * @param regSup boolean true si fue en la regi�n superior, false si fue
	 * regi�n inferior.
	 */
	public void asignacionAutomatica(int region, int x , int y , boolean regSup) {
		getModelo().asignacionAutomatica(region, x, y, regSup);
	}
	
	/**
	 * M�todo que cambia la carta del stock
	 * de cartas disponibles para jugar
	 * en la parte superior izquierda.
	 */
	public void cambiarCartaStock() {
		getModelo().cambiarCartaStock();
	}
	
	/**
	 * M�todo d�nde permite dibujar la carta soltada
	 * en una regi�n determinada
	 * @param region int n�mero de region donde se desea soltar la carta
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
