/*
Nomes: Leonardo Bianchini e João C. Becker 
Emails: joaoc.becker@hotmail.com e leonardobianchini7@gmail.com

	Essa classe contém o interpretador
*/

class Interpretador {
	private String linhas[];
	
	public void interpreta(String l[]) {
		
		this.linhas = l;
		String [] variaveis = new String[2000];
		Real [] valores = new Real[2000];//usar real.setValor
		
		int i=0, n=0, tVariaveis=0, a=0, b=0,z=0, volta=0;
		String linhaAtual, compara="vazio", aux="vazio";
		
		Variavel objVariavel = new Variavel();
		
		String [] variaveis = new String[2000];
		double [] valores = new double[2000];
		
		for(int i = 0; i < linhas.length; i++) {
			if(linhas[i] != null) {
				// TODO: interpretar a linha
				linhaAtual = linhas[i];
				
				if(linhaAtual.endsWith("?")){ //identifica que é uma declaração de variavel
					variaveis[n]=linhaAtual.replaceAll("?","");//tira o ? da variavel
					valores[n]=0; /*variavel inicializa com 0*/
					n++;
					tVariaveis++;
				}
				
				if(linhaAtual.contains("=")){//procura token de atribuição
					//chama a verificacao do operador
				}
				if(linhaAtual.contains("if")){
					//chama a verificacao da condicao
				}	
				if(linhaAtual.contains("fi")){
					//chama a verificacao do fim condicao
				}
				if(linhaAtual.contains("loop")){
					//chama a verificacao do laco
				}
				if(linhaAtual.contains("pool")){
					//chama a verificacao do fim do laco
				}
				
			}
		}
	}
}

