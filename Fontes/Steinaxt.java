/*
Nomes: Leonardo Bianchini e João C. Becker 
Emails: joaoc.becker@hotmail.com e leonardobianchini7@gmail.com

	Essa classe contém a main do interpretador
*/
import java.util.Scanner;
import java.io.*;

class Steinaxt {
	public static void main(String args[]) throws Exception {
		File f;
		Scanner s;
		Interpretador b;
		String linhas[] = new String[2000]; // arquivo pode ter, no máximo, 2000 linhas.
		
		try {
			f = new File(args[0]);
			s = new Scanner(f);
			b = new Interpretador();
			
			int i = 0;
			while(s.hasNext()) {
				linhas[i] = s.nextLine();
				i++;
			}
			
			b.interpreta(linhas);
		} catch {
			
		}
    }
}
