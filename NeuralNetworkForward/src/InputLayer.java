import Jama.Matrix;

public class InputLayer extends Layer{

	public InputLayer(int neuronCount, NeuralNetworkF net){
		super(neuronCount, net);
	}
	public void randomizeWeights(){
		return;
	}
	public Matrix genInputs(Matrix mainInput){
		//Add the bias neurons into the input chain
		Matrix output = new Matrix(mainInput.getRowDimension(), mainInput.getColumnDimension()+1);
		output.setMatrix(0, mainInput.getRowDimension()-1, mainInput.getColumnDimension(),
		         mainInput.getColumnDimension(), new Matrix(mainInput.getRowDimension(),1,1.0));
		output.setMatrix(0, mainInput.getRowDimension()-1, 0,
		         mainInput.getColumnDimension()-1, mainInput);
		//Create artificial outputs for the input neurons
		for(int i=0; i<neurons.size();i++)
			neurons.get(i).output =  output.getMatrix(0, output.getRowDimension()-1, i, i).copy();
		return output;
	}
	public Matrix inputToOutput(Matrix inputs) {
		return null;
	}
}
