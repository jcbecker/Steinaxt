/*
Nomes: Leonardo Bianchini e João C. Becker 
Emails: joaoc.becker@hotmail.com e leonardobianchini7@gmail.com

	Essa classe contém o interpretador
*/

class Interpretador {
	private String linhas[];
	public Variavel vars [];
	private String linha;
	public int numvar;//vai conter o numero de variaveis alocadas
	
	public Interpretador(String l[]){
		this.linhas = l;
		vars = new Variavel [1000];
		numvar = 0;
	}
	
	public void Test (){
		vars [0]= new Int ("number",7);
		vars [1]= new Real ("pi",3.1415);
		vars [2]= new Text ("texto","James_hetfield");
		System.out.println (vars[0].getNome() +" = " +((Int)vars[0]).getValor());
		System.out.println (vars[1].getNome() +" = "+((Real)vars[1]).getValor());
		System.out.println (vars[2].getNome() +" = "+((Text)vars[2]).getConteudo());
		
	}
	
	public void interpreta(int i) {//parametro int para poder fazer recurção na interpretação, i = linha
		for(; i < this.linhas.length; i++) {
			if(this.linhas[i] != null) {
				linha = linhas[i].trim();//faz cópia e retira tabulação, espaços no inicio e final
//				linha = linha.replaceAll(" ", "");
				if (linha.startsWith("int ")){
					System.out.println ("Declaração de inteiro na linha "+ (i+1));
					
				}
				else if (linha.startsWith ("real ")){
					System.out.println ("Declaração de real na linha"+ (i+1));
					
				}
				
				else if (linha.startsWith ("text ")){
					System.out.println ("Declaração de string na linha"+ (i+1));
					
				}
				
				
				else if (linha.startsWith ("loop ")){
					System.out.println ("Inicio de laço na linha "+ (i+1));
					
				}
				
				else if (linha.startsWith ("if ")){
					System.out.println ("If reconhecido na linha "+ (i+1));
					
				}
				
				else if (linha.startsWith ("show")){
					System.out.println("Comando de saida reconhecido na linha "+ (i+1));
					
				}
				
				else if (linha.contains ("=")){
					System.out.println ("Atribuição reconhecida na linha "+(i+1));
					
				}
				
//				System.out.println("Linha " + (i + 1) + ":" + linha);
				
				
				
				
				
				
				
				
				
			}
		}
	}
	
	
	
	
	
}
