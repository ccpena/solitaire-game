import co.edu.ud.inf1.solitario.presentancion.Modelo;


public class Launcher {

	private static Modelo aplicacion;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		aplicacion = new Modelo();
		aplicacion.iniciarSolitario();
	}

}
