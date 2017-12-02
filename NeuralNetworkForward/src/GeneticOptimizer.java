import java.io.IOException;

public class GeneticOptimizer {

	NeuralNetworkF[] population;
	
	public GeneticOptimizer(int popSize, int baseLayerCount, int baseLayerSize){
		NeuralNetworkF bestNet = new NeuralNetworkF(5, 1, baseLayerCount, baseLayerSize);
		System.out.println("Starting: C-" + bestNet.lCount + " | S-" + bestNet.lSize);
		
		for(int i=0; i<50; i++){
			population = genNewPop(popSize, bestNet.lCount, bestNet.lSize);
			trainPopulation(population);
			bestNet = findBestMember(population);
			System.out.println("Survived: C-" + bestNet.lCount + " | S-" + bestNet.lSize);
		}
		
	}
	private NeuralNetworkF findBestMember(NeuralNetworkF[] population){
		double lowestError = 1;
		int lowestNumber = -1;
		for(int i=0; i<population.length; i++){
			double error = population[i].getError();
			if(error<lowestError){
				lowestError = error;
				lowestNumber = i;
			}
		}
		return population[lowestNumber];
	}
	private void trainPopulation(NeuralNetworkF[] population){
		for(int i=0; i<population.length; i++){
			population[i].train(10000);
		}
	}
	private NeuralNetworkF[] genNewPop(int popSize, int baseLayerCount, int baseLayerSize){
		NeuralNetworkF[] population = new NeuralNetworkF[popSize];
		for(int i =0; i< popSize; i++){
			if(i==0) population[i] = new NeuralNetworkF(5, 1, baseLayerCount, baseLayerSize);
			else	 population[i] = new NeuralNetworkF(5, 1, getRandomLayerCount(baseLayerCount), getRandomLayerSize(baseLayerSize));//iSize, oSize, lCount, lSize
			population[i].setup(0.01, false, "Zulrah%27s_scales", 0, .50, 200, false);
		}
		return population;
	}
	private int getRandomLayerCount(int base){
		int num = (int) ((Math.random() * 3) + 1);
		if(num < 1)
			num = 1;
		return num;
	}
	private int getRandomLayerSize(int base){
		int num = (int) ((Math.random() * 8) + 1);
		if(num < 1)
			num = 1;
		return num;
	}
	
	public static void main(String[] args) throws IOException{
		
		GeneticOptimizer system =  new GeneticOptimizer(5, 1, 15); //pop Size, base layer count, base layer size
		
	}
}
