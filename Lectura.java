
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lectura {

	private int[][] matrix = new int[9][9];

	public Lectura(){
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				matrix[i][j]=0;
			}
		}
	}

	public void leer() {
		int fila = 0;
		File f = new File("entrada.txt");
		Scanner s;
		try {
			s = new Scanner(f);
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("\\s+");
				for(int columna=0;columna<9;columna++){
					matrix[fila][columna] = Integer.parseInt(sl.next());
				}
				fila++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int[][] getMatrix(){
		return matrix;
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
}