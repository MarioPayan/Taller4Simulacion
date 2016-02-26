import java.util.Random;
class Generador {
	private int a;
	private long m;
	private long semilla;
	private Random rdn;

	public Generador (long semilla) {
		if(semilla!=0){
			this.semilla = semilla;
		}else{this.semilla=11749;}
		a = (int) Math.pow(7, 5);
		m = ((int) Math.pow(2, 31) -1);
		rdn = new Random();
	}

	public int get (int max) {
		int randomR = 0;
		double random = 0;
		do{
			random = (double)semilla/m;
			semilla = ((a*semilla + 0)%m);
		}while(random>1);
		randomR = (int) (random*max);
		if(randomR==max){
			randomR--;
		}
		return randomR;
	}

	public int get2 (int max) {
		int randomR = 0;
		double random = 0;
		do{
			random = (double)semilla/m;
			semilla = ((a*semilla + 0)%m);
		}while(random>1);
		randomR = (int) (rdn.nextDouble()*max);
		if(randomR==max){
			randomR--;
		}
		return randomR;
	}
}

