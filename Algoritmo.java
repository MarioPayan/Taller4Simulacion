public class Algoritmo{
	private int matrix[][];
	private int matrixOriginal[][];
	private int faltantes;
	private Generador generador;
	private int tamPosibilidades;
	private boolean solucionado;
	public boolean pasoOrden;

	public int intentosNecesarios;

	public Algoritmo(int[][] matrix){
		this.matrix = matrix;
		matrixOriginal = new int[9][9];
		copiadoMatrix(matrix, matrixOriginal);
		faltantes = 81;
		generador = new Generador(0);
		tamPosibilidades = 0;
		solucionado = false;
		pasoOrden = true;
	}

	public void resolverIterando(int intentos){
		int intento = 0;
		int cerca = 81;
		int faltantesOriginal = contarFaltantes();
		do{
			System.out.println("----------Intento-"+intento+"---------");
			pintar();
			copiadoMatrix(matrixOriginal, matrix);
			System.out.println("Le faltaron: " + faltantes + " celdas");
			if(faltantes<cerca){cerca=faltantes;}
			intento++;
			System.out.println("");
			if(intento==(intentos+1)){
				break;
			}
		}while(!resolver());
		
		if(solucionado){
			System.out.println("SI se alcanzó la solucion:");
			cerca = 0;
			pintar();
			System.out.println("");
			intentosNecesarios = intento;
			System.out.println("Numero de iteraciones: "+ intento);
		}else{
			System.out.println("NO se alcanzó la solucion");
			System.out.println("Numero de iteraciones: "+ (intento-1));
		}
		System.out.println("Celdas faltantes originalmente: "+ faltantesOriginal);
		System.out.println("Minimo de celdas faltantes: "+ cerca);
	}

	public boolean resolver(){
		tamPosibilidades = -1;
		faltantes = contarFaltantes();
		boolean exito = true;
		int[] posiciones;
		for(int i=0;i<faltantes;i++){
			if(pasoOrden){posiciones = seleccionarEnOrden();}
			else{posiciones = seleccionarPorPeso();}
			if(!resolverCasilla(posiciones[0], posiciones[1])){
				faltantes -= i+1;
				exito = false;
				break;
			}
		}
		if(exito==true){
			faltantes=0;
			solucionado=true;
		}
		return exito;
	}

	public boolean[] posibles(int fila, int columna){
		boolean[] posibles = {true, true, true, true, true, true, true, true, true};
		int valor = 0;

		int fila33 = 0;
		if(fila<=2){fila33=0;}
		else if(fila<=5){fila33=3;}
		else if(fila<=8){fila33=6;}
		int fila33B = fila33+3;int columna33 = 0;

		if(columna<=2){columna33=0;}
		else if(columna<=5){columna33=3;}
		else if(columna<=8){columna33=6;}
		int columna33B = columna33+3;

		for(int ifila=fila33;ifila<fila33B;ifila++){
			for(int icolu=columna33;icolu<columna33B;icolu++){
				valor = matrix[ifila][icolu]-1;
				if(valor<0){continue;}
				posibles[valor] = false;
			}
		}

		for(int i=0;i<9;i++){
			valor = matrix[i][columna]-1;
			if(valor<0){continue;}
			posibles[valor] = false;
		}

		for(int i=0;i<9;i++){
			valor = matrix[fila][i]-1;
			if(valor<0){continue;}
			posibles[valor] = false;
		}

		int posiblesCount = 0;
		for(int j=0;j<9;j++){
			if(posibles[j]){
				posiblesCount++;
			}
		}
		tamPosibilidades = posiblesCount;

		return posibles;
	}

	public int[] seleccionarEnOrden(){
		int[] seleccionados = {-1, -1};
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(matrix[i][j]==0){
					seleccionados[0]=i;
					seleccionados[1]=j;
					return seleccionados;
				}
			}
		}
		return seleccionados;
	}

	public int[] seleccionarPorPeso(){
		int[][] matrixPesos = new int[9][9];
		int[] seleccionados = {-1, -1};
		int pesoMinimo = 10;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(matrix[i][j]==0){
					posibles(i,j);
					matrixPesos[i][j] = tamPosibilidades;
				}else{
					matrixPesos[i][j] = 9;
				}
			}
		}

		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(matrixPesos[i][j]<pesoMinimo){
					pesoMinimo = matrixPesos[i][j];
					seleccionados[0]=i;
					seleccionados[1]=j;
				}
			}
		}
		return seleccionados;
	}

	public int contarFaltantes(){
		int faltan=0;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(matrix[i][j]==0){
					faltan++;
				}
			}
		}
		return faltan;
	}

	public boolean resolverCasilla(int fila, int columna){
		boolean[] posibles = posibles(fila, columna);
		if(tamPosibilidades==0){
			return false;
		}

		int valor = generador.get2(tamPosibilidades);
		int[] posiblesNumeros = new int[tamPosibilidades];
		int k = 0;
		for(int j=0;j<9;j++){
			if(posibles[j]){
				posiblesNumeros[k] = j+1;
				k++;
			}
			
		}
		k--;

		matrix[fila][columna] = posiblesNumeros[valor];
		return true;
	}

	public void pintar(){
		String linea = "";
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				linea += matrix[i][j] + " ";
			}
			System.out.println(linea);
			linea = "";
		}
	}

	public void copiadoMatrix(int[][] origen, int[][] destino){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				destino[i][j] = origen[i][j];
			}
		}
	}
}