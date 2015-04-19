/*
Nomes: Leonardo Bianchini e João C. Becker 
Emails: joaoc.becker@hotmail.com e leonardobianchini7@gmail.com

	Essa classe contém o interpretador
*/

class Interpretador {
	private String linhas[];
	public Variavel vars [];
	
	public Interpretador(String l[]){
		this.linhas = l;
		vars = new Variavel [1000];
	}
	
	public void Test (){
		vars [0]= new Int ("number",7);
		vars [1]= new Real ("pi",3.1415);
		vars [2]= new Text ("texto","James_hetfield");
		System.out.println (vars[0].getNome() +" = " +((Int)vars[0]).getValor());
		System.out.println (vars[1].getNome() +" = "+((Real)vars[1]).getValor());
		System.out.println (vars[2].getNome() +" = "+((Text)vars[2]).getConteudo());
		
	}
	
	public void interpreta() {
		for(int i = 0; i < this.linhas.length; i++) {
			if(this.linhas[i] != null) {
				// TODO: interpretar a linha
				System.out.println("Linha " + (i + 1) + ": " + this.linhas[i]);
			}
		}
	}
}
