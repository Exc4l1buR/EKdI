package kreuzung;

import java.util.Arrays;

public class Ampel {

	private int[] trasse1;
	private int[] trasse2;
	private boolean status;
	private int nummer;
	
	public Ampel(int nummer, int trassenL�nge) {
		this.nummer = nummer;
		trasse1 = new int[trassenL�nge];
		trasse2 = new int[trassenL�nge];
	}
	
	public void schalten() {
		status = !status;
	}
	
	public void tick() {
		trasse1[0] += Math.round(Math.random());
		trasse2[0] += Math.round(Math.random());
		if (status) {
			for (int i = trasse1.length-1; i > 0; i--) {
				trasse1[i] = trasse1[i-1];
				trasse2[i] = trasse2[i-1];
			}
		}
	}
	
	public void tick(Ampel n�chster) {
		this.trasse1[0] += Math.round(Math.random());
		this.trasse2[0] += Math.round(Math.random());
		if (this.status) {
			n�chster.trasse1[0] += this.trasse1[trasse1.length-1];
			this.trasse2[0] += n�chster.trasse2[n�chster.trasse2.length-1];
			for (int i = trasse1.length-1; i > 0; i--) {
				this.trasse1[i] = this.trasse1[i-1];
				this.trasse2[i] = this.trasse2[i-1];
			}
		}
		n�chster.tick();
	}
	
	public String toString() {
		return ("Nummer: " + nummer +
				"\nStatus: " + (status?"gr�n":"rot") +
				"\nTrasse 1: " + Arrays.toString(trasse1) +
				"\nTrasse 2: " + Arrays.toString(trasse2));
	}

}
