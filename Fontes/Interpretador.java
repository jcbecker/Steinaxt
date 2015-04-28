/*
Nomes: Leonardo Bianchini e João C. Becker 
Emails: joaoc.becker@hotmail.com e leonardobianchini7@gmail.com

	Essa classe contém o interpretador
*/

class Interpretador {
	private String linhas[];
	public Variavel vars [];
	public int numvar;//vai conter o numero de variaveis alocadas
	private Ulamanager ula;
	private Varsmanager Varsm;
	
	public Interpretador(String l[]){
		this.linhas = l;
		vars = new Variavel [1000];
		numvar = 0;
		ula = new Ulamanager();
		Varsm = new Varsmanager();
	}
	
	public void Test (){
		vars [numvar]= new Int ("number",7);
		numvar++;
		vars [numvar]= new Real ("pi",3.1415);
		numvar++;
		vars [numvar]= new Text ("texto","James_hetfield");
		numvar++;
		
	}
	
	public void interpreta(int i, int endinter) {//parametro int para poder fazer recurção na interpretação, i = linha
		for(; i < endinter; i++) {
			if(this.linhas[i] != null) {
				String linha = new String (linhas[i].trim());//faz cópia e retira tabulação, espaços no inicio e final
				
				
				if (linha.startsWith("int ")){//declaração de inteiro
					
					
					System.out.println ("Debug: Declaração de inteiro na linha "+ (i+1));
					String varsline = new String (linha.substring (4));
					if (!(varsline.isEmpty())){
						if (varsline.contains(",")){//declara mais que um inteiro
							String[] varofline = varsline.split(",");
							
							for (int k=0; k<varofline.length; k++){
								if (varofline[k].contains("=")){
									
									String[] atributo = varofline[k].split("=");
									
									if (atributo.length != 2){
										System.out.println ("Erro na linha "+(i+1)+" declaração não reconhecida");
										System.out.println ("Erro perto de "+ varofline[k]);
										return ;
									}
									varofline[k]=atributo[0].trim();
									int a= 0;
									try{
										a= Integer.parseInt(atributo[1].trim());
									}catch (NumberFormatException e) {
										System.out.println("Erro na linha "+(i+1)+" declaração não reconhecida para catch numb");
										System.out.println ("Erro perto de "+ varofline[k]);
										return ;
									}
									if (Varsm.validname(varofline[k], i+1, vars)){
										vars [numvar]= new Int (varofline[k],a);
										numvar++;
									}else{
										return;
									}
								}
								else{//declara sem atribuição
									varofline[k]=varofline[k].trim();
									if (Varsm.validname(varofline[k], i+1, vars)){
										vars [numvar]= new Int (varofline[k],0);
										numvar++;
									}else{
										return;
									}
								}
//								System.out.println ("Debug: "+varofline[k]);
							}
						}else{
							if (varsline.contains("=")){//uma variavel com atrbuição
								String[] atributo = varsline.split("=");
								if (atributo.length != 2){
									System.out.println ("Erro na linha "+(i+1)+" declaração não reconhecida");
									System.out.println ("Erro perto de "+ varsline);
									return ;
								}
								varsline=atributo[0].trim();
								int a= 0;
								try{
									a= Integer.parseInt(atributo[1].trim());
								}catch (NumberFormatException e) {
									System.out.println("Erro na linha "+(i+1)+" declaração não reconhecida para catch numb");
									System.out.println ("Erro perto de "+ varsline);
									return ;
								}
								if (Varsm.validname(varsline, i+1, vars)){
									vars [numvar]= new Int (varsline,a);
									numvar++;
								}else{
									return;
								}
								
								
							}else{//uma variavel sem atribuição
								varsline=varsline.trim();
								if (Varsm.validname(varsline, i+1, vars)){
									vars [numvar]= new Int (varsline,0);
									numvar++;
								}else{
									return;
								}
								
							}
								
						}
						
					}else{
						System.out.println ("Erro na linha "+(i+1)+" declaração não reconhecida");
						return;
						
					}
					
				}
				else if (linha.startsWith ("real ")){
					System.out.println ("Debug: Declaração de real na linha"+ (i+1));
					
					String varsline = new String (linha.substring (5));
					if (!(varsline.isEmpty())){
						if (varsline.contains(",")){//declara mais que um real
							String[] varofline = varsline.split(",");
							
							for (int k=0; k<varofline.length; k++){
								if (varofline[k].contains("=")){//faz atrbuição
									
									String[] atributo = varofline[k].split("=");
									
									if (atributo.length != 2){
										System.out.println ("Erro na linha "+(i+1)+" declaração não reconhecida");
										System.out.println ("Erro perto de "+ varofline[k]);
										return ;
									}
									varofline[k]=atributo[0].trim();
									double a= 0.0;
									try{
										a= Double.parseDouble(atributo[1].trim());//Double.parseDouble(text);
									}catch (NumberFormatException e) {
										System.out.println("Erro na linha "+(i+1)+" declaração não reconhecida para catch numb");
										System.out.println ("Erro perto de "+ varofline[k]);
										return ;
									}
									if (Varsm.validname(varofline[k], i+1, vars)){
										vars [numvar]= new Real (varofline[k],a);
										numvar++;
									}else{
										return;
									}
								}
								else{//declara sem atribuição
									varofline[k]=varofline[k].trim();
									if (Varsm.validname(varofline[k], i+1, vars)){
										vars [Varsm.getNewVarPos(vars)]= new Real (varofline[k],0.0);
										numvar++;
									}else{
										return;
									}
								}
//								System.out.println ("Debug: "+varofline[k]);
							}
						}else{
							if (varsline.contains("=")){//uma variavel com atrbuição
								String[] atributo = varsline.split("=");
								if (atributo.length != 2){
									System.out.println ("Erro na linha "+(i+1)+" declaração não reconhecida");
									System.out.println ("Erro perto de "+ varsline);
									return ;
								}
								varsline=atributo[0].trim();
								double a= 0.0;
								try{
									a= Double.parseDouble(atributo[1].trim());
								}catch (NumberFormatException e) {
									System.out.println("Erro na linha "+(i+1)+" declaração não reconhecida para catch numb");
									System.out.println ("Erro perto de "+ varsline);
									return ;
								}
								if (Varsm.validname(varsline, i+1, vars)){
									vars [numvar]= new Real (varsline,a);
									numvar++;
								}else{
									return;
								}
								
								
							}else{//uma variavel sem atribuição
								varsline=varsline.trim();
								if (Varsm.validname(varsline, i+1, vars)){
									vars [numvar]= new Real (varsline,0.0);
									numvar++;
								}else{
									return;
								}
								
							}
								
						}
						
					}else{
						System.out.println ("Erro na linha "+(i+1)+" declaração não reconhecida");
						return;
						
					}
					
					
				}
				
				else if (linha.startsWith ("text ")){
					System.out.println ("Debug: Declaração de string na linha"+ (i+1));
					
				}
				
				
				else if (linha.startsWith ("loop ")){
					System.out.println ("Debug: Inicio de laço na linha "+ (i+1));
					
				}
				
				else if (linha.startsWith ("if ")){
					System.out.println ("Debug: If reconhecido na linha "+ (i+1));
					
				}
				
				else if (linha.startsWith ("show")){
					System.out.println("Debug: Comando de saida reconhecido na linha "+ (i+1));
					
				}
				
				else if (linha.contains ("=")){
					int posdestino;
					
					System.out.println ("Debug: Atribuição reconhecida na linha "+(i+1));
					
					String[] atr = linha.split("=");
					
					if (atr.length == 2){
						String destino = new String (atr[0].trim());
						String ope = new String (atr[1].trim());
						boolean flagachou=false;
						for (int j=0; j<numvar;j++){
							if (destino.equals(vars[j].getNome())){
								flagachou=true;
								posdestino = j;
							}
						}
						
						if (flagachou){
							System.out.println ("Debug: Destino: "+destino+" Operação: "+ope);
							flagachou=false;
							if (ope.contains(" + ")){//faz soma e atribui
								
							}else if (ope.contains(" - ")){//faz subtração e atribui
								
							}else if (ope.contains(" * ")){//faz multiplicação e atribui
								
							}else if (ope.contains(" / ")){//divide e atribui
								
							}else if (ope.contains(" % ")){//faz mod e atribui
								
							}else{//apenas atribui
								if (ope.charAt(0) >= '0' && ope.charAt(0) <= '9' || ope.charAt(0) == '-' ){
									System.out.println ("Debug: é esperado constante");
									
								}else{
									System.out.println ("Debug: é esperado variavel");
									
									
									
									
								}
								
								
								
								
								
								
								
								
								for (int j=0; j<numvar;j++){
									if (ope.equals(vars[j].getNome())){
										flagachou=true;
									}
									
								}
								
								
								
								
								
							}
							
							
							
							
							
						}else{//não chou variavel de destino
							System.out.println("Erro era esperado uma atribuição na linha "+(i+1));
							System.out.println("Mas não foi encontrado uma variavel com o nome de "+destino);
							return;
							
							
						}
						
						
						
					}else{//dividio as strings em um numero diferente de dois
						System.out.println("Comando da linha "+(i+1)+" não reconhecido");
						return;
						
						
					}
					
					
				}
				
//				System.out.println("Linha " + (i + 1) + ":" + linha);
				
			}
		}
		Varsm.printVars(vars);
	}

	
}
