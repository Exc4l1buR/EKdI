package mitKollision;



import java.io.BufferedWriter;

import java.io.FileWriter;

import java.io.IOException;



public class Kreuzung {



	public static void main(String[] args) throws IOException {

		FileWriter fr = new FileWriter("report.log");

		BufferedWriter br = new BufferedWriter(fr);

		Ampel a00 = new Ampel(0x00); // Fußgänger-Ampeln

		Ampel a02 = new Ampel(0x02);

		Ampel a04 = new Ampel(0x04);

		Ampel a06 = new Ampel(0x06);

		Ampel a08 = new Ampel(0x08);

		Ampel a0a = new Ampel(0x0a);



		Ampel a01 = new Ampel(0x01, 5); // Auto-Ampeln

		Ampel a05 = new Ampel(0x05, 5);



		Ampel a03 = new Ampel(0x03, 5); // Bahn-Ampel



		Ampel[] ampelnOHF = { a04, a0a, a01, a05, a03 }; // alle Ampeln zusammen, außer die horizontalen

															// Fußgänger-Ampeln

		// Für die Ausgabe:

		Ampel[] alleAmpeln = { a00, a01, a02, a03, a04, a05, a06, a08, a0a };

		boolean bas = false; // Bahn aus Süden

		boolean ban = false; // Bahn aus Norden

		int bahncounter = 0;

		int tickcounter = 0; // damit bei einer bahn alles zurückgesetzt wird


		for (int i = 0; i <= 240; i++) {

			System.out.println("Zeit vergangen: " + i);

			br.write("Zeit vergangen: " + i);

			br.newLine();



			// Sonderfall Bahn

			if (bahncounter % 5 == 0) { // Bahn ist 5t auf kreuzung, danach neu spawnen oder auch nicht

				a03.setStatus(false);

				if (bahncounter == 5) {

					tickcounter = 0;

				}

				ban = false;

				bas = false;

				bahncounter = 0;

				long random = Math.round(Math.random() * Math.random() * Math.random()); //damit Bahnen seltener spawnen

				if (a03.hatWartende() && random == 1) {

					if (Math.round(Math.random()) == 0) {

						ban = true;

					} else {

						bas = true;

					}

				}



			}

			if (ban || bas) {

				a03.setStatus(true);

				System.out.println("Bahn!");

				a00.setStatus(false);

				a02.setStatus(false);

				a04.setStatus(false);

				a06.setStatus(false);

				a08.setStatus(false);

				a0a.setStatus(false);

				a01.setStatus(false);

				a05.setStatus(false);

				bahncounter++;



			} else {

				// horizontale Ampeln -> grün

				//System.out.println(tickcounter);

				if (tickcounter % 70 == 0) { // alle 70t

					a00.setStatus(true);

					a02.setStatus(true);

					a06.setStatus(true);

					a08.setStatus(true);



					a05.setStatus(true);



				}

				// horizontale Fußgänger -> rot

				if (tickcounter % (70 - 2) == 10) { // alle 70t und 2t Puffer

					a00.setStatus(false);

					a02.setStatus(false);

					a06.setStatus(false);

					a08.setStatus(false);

					

					a05.setStatus(false);

				}

				// horizontale Autos -> rot

				//if (i % (70 - 5) == 10) { // alle 70t und 5t puffer

					//a05.setStatus(false);

				//}



				// vertikale Ampeln -> grün

				if (tickcounter % 70 == 10) {

					a04.setStatus(true);

					a0a.setStatus(true);



					a01.setStatus(true);



				}

				// vertikale Fußgänger -> rot

				if (tickcounter % (70 - 2) == 20) {

					a04.setStatus(false);

					a0a.setStatus(false);

				}

				// vertikale Autos -> rot

				if (tickcounter % (70 - 5) == 0) {

					a01.setStatus(false);

				}

			}



			for (Ampel a : ampelnOHF)

				a.tick();



			a00.tick(a02);

			a06.tick(a08);

			

			// CONSOLE OUT

				for (Ampel alle : alleAmpeln) {

					System.out.println(alle.toString());

					br.write(alle.toString());	
					
					br.newLine();
					if(a01.status==true&&a08.status==true||a01.status==true&&a06.status==true||a01.status==true&&a02.status==true||a01.status==true&&a00.status==true||a03.status==true&&a02.status==true||a03.status==true&&a00.status==true||a03.status==true&&a06.status==true||a03.status==true&&a08.status==true||a05.status==true&&a04.status==true||a05.status==true&&a0a.status==true||a01.status==true&&a03.status==true||a01.status==true&&a05.status==true||a03.status==true&&a05.status==true)
                      System.out.println("Es gibt eine Kollision");
				}

				System.out.println();

				br.newLine();

			

			tickcounter++;

		}

		br.close();

		fr.close();

		System.out.println("File written in Workspace-Directory (\"report.log\").");

	}



}