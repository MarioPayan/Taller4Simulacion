public class main{
	public static void main(String[] args) {
		
		Lectura lectura = new Lectura();
		lectura.leer(args[1]);

		Algoritmo algoritmo = new Algoritmo(lectura.getMatrix());
		algoritmo.pasoOrden = false;
		algoritmo.resolverIterando(Integer.parseInt(args[0]));


	}
}