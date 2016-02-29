public class main{
	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		Lectura lectura = new Lectura();
		lectura.leer(args[1]);

		Algoritmo algoritmo = new Algoritmo(lectura.getMatrix());
		algoritmo.pasoOrden = false;
		algoritmo.resolverIterando(Integer.parseInt(args[0]));

		/*
		int iteracionesTotales = 0;
		int n=100;
		for(int i=0;i<n;i++){
			algoritmo.resolverIterando(Integer.parseInt(args[0]));
			iteracionesTotales += algoritmo.intentosNecesarios;
		}
		System.out.println("-----------------------");
		System.out.println("-----------------------");
		System.out.println("-----------------------");
		System.out.println("Se realizaron "+n+ " pruebas sobre " + args[1]);
		double porcentaje = ((double) n * (double) 100) / (double) iteracionesTotales;
		System.out.println("Para encontrar 100 aciertos se requirieron "+iteracionesTotales+" intentos " + "(%" + porcentaje + ")");
		*/
		long stop = System.currentTimeMillis();
		long time = stop - start;
		System.out.println("Tiempo de ejecución: "+ ((double) time / (double) 1000)+ " Segundos");

	}
}