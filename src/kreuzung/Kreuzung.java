package kreuzung;

public class Kreuzung {

	public static void main(String[] args) {
		Ampel a00 = new Ampel(0x00);	//Fuﬂg‰nger-Ampeln
		Ampel a02 = new Ampel(0x02);
		Ampel a04 = new Ampel(0x04);
		Ampel a06 = new Ampel(0x06);
		Ampel a08 = new Ampel(0x08);
		Ampel a0a = new Ampel(0x0a);
		
		Ampel a01 = new Ampel(0x01);	//Auto-Ampeln
		Ampel a05 = new Ampel(0x05);
		
		Ampel a03 = new Ampel(0x03);	//Bahn-Ampel
		
		for(int i = 0; i < 240; i++) {
			//TODO passende bedingung ausdenken
			if (true) {
				//TODO zu schaltende Ampeln einf¸gen
			}
			
		}

	}

}
