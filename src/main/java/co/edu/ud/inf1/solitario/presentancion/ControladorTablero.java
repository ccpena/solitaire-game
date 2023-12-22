/**
 * 
 */
package co.edu.ud.inf1.solitario.presentancion;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import co.edu.ud.inf1.solitario.presentacion.util.IConstantes;

/**
 * @author RaspuWIN7
 * 
 */
public class ControladorTablero implements MouseMotionListener, MouseListener {

	private final VistaPrincipal vista;

	private boolean isDragged;

	public ControladorTablero(VistaPrincipal pVista) {
		vista = pVista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent )
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		isDragged = true;
		int eventX = e.getX();
		int eventY = e.getY();
		boolean regSup = false;

		regSup = eventY >= IConstantes.PXLY_REGION_SUPERIOR && eventY <= (IConstantes.PXLY_REGION_SUPERIOR + IConstantes.ALTO_IMG_POKER);
		int[] result = getInfoPosibleRegion(eventX, eventY);
		int posibleReg = result[0];
		int minPxlReg = result[1];
		int maxPxlReg = result[2];

		if (maxPxlReg != -1) {
			if (eventX >= minPxlReg && eventX <= maxPxlReg) {
				getVista().moverCarta(eventX, eventY, posibleReg, regSup);
				return;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int eventX = e.getX();
		int eventY = e.getY();

		if (isDragged) {
			return;
		}

		if (e.getClickCount() == 2) {
			boolean regSup = eventY >= IConstantes.PXLY_REGION_SUPERIOR && eventY <= (IConstantes.PXLY_REGION_SUPERIOR + IConstantes.ALTO_IMG_POKER);
			int[] infoPosibleReg = new int[3];

			infoPosibleReg = getInfoPosibleRegion(eventX, eventY);

			int posibleReg = infoPosibleReg[0];
			int minPxlReg = infoPosibleReg[1];
			int maxPxlReg = infoPosibleReg[2];

			if (maxPxlReg != -1) {
				if (eventX >= minPxlReg && eventX <= maxPxlReg) {
					getVista().asignacionAutomatica(posibleReg, eventX, eventY, regSup);
					return;
				}
			}
		}

		if (eventX >= IConstantes.PXLX_REGION_SUPERIOR && eventX <= (IConstantes.PXLX_REGION_SUPERIOR + IConstantes.ANCHO_IMG_POKER)) {
			if (eventY >= IConstantes.PXLY_REGION_SUPERIOR && eventY <= (IConstantes.PXLY_REGION_SUPERIOR + IConstantes.ALTO_IMG_POKER)) {
				getVista().cambiarCartaStock();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		int eventX = e.getX();
		int eventY = e.getY();
		boolean regSup = false;

		isDragged = false;

		regSup = eventY >= IConstantes.PXLY_REGION_SUPERIOR && eventY <= (IConstantes.PXLY_REGION_SUPERIOR + IConstantes.ALTO_IMG_POKER);
		int[] result = getInfoPosibleRegion(eventX, eventY);

		int posibleReg = result[0];
		int maxPxlReg = result[2];

		if (maxPxlReg != -1 && posibleReg >= 0) {
			getVista().soltarCarta(posibleReg, regSup);
			return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		e.consume();

	}

	/**
	 * Método para obtener la posible región donde se está ejecutando el evento por parte del
	 * usuario en el tablero, basados en las posiciones X y Y, pasadas como parámetro.
	 * 
	 * @param eventX
	 *            int número de pixeles horizontales del tablero donde se ejecutó el evento.
	 * @param eventY
	 *            int número de pixeles verticales del tablero donde se ejecutó el evento.
	 * @return int[] Vector con la información de la región dónde se ejecutó el evento.
	 *         <ul>
	 *         <li><b>Posición 0 : </b> Posible número de región en el tablero dónde se ejecutó el
	 *         evento.</li>
	 *         <li><b>Posición 1 : </b> Mínimo número de de pixeles horizontales de la región dónde
	 *         se ejecutó el evento.</li>
	 *         <li><b>Posición 2 : </b> Mínimo número de de pixeles verticales de la región dónde se
	 *         ejecutó el evento</li>
	 *         </ul>
	 */
	private int[] getInfoPosibleRegion(int eventX, int eventY) {
		int[] result = new int[3];
		int posibleReg = -1;

		posibleReg = eventX / IConstantes.PXL_SEPARACION_CARTAS;

		int maxPxlReg = -1;
		int minPxlReg = -1;
		switch (posibleReg) {
		case 0:
			minPxlReg = IConstantes.PXLX_REG_0;
			maxPxlReg = IConstantes.PXLX_REG_0 + IConstantes.ANCHO_IMG_POKER;
			break;
		case 1:
			minPxlReg = IConstantes.PXLX_REG_1;
			maxPxlReg = IConstantes.PXLX_REG_1 + IConstantes.ANCHO_IMG_POKER;
			break;
		case 2:
			minPxlReg = IConstantes.PXLX_REG_2;
			maxPxlReg = IConstantes.PXLX_REG_2 + IConstantes.ANCHO_IMG_POKER;
			break;
		case 3:
			minPxlReg = IConstantes.PXLX_REG_3;
			maxPxlReg = IConstantes.PXLX_REG_3 + IConstantes.ANCHO_IMG_POKER;
			break;
		case 4:
			minPxlReg = IConstantes.PXLX_REG_4;
			maxPxlReg = IConstantes.PXLX_REG_4 + IConstantes.ANCHO_IMG_POKER;
			break;
		case 5:
			minPxlReg = IConstantes.PXLX_REG_5;
			maxPxlReg = IConstantes.PXLX_REG_5 + IConstantes.ANCHO_IMG_POKER;
			break;
		case 6:
			minPxlReg = IConstantes.PXLX_REG_6;
			maxPxlReg = IConstantes.PXLX_REG_6 + IConstantes.ANCHO_IMG_POKER;
			break;
		default:
			break;
		}

		result[0] = posibleReg;
		result[1] = minPxlReg;
		result[2] = maxPxlReg;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		e.consume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		e.consume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		e.consume();
	}

	/**
	 * Obtiene
	 * 
	 * @return the vista
	 */
	public VistaPrincipal getVista() {
		return vista;
	}

}
