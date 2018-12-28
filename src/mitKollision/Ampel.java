package mitKollision;

public class Ampel {

	int[] trasse1;

	int[] trasse2;

	boolean status;

	int nummer;



	public Ampel(int nummer, int trassenLänge) {

		this.nummer = nummer;

		this.trasse1 = new int[trassenLänge];

		this.trasse2 = new int[trassenLänge];

	}



	public Ampel(int nummer) {

		this.nummer = nummer;

		this.trasse1 = new int[3];

		this.trasse2 = new int[3];

	}



	public void schalten() {

		status = !status;

	}



	public void setStatus(boolean status) {

		this.status = status;

	}



	public void tick() {

		this.trasse1[0] += Math.round(Math.random()*Math.random());

		this.trasse2[0] += Math.round(Math.random()*Math.random());

		if (status) {

			for (int i = this.trasse1.length - 1; i > 0; i--) {

				this.trasse1[i] = this.trasse1[i - 1];

				this.trasse2[i] = this.trasse2[i - 1];

			}

			this.trasse1[0] = 0;

			this.trasse2[0] = 0;

		} else {

			for (int i = this.trasse1.length - 2; i > 0; i--) {

				this.trasse1[i] = this.trasse1[i - 1];

				this.trasse2[i] = this.trasse2[i - 1];

			}

		}

		

	}



	public void tick(Ampel nächster) {

		this.trasse1[0] += Math.round(Math.random()*Math.random());

		this.trasse2[0] += Math.round(Math.random()*Math.random());

		if (this.status) {

			nächster.trasse1[0] += this.trasse1[trasse1.length - 1];

			this.trasse2[0] += nächster.trasse2[nächster.trasse2.length - 1];

			for (int i = trasse1.length - 1; i > 0; i--) {

				this.trasse1[i] = this.trasse1[i - 1];

				this.trasse2[i] = this.trasse2[i - 1];

			}

			this.trasse1[0] = 0;

			this.trasse2[0] = 0;

		} else {

			for (int i = trasse1.length - 2; i > 0; i--) {

				trasse1[i] = trasse1[i - 1];

				trasse2[i] = trasse2[i - 1];

			}

		}

		nächster.tick();

	}



	public boolean hatWartende() {

		for (int i : trasse1) {

			if (i != 0)

				return true;

		}

		for (int i : trasse2) {

			if (i != 0)

				return true;

		}

		return false;

	}



	public String toString() {

		return ("Nummer: " + nummer + "| Status: " + (status ? "grün" : "rot") + "| Trasse 1: " + trasse1[0]

				+ "| Trasse 2: " + trasse2[0]);

	}

}