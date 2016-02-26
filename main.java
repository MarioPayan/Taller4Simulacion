public class main{
	public static void main(String[] args) {
		
		Lectura lectura = new Lectura();
		lectura.leer();

		Algoritmo algoritmo = new Algoritmo(lectura.getMatrix());
		algoritmo.resolverIterando(Integer.parseInt(args[0]));


	}
}