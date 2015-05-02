/*
Nomes: Leonardo Bianchini e João C. Becker 
Emails: joaoc.becker@hotmail.com e leonardobianchini7@gmail.com

	Essa classe contém o interpretador
*/

class Interpretador {
	private String linhas[];
	public Variavel vars [];
	public static boolean error;
	
	
	public Interpretador(String l[]){
		this.linhas = l;
		vars = new Variavel [1000];
		Interpretador.error=false;
		
		
	}
	
	public void Test (){
		Varsmanager varmanager;
		varmanager = new Varsmanager();
		vars [(varmanager.getNewVarPos(vars))]= new Int ("number",7);
		vars [(varmanager.getNewVarPos(vars))]= new Real ("pi",3.1415);
		vars [(varmanager.getNewVarPos(vars))]= new Text ("texto","James_hetfield");
		
	}
	
	public void interpreta(int i, int endinter) {//parametro int para poder fazer recurção na interpretação, i = linha
		Varsmanager varmanager;
		Ulamanager ula;
		Entradasaida IO;
		ula = new Ulamanager();
		varmanager = new Varsmanager();
		IO = new Entradasaida();
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
									int a= ula.getIntOpe(atributo[1], vars);
									if (Interpretador.error){
										System.out.println ("Erro na linha "+(i+1));
										return;
									}
									if (varmanager.validname(varofline[k], i+1, vars)){
										vars [varmanager.getNewVarPos(vars)]= new Int (varofline[k],a);
										//falta add o error de varmanager.getNewVarPos(vars) retornar -1
									}else{
										return;
									}
								}
								else{//declara sem atribuição
									varofline[k]=varofline[k].trim();
									if (varmanager.validname(varofline[k], i+1, vars)){
										vars [varmanager.getNewVarPos(vars)]= new Int (varofline[k],0);
										//falta add error de varmanager.getNewVarPos(vars) retornar -1
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
								int a= ula.getIntOpe(atributo[1],vars);
								if(Interpretador.error){
									System.out.println("Erro: na linha "+(i+1));
									return;
								}
								if (varmanager.validname(varsline, i+1, vars)){
									vars [varmanager.getNewVarPos(vars)]= new Int (varsline,a);
									//falta add o error de varmanager.getNewVarPos(vars) retornar -1 
								}else{
									return;
								}
								
								
							}else{//uma variavel sem atribuição
								varsline=varsline.trim();
								if (varmanager.validname(varsline, i+1, vars)){
									vars [varmanager.getNewVarPos(vars)]= new Int (varsline,0);
									//falta add o error de varmanager.getNewVarPos(vars) retornar -1
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
									double a= ula.getRealOpe(atributo[1],vars);
									if (Interpretador.error){
										System.out.println ("Erro: na linha "+(i+1));
										return;
									}
									if (varmanager.validname(varofline[k], i+1, vars)){
										vars [varmanager.getNewVarPos(vars)]= new Real (varofline[k],a);
										//falta add o error de varmanager.getNewVarPos(vars) retornar -1
									}else{
										return;
									}
								}
								else{//declara sem atribuição
									varofline[k]=varofline[k].trim();
									if (varmanager.validname(varofline[k], i+1, vars)){
										vars [varmanager.getNewVarPos(vars)]= new Real (varofline[k],0.0);
										//falta add o error de varmanager.getNewVarPos(vars) retornar -1
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
								double a= ula.getRealOpe(atributo[1], vars);
								if(Interpretador.error){
									System.out.println ("Erro: na linha "+(1+1));
									return;
								}
								if (varmanager.validname(varsline, i+1, vars)){
									vars [varmanager.getNewVarPos(vars)]= new Real (varsline,a);
									//falta add o error de varmanager.getNewVarPos(vars) retornar -1
								}else{
									return;
								}
								
								
							}else{//uma variavel sem atribuição
								varsline=varsline.trim();
								if (varmanager.validname(varsline, i+1, vars)){
									vars [varmanager.getNewVarPos(vars)]= new Real (varsline,0.0);
									//falta add o error de varmanager.getNewVarPos(vars) retornar -1
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
					String argumento = new String(linha.substring(4));//String varsline = new String (linha.substring (4));
					argumento=IO.getOutput(argumento, vars);
					if (Interpretador.error){
						System.out.println("Erro: na linha "+(i+1)+", era esperado um comando de saida");
						return;
					}
					System.out.println (argumento);
					
				}
				
				else if (linha.contains ("=")){
					System.out.println ("Debug: Atribuição reconhecida na linha "+(i+1));
					String[] atribuicao = linha.split("=");
					
					if (atribuicao.length != 2){
						Interpretador.error=true;
						System.out.println("Erro: era esperedo uma atribuição na linha "+(i+1)+" mas o comando é desconhecido");
						return;
					}
					String destino = new String (atribuicao[0].trim());
					String origem = new String (atribuicao[1].trim());
					
					int posdestino = varmanager.getVarPos(destino, vars);
					if (posdestino == -1){
						Interpretador.error=true;
						System.out.println("Erro: na linha "+(i+1)+" não foi encontrado variavel com o nome "+destino);
						return;
					}
					if (vars[posdestino] instanceof Int){
						((Int)vars[posdestino]).setValor(ula.getIntOpe(origem,vars));
						if (Interpretador.error){
							System.out.println ("Erro: o erro foi na linha "+(i+1)+" era esperado atribuição para int");
							return;
						}
						
					}else if (vars[posdestino] instanceof Real){
						((Real)vars[posdestino]).setValor(ula.getRealOpe(origem,vars));
						if (Interpretador.error){
							System.out.println ("Erro: o erro foi na linha "+(i+1)+" era esperado atribuição para real");
							return;
						}
					}
				}
				else if (linha.equals("]")){
					System.out.println ("Debug: fecha escopo reconhecido na linha "+(i+1));
					
				}
				
//				System.out.println("Linha " + (i + 1) + ":" + linha);
				
			}
		}
		varmanager.printVars(vars);
	}

	
}
