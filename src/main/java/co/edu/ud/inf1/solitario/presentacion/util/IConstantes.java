/**
 * 
 */
package co.edu.ud.inf1.solitario.presentacion.util;

import java.awt.Color;

/**
 * @author RaspuWIN7
 *
 */
public interface IConstantes {

	public final static int ALTURA_SOMBRA_CARTA = 20;
	public static int ANCHO_FRAME = 1100;
	public static int ALTO_FRAME = 700;
	public static Color BACK_GROUND = new Color(25, 142,29);
	public static int ANCHO_IMG_POKER = 100;
	public static int ALTO_IMG_POKER = 145;
	public final static String CORAZONES = "C";
	public final static String DIAMANTES = "D";
	public final static String PICAS = "P";
	public final static String TREBOLES = "T";
	public final static int TOT_CARTA_TIPO = 13;
	public final static int PXLY_REGION_SUPERIOR = 50;
	public final static int PXL_SEPARACION_CARTAS = 150;
	public final static int PXLX_REGION_SUPERIOR = 50;
	public final static int PXLX_REG_0 = PXLX_REGION_SUPERIOR;
	public final static int PXLX_REG_1 = PXLX_REGION_SUPERIOR + (PXL_SEPARACION_CARTAS * 1);
	public final static int PXLX_REG_2 = PXLX_REGION_SUPERIOR + (PXL_SEPARACION_CARTAS * 2);
	public final static int PXLX_REG_3 = PXLX_REGION_SUPERIOR + (PXL_SEPARACION_CARTAS * 3);
	public final static int PXLX_REG_4 = PXLX_REGION_SUPERIOR + (PXL_SEPARACION_CARTAS * 4);
	public final static int PXLX_REG_5 = PXLX_REGION_SUPERIOR + (PXL_SEPARACION_CARTAS * 5);
	public final static int PXLX_REG_6 = PXLX_REGION_SUPERIOR + (PXL_SEPARACION_CARTAS * 6);
	public final static int PXLY_REGION_INFERIOR = ALTO_IMG_POKER + (2 * PXLY_REGION_SUPERIOR);
	
	public final static int AS = 1;
}
