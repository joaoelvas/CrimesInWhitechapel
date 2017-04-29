
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class que se destina a representar o Whitechapel
 */

/**
 * @author Rodolfo Ferreira n 41654
 * @author Joao Elvas n 41934
 */
public class Whitechapel {

	private static final String NO_SOLUTION= "NO SOLUTION";

	int locations [][]; // estrutura que guarda os sussesores no indice de cada no
	int numNodes; // numero de nos
	boolean []found ; // vector de boleanos que no indice do no diz se ja foi explorado ou nao
	int [] equals; // vector que tem todos os numeros iguais depois das iteracoes
	int equalNumbers ; // todos os numeros iguais


	public Whitechapel(int numLocations ){
		locations = new int [numLocations + 1][];
		locations[0] =  null;
		found = new boolean [numLocations + 1];
		equals = null;
		numNodes = numLocations;
		equalNumbers = 0;

	}


	/**
	 * coloca na estrutura de dados no indice = node todos os seus sucessores 
	 * @param node - indice do no
	 * @param sucessores - todos os sucessores desse no
	 */
	public void addSucessor(int node , String[] sucessores ) {
		int [] tmp = new int [sucessores.length-1 ];

		int i = 0;
		while(!sucessores[i].equals("0")){
			tmp[i]=Integer.parseInt(sucessores[i]);
			i++;
		}

		locations[node]= tmp;
	}


	/**
	 * Preenche a estrutura equals na primeira iteracao
	 * @param node indice do no a explorar
	 * @param numberOfTimes 
	 */
	public void bfsExploreFirst(int node , int numberOfTimes){
		Queue <Integer> waiting =  new LinkedList<Integer>();
		waiting.add(node);
		found[node] = true;
		for(int i = 0 ; i< numberOfTimes ; i++){
			waiting = calcNextQueue(waiting);
		}

		int[] tmp = new int[waiting.size()];
		int d = 0;
		while(!waiting.isEmpty()){
			tmp[d]=waiting.poll() ;
			d++;
		}

		equals = tmp;
		found = new boolean [numNodes + 1];
		equalNumbers = equals.length;
	}


	/**
	 * Preenche a estrutura equals depois de numberOfTimes iteracoes
	 * @param node indice do no a explorar
	 * @param numberOfTimes - numero de vezes que se vai esplorar todos os nos da pilha
	 */
	public void bfsExplore(int node , int numberOfTimes){
		Queue <Integer> waiting =  new LinkedList<Integer>();
		waiting.add(node);
		found[node] = true;
		for(int i = 0 ; i< numberOfTimes ; i++){
			waiting = calcNextQueue(waiting);
		}

		int[] tmp = new int[waiting.size()];
		int d = 0;
		while(!waiting.isEmpty()){
			tmp[d]=waiting.poll();
			d++;
		}

		comparation(equals,tmp);
		found = new boolean [numNodes + 1];

	}


	/**
	 * calcula a prosima queue 
	 * @param waiting - queue da iteracao anterior 
	 * @return - prosima queue dessa iteracao 
	 */
	private Queue<Integer> calcNextQueue(Queue<Integer> waiting) {
		Queue <Integer> processed =  new LinkedList<Integer>();
		do{

			int nodeX = waiting.poll();

			for(Integer i : locations[nodeX]) {
				if(!found[i]) {
					processed.add(i);
					found[i] = true;
				}	

			}

		} while(!waiting.isEmpty() );

		return processed;
	}



	/**
	 * Compara dois vectores de inteiros 
	 * @param x - vector a comparar
	 * @param y - vector a comprar
	 */
	private void comparation( int[] x , int[] y  ){

		int[]  tmpVec = new int[x.length];
		equalNumbers = 0;

		for(int i = 0 ; i<x.length ; i++){
			for(int z = 0 ; z < y.length ; z++){

				if( x[i] != 0 && x[i]==y[z] ){
					tmpVec[equalNumbers]=x[i];
					equalNumbers++;
				}
			}

		}
		equals = tmpVec;
	}


	/**
	 * retorna uma String que responde ao problema 
	 * @return - String formatada que responde ao problema 
	 */
	public String getAnswer(){
		String answer = new String() ;

		if(equalNumbers == 0)
			answer = NO_SOLUTION;
		else if(equalNumbers == 1 ){
			answer = String.valueOf(equals[0]);}
		else {

			int[] integerArray = new int[equalNumbers];
			for(int p=0 ; p< equalNumbers ; p++){
				integerArray[p] = equals[p];
			}
			equals = integerArray;
			Arrays.sort(equals);

			answer = String.valueOf(equals[0]);
			int i = 1;
			while(i < equalNumbers ){
				answer = answer.concat(" "+ equals[i]);
				i++;
				if(i== equals.length)
					break;
			}
		}

		return answer;

	}


}
