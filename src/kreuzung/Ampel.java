package kreuzung;

import java.util.Arrays;

public class Ampel {

	private int[] trasse1;
	private int[] trasse2;
	private boolean status;
	private int nummer;
	
	public Ampel(int nummer, int trassenLänge) {
		this.nummer = nummer;
		trasse1 = new int[trassenLänge];
		trasse2 = new int[trassenLänge];
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
	
	public void tick(Ampel nächster) {
		this.trasse1[0] += Math.round(Math.random());
		this.trasse2[0] += Math.round(Math.random());
		if (this.status) {
			nächster.trasse1[0] += this.trasse1[trasse1.length-1];
			this.trasse2[0] += nächster.trasse2[nächster.trasse2.length-1];
			for (int i = trasse1.length-1; i > 0; i--) {
				this.trasse1[i] = this.trasse1[i-1];
				this.trasse2[i] = this.trasse2[i-1];
			}
		}
		nächster.tick();
	}
	
	public String toString() {
		return ("Nummer: " + nummer +
				"\nStatus: " + (status?"grün":"rot") +
				"\nTrasse 1: " + Arrays.toString(trasse1) +
				"\nTrasse 2: " + Arrays.toString(trasse2));
	}

}
