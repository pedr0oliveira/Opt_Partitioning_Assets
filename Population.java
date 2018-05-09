
public class Population {

private Individual[] citizens;
	
	public Population() {
		
		citizens = new Individual[Parameters.popSize];
		for (int i = 0; i < Parameters.popSize; i++) {
			citizens[i] = new Individual();
		}
		
	}
	
	public Individual getCitizen(int index) {
		return citizens[index];
	}
	
	public Individual select() {
		
		return tournamentSelection();
		
	}
	
	private Individual tournamentSelection() {
		
		Individual current = getRandomIndiv();
		Individual best = current.myClone();
		for (int i = 0; i < Parameters.tournamentSize; i++) {
			current = getRandomIndiv();
			if (current.better(best)) {
				best = current.myClone();
			}
		}
		return best;
		
	}
	
	private Individual getRandomIndiv() {
		
		int index = (int) (Math.random() * Parameters.popSize);
		return citizens[index];
	}
	
	public void setCitizen (int index, Individual ind) {
		citizens[index] = ind;
	}
	
	
	public Individual bestCitizen() {
		
		Individual best = citizens[0].myClone();
		for (int i = 1; i < Parameters.popSize; i++) {
			if (citizens[i].better(best)) {
				best = citizens[i].myClone();
			}
		}
		return best;
	}
}
