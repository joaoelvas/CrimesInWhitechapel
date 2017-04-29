import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 */

/**
 * @author Rodolfo Ferreira n 41654
 * @author Joao Elvas n 41934
 */
public class Main {

	public static void main(String[] args) throws IOException {	
		BufferedReader in = new BufferedReader(new InputStreamReader (System.in));
		int numberOfLocations = Integer.parseInt(in.readLine());
		Whitechapel whiteChapel =  new Whitechapel(numberOfLocations);

		for(int n = 1 ; n <= numberOfLocations ; n++) {
			String[] sucessores = in.readLine().split(" ");
			whiteChapel.addSucessor(n, sucessores);
		}


		int numberOfClues = Integer.parseInt(in.readLine());
		int node;
		int  distance;
		String[] tmp = in.readLine().split(" ");

		
		node = Integer.parseInt(tmp[0]);
		distance = Integer.parseInt(tmp[1]);

		whiteChapel.bfsExploreFirst(node,distance);

		if(numberOfClues == 1){
			System.out.println(whiteChapel.getAnswer());
		}
		else{ 
			for(int x = 1 ; x < numberOfClues ; x++){
				tmp = in.readLine().split(" ");
				node = Integer.parseInt(tmp[0]);
				distance = Integer.parseInt(tmp[1]);
				whiteChapel.bfsExplore(node, distance);
			}
			in.close();
			System.out.println(whiteChapel.getAnswer());
		}
	}
}

