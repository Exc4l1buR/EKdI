package kreuzung;

public class Kreuzung {

	public static void main(String[] args) {
		Ampel a00 = new Ampel(0x00);	//Fuﬂg‰nger-Ampeln
		Ampel a02 = new Ampel(0x02);
		Ampel a04 = new Ampel(0x04);
		Ampel a06 = new Ampel(0x06);
		Ampel a08 = new Ampel(0x08);
		Ampel a0a = new Ampel(0x0a);
		
		Ampel a01 = new Ampel(0x01, 5);	//Auto-Ampeln
		Ampel a05 = new Ampel(0x05, 5);
		
		Ampel a03 = new Ampel(0x03, 5);	//Bahn-Ampel
		
		Ampel[] ampelnOHF = {a04, a0a, a01, a05, a03}; //alle Ampeln zusammen, auﬂer die horizontalen Fuﬂg‰nger-Ampeln
		
		for(int i = 1, counterH = 0, counterV = 0; i <= 240; i++) {
			// Sonderfall Bahn
			if (a03.hatWartende()) {
				a03.setStatus(true);
				a00.setStatus(false);
				a02.setStatus(false);
				a04.setStatus(false);
				a06.setStatus(false);
				a08.setStatus(false);
				a0a.setStatus(false);
				a01.setStatus(false);
				a05.setStatus(false);
			} else {
				counterH++;
				// horizontale Ampeln -> gr¸n
				if ((i - (counterH * 70)) % 60 == 0) {
					a00.setStatus(true);
					a02.setStatus(true);
					a06.setStatus(true);
					a08.setStatus(true);
					
					a05.setStatus(true);
					
					counterH++;
				}
				// horizontale Fuﬂg‰nger -> rot
				if ((i - ((counterH - 1) * 70)) % 60 == 8) {
					a00.setStatus(false);
					a02.setStatus(false);
					a06.setStatus(false);
					a08.setStatus(false);
				}
				// horizontale Autos -> rot
				if ((i - ((counterH - 1) * 70) + 30) % 60 == 0) {
					a05.setStatus(false);
				}

				// vertikale Ampeln -> gr¸n
				if ((i - (counterV * 70) + 35) % 60 == 0) {
					a04.setStatus(true);
					a0a.setStatus(true);
					
					a01.setStatus(true);
					
					counterV++;
				}
				// vertikale Fuﬂg‰nger -> rot
				if ((i - ((counterV - 1) * 70) + 35) % 60 == 8) {
					a04.setStatus(false);
					a0a.setStatus(false);
				}
				// vertikale Autos -> rot
				if ((i - ((counterV - 1) * 70) - 5) % 60 == 0) {
					a01.setStatus(false);
				}
			}
			
			for (Ampel a : ampelnOHF) a.tick();
			a00.tick(a02);
			a06.tick(a08);
		}

	}

}
