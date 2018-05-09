
public class Individual {

	private boolean[] structure;
	private double fitness;
	private boolean alreadyCalculated;

	public Individual() {

		structure = new boolean[Parameters.structureLength];
		for (int i = 0; i < Parameters.structureLength; i++) {
			if (Math.random() < 0.5) {
				structure[i] = false;
			} else {
				structure[i] = true;
			}
		}

		alreadyCalculated = false;
		fitness = -1;
	}

	public double calculateFitness() {

		if (alreadyCalculated) {
			return fitness;
		} else {
			double res = 0;
			for (int i = 0; i < Parameters.structureLength; i++) {
				if (structure[i]) {
					res += 1.0;
				}
			}
			fitness = res;
			alreadyCalculated = true;
			return res;
		}
	}

	public void myPrint() {

		for (int i = 0; i < Parameters.structureLength; i++) {
			if (structure[i]) {
				System.out.print("1");
			} else {
				System.out.print("0");
			}
		}
		System.out.println("  " + calculateFitness());

	}

	public Individual myClone() {

		Individual res = new Individual();
		for (int i = 0; i < Parameters.structureLength; i++) {
			res.structure[i] = this.structure[i];
		}
		res.fitness = this.fitness;
		res.alreadyCalculated = this.alreadyCalculated;
		return res;
	}

	public boolean better(Individual another) {
		if (Parameters.maximization) {
			return this.calculateFitness() > another.calculateFitness();
		} else {
			return this.calculateFitness() > another.calculateFitness();
		}

	}

	public void setCharacter(int index, boolean b) {
		structure[index] = b;
	}

	public boolean getCharacter(int index) {
		return structure[index];
	}

	public void mutate() {

		for (int i = 0; i < Parameters.structureLength; i++) {
			if (Math.random() < Parameters.mutationRate) {
				structure[i] = !structure[i];
			}
		}
		alreadyCalculated = false;
	}

}
