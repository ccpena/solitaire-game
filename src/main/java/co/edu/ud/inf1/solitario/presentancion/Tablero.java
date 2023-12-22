/**
 * 
 */
package co.edu.ud.inf1.solitario.presentancion;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import co.edu.ud.inf1.solitario.logica.Carta;
import co.edu.ud.inf1.solitario.presentacion.util.IConstantes;

/**
 * @author RaspuWIN7
 * 
 */
public class Tablero extends JPanel {

	private BasicStroke bsRegion = new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
	private final static int PXL_HORIZONTAL_FINAL = IConstantes.PXLX_REG_6;
	private Image imgSombra, fondo, imgPortada = null;
	private Carta actualCartaVisibleStock;
	private List<Carta> lstCartasEnMov = null;
	private Map<Integer, List<Carta>> mapSlots = new HashMap<Integer, List<Carta>>();
	private Map<Integer, List<Carta>> mapRegionesInferiores = new HashMap<Integer, List<Carta>>();

	/**
	 * Constructor por defecto
	 */
	public Tablero() {
		imgPortada = new ImageIcon(getClass().getResource("/imagenes/portada.png")).getImage();
		imgSombra = new ImageIcon(getClass().getResource("/imagenes/sombra.png")).getImage();
		fondo = new ImageIcon(getClass().getResource("/imagenes/fondo1.jpg")).getImage();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		dibujarFondo(g2);
		dibujarSlots(g2);
		dibujarCartasSobreTablero(g2);
		dibujarCartasEnMovimiento(g2);
		dibujarCartaStock(g2);
		
	}
	
	/**
	 * Método para dibujar el fondo sobre el tablero
	 * @param g2 Graphics2D Objeto graphics sobre el cuál se dibuja.
	 */
	private void dibujarFondo(Graphics2D g2) {
		g2.drawImage(fondo,0,0,null);		
	}
	
	/**
	 * Método que dibuja la región de los slots superiores.
	 * Se dibuja un rectangulo blanco basado en las posiciones en pixeles
	 * ya definidas para los slots, y en el tamaño de la imagen
	 * de una carta.
	 * @param g2 Graphics2D Objeto graphics sobre el cuál se dibuja.
	 */
	private void dibujarSlots(Graphics2D g2) {
		for (int i = IConstantes.PXLX_REGION_SUPERIOR; i <= PXL_HORIZONTAL_FINAL; i += IConstantes.PXL_SEPARACION_CARTAS) {
			if (i != IConstantes.PXLX_REG_2) {
				g2.setColor(Color.WHITE);
				g2.setStroke(bsRegion);
				g2.drawRect(i, IConstantes.PXLY_REGION_SUPERIOR, IConstantes.ANCHO_IMG_POKER, IConstantes.ALTO_IMG_POKER);
				if (i == IConstantes.PXLX_REGION_SUPERIOR) {
					g2.drawImage(imgPortada, i, IConstantes.PXLY_REGION_SUPERIOR, null);
				}
			}
		}
	}

	
	/**
	 * Método encargado de pintar las imagenes de las cartas
	 * asignadas actualmente a los slots, y a las regiones
	 * inferiores basado en la lista de cartas por mapa
	 * del tablero.
	 * @param g2 Graphics2D Objeto graphics donde se realizará el pintado.
	 */
	private void dibujarCartasSobreTablero(Graphics2D g2) {
		
		if (getMapSlots() != null) {
			for (int region = 3; region <= 6; region++) {
				List<Carta> cartas = getMapSlots().get(region);
				if (cartas != null && !cartas.isEmpty()) {
					for (int pos = 0; pos < cartas.size(); pos++) {
						Carta c = cartas.get(pos);
						asignacionSlotACarta(c,region);
						pintarCarta(g2, c);
					}
				}
			}
		}
		
		if (getMapRegionesInferiores() != null) {
			for (int region = 0; region <= 6; region++) {
				List<Carta> cartas = getMapRegionesInferiores().get(region);
				verificacionVisibilidad(cartas);
				for (int pos = 0; pos < cartas.size(); pos++) {
					Carta c = cartas.get(pos);
					asignarPosicionesInferiores(pos, c, region);
					pintarCarta(g2, c);
				}
			}
		}
	}

	/**
	 * Método que asigna a una carta un slot, modificando las posiciones
	 * actuales de la carta, por las posiciones en pixeles del slot.
	 * @param carta Objeto carta a modificar sus posiciones.
	 * @param numSlot Numero de slot
	 */
	private void asignacionSlotACarta(Carta carta, int numSlot) {
		switch (numSlot) {
		case 3:
			carta.setPosX(IConstantes.PXLX_REG_3);
			break;
		case 4:
			carta.setPosX(IConstantes.PXLX_REG_4);
			break;
		case 5:
			carta.setPosX(IConstantes.PXLX_REG_5);
			break;
		case 6:
			carta.setPosX(IConstantes.PXLX_REG_6);
			break;
		}
		carta.setPosY(IConstantes.PXLY_REGION_SUPERIOR);
	}
	
	/**
	 * Método que dibuja las cartas qué actualmente se encuentran
	 * en movimiento por el usuario, desplazandola desde una región
	 * a otra, o a un slot. Se basta en el objeto lstCartasEnMov. Si la lista
	 * de cartas en movimiento son diferentes de nulas, se dibujará las imagenes
	 * presentes en la lista, tomando como referencia la primera carta
	 * que se encuentre en la lista.
	 * @param g2 Graphics2D Objeto graphics donde se pintará la imagen
	 */
	private void dibujarCartasEnMovimiento(Graphics2D g2) {
		if (getLstCartasEnMov() != null) {
			int totalCartas = getLstCartasEnMov().size();
			int x = 0, y = 0;
			for (int pos = 0; pos < totalCartas; pos++) {
				Carta c = getLstCartasEnMov().get(pos);
				if (pos == 0) {
					x = c.getPosX();
					y = c.getPosY();
				} else {
					y += IConstantes.ALTURA_SOMBRA_CARTA;
				}
				c.setPosY(y);
				g2.drawImage(c.getImg(), x, y, null);
			}
		}
	}
	
	/**
	 * Método para dibujar una imagen de una carta, en la región
	 * del stock (Lista de cartas disponibles) para qué se encuentre disponible 
	 * para su uso. Se basa en el objeto actualCartaVisibleStock, él cuál si
	 * es diferente de nulo, significa qué se necesita que se dibuje una
	 * imagen de una carta en el stock. Si es nulo, no se dibujará nada.
	 * @param g2 Graphics2D Objeto graphics donde se pintará la imagen
	 */
	private void dibujarCartaStock(Graphics2D g2) {
		if (getActualCartaVisibleStock() != null) {
			g2.drawImage(getActualCartaVisibleStock().getImg(), IConstantes.PXLX_REG_1, IConstantes.PXLY_REGION_SUPERIOR, null);
		}
	}

	/**
	 * Método que toma el objeto graphics, y pinta una imagen
	 * sobre él. Si la imagen de la carta pasada como parámetro
	 * es visible, se pinta dicha imagen, si no es visible
	 * si pinta la imagen que hace referencia a una carta volteada.
	 * @param g2 Graphics2D Objeto graphics donde se pintará la imagen
	 * @param c Ojeto Carta que contiene la imagen y las posiciones donde se pintará.
	 */
	private void pintarCarta(Graphics2D g2, Carta c) {
		if (!c.isVisible()) {
			g2.drawImage(imgSombra, c.getPosX(), c.getPosY(), null);
		} else {
			g2.drawImage(c.getImg(), c.getPosX(), c.getPosY(), null);
		}
	}

	/**
	 * Método que asigna a una carta sus nuevas posiciones x,y
	 * en pixeles con respecto al tablero. Aplica únicamente
	 * a las cartas qué se encuentren en las regiones inferiores
	 * del tablero.
	 * @param pos int posición de la carta en la región.
	 * @param carta Carta Objeto a modificar sus posiciones x y y.
	 * @param region int Número de región inferior dónde se va a asignar
	 * la carta.
	 */
	private void asignarPosicionesInferiores(int pos, Carta carta, int region) {
		switch (region) {
		case 0:
			carta.setPosX(IConstantes.PXLX_REGION_SUPERIOR);
			break;
		default:
			carta.setPosX(IConstantes.PXLX_REGION_SUPERIOR + (IConstantes.PXL_SEPARACION_CARTAS * region));
			break;
		}
		carta.setPosY(IConstantes.PXLY_REGION_INFERIOR + (pos * IConstantes.ALTURA_SOMBRA_CARTA));
	}

	/**
	 * Método para verificar la visibilidad de las cartas.
	 * @param lstCarta
	 */
	private void verificacionVisibilidad(List<Carta> lstCarta) {

		if (lstCarta == null || getLstCartasEnMov() != null) {
			return;
		}

		if (lstCarta != null && !lstCarta.isEmpty()) {
			for (Carta c : lstCarta) {
				if (c.isVisible()) {
					return;
				}
			}
		}

		if (!lstCarta.isEmpty()) {
			lstCarta.get(lstCarta.size() - 1).setVisible(true);
		}
	}


	// GETTER && SETTER

	/**
	 * Obtiene
	 * 
	 * @return the mapRegionesSuperiores
	 */
	public Map<Integer, List<Carta>> getMapSlots() {
		return mapSlots;
	}

	/**
	 * Obtiene
	 * 
	 * @return the mapRegionesInferiores
	 */
	public Map<Integer, List<Carta>> getMapRegionesInferiores() {
		return mapRegionesInferiores;
	}

	/**
	 * Asigna
	 * 
	 * @param mapRegionesSuperiores
	 *            the mapRegionesSuperiores to set
	 */
	public void setMapSlots(Map<Integer, List<Carta>> pMapSlots) {
		this.mapSlots = pMapSlots;
	}

	/**
	 * Asigna
	 * 
	 * @param mapRegionesInferiores
	 *            the mapRegionesInferiores to set
	 */
	public void setMapRegionesInferiores(Map<Integer, List<Carta>> mapRegionesInferiores) {
		this.mapRegionesInferiores = mapRegionesInferiores;
	}

	/**
	 * Obtiene
	 * 
	 * @return the actualCartaVisibleStock
	 */
	public Carta getActualCartaVisibleStock() {
		return actualCartaVisibleStock;
	}

	/**
	 * Asigna
	 * 
	 * @param actualCartaVisibleStock
	 *            the actualCartaVisibleStock to set
	 */
	public void setActualCartaVisibleStock(Carta actualCartaVisibleStock) {
		this.actualCartaVisibleStock = actualCartaVisibleStock;
	}

	/**
	 * Obtiene
	 * 
	 * @return the cartasToMove
	 */
	public List<Carta> getLstCartasEnMov() {
		return lstCartasEnMov;
	}

	/**
	 * Asigna
	 * 
	 * @param cartasToMove
	 *            the cartasToMove to set
	 */
	public void setLstCartasEnMov(List<Carta> cartasToMove) {
		this.lstCartasEnMov = cartasToMove;
	}

	/**
	 * Obtiene
	 * @return the fondo
	 */
	public Image getFondo() {
		return fondo;
	}

	/**
	 * Asigna
	 * @param fondo the fondo to set
	 */
	public void setFondo(Image fondo) {
		this.fondo = fondo;
	}
	
	

}
