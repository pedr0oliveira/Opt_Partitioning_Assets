
public class MainClass {

	public static void main(String[] args) {

		Population pop = new Population();
		System.out.print("0. ");
		pop.bestCitizen().myPrint();
		Population newPop;

		for (int gen = 1; gen <= Parameters.maxGen; gen++) {

			newPop = new Population();
			for (int i = 0; i < Parameters.popSize; i++) {
				pop.getCitizen(i).calculateFitness();
			}
			int newPopIndivs = 0;

			while (newPopIndivs < Parameters.popSize) {

				Individual father = pop.select();
				Individual mother = pop.select();

				Individual[] offspring = new Individual[2];

				if (Math.random() < Parameters.crossoverRate) {
					offspring = crossover(father, mother);
				} else {
					offspring[0] = father;
					offspring[1] = mother;
				}

				offspring[0].mutate();
				offspring[1].mutate();

				if (newPopIndivs < Parameters.popSize) {
					newPop.setCitizen(newPopIndivs, offspring[0]);
					newPopIndivs++;
				}

				if (newPopIndivs < Parameters.popSize) {
					newPop.setCitizen(newPopIndivs, offspring[1]);
					newPopIndivs++;
				}

			} // end of the internal cycle
			System.out.print(gen + ". ");
			newPop.bestCitizen().myPrint();
			pop = newPop;

		} // end of the external cycle

	} // end of main

	static public Individual[] crossover(Individual father, Individual mother) {

		Individual[] offspring = new Individual[2];
		offspring[0] = new Individual();
		offspring[1] = new Individual();

		int index = (int) (Math.random() * (Parameters.structureLength - 1));

		for (int i = 0; i < Parameters.structureLength; i++) {
			if (i <= index) {
				offspring[0].setCharacter(i, father.getCharacter(i));
				offspring[1].setCharacter(i, mother.getCharacter(i));
			} else {
				offspring[0].setCharacter(i, mother.getCharacter(i));
				offspring[1].setCharacter(i, father.getCharacter(i));
			}
		}
		return offspring;

	}

}
