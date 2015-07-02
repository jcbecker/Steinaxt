/**
 *	Nomes: João Carlos Becker e Leonardo Bianchini
 *	Emails: joaoc.becker@hotmail.com e leonardobianchini7@gmail.com
 *
 *	Essa classe contém o interpretador
*/


class Interpretador {
	private String linhas[];
	public static boolean error;
	public Varsmanager varmanager;
	public Ulamanager ula;
	public Entradasaida IO;


	public Interpretador(String l[]){
		this.linhas = l;
		Interpretador.error=false;
		ula = new Ulamanager(this);
		varmanager = new Varsmanager();
		IO = new Entradasaida(this);
	}


	public void interpreta(int i, int endinter) {//parametro int para poder fazer recurção na interpretação, i = linha

		for(; i < endinter; i++) {
			if(this.linhas[i] != null) {
				String linha = new String (linhas[i].trim());//faz cópia e retira tabulação, espaços no inicio e final

				int tipo = 0;
				if (linha.startsWith("int ")||linha.startsWith("int	") || linha.startsWith ("real ")||linha.startsWith ("real	")){//declaração variavel
					if (linha.startsWith("int ")||linha.startsWith("int	")){
						tipo = 1;
					}
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
									int a= ula.getIntOpe(atributo[1]);
									if (Interpretador.error){
										System.out.println ("Erro na linha "+(i+1));
										return;
									}
									if (varmanager.validname(varofline[k], i+1)){
										if (tipo==1){
											varmanager.vars [varmanager.getNewVarPos()]= new Int (varofline[k],a);
										} else {
											varmanager.vars [varmanager.getNewVarPos()]= new Real (varofline[k],a);
										}
										//falta add o error de varmanager.getNewVarPos(vars) retornar -1
									}else{
										return;
									}
								}
								else{//declara sem atribuição
									varofline[k]=varofline[k].trim();
									if (varmanager.validname(varofline[k], i+1)){
										if (tipo==1){
											varmanager.vars [varmanager.getNewVarPos()]= new Int (varofline[k],0);
										} else {
											varmanager.vars [varmanager.getNewVarPos()]= new Real (varofline[k],0);
										}
										//falta add error de varmanager.getNewVarPos(vars) retornar -1
									}else{
										return;
									}
								}
				//			System.out.println ("Debug: "+varofline[k]);
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
								int a= ula.getIntOpe(atributo[1]);
								if(Interpretador.error){
									System.out.println("Erro: na linha "+(i+1));
									return;
								}
								if (varmanager.validname(varsline, i+1)){
									if (tipo==1){
										varmanager.vars [varmanager.getNewVarPos()]= new Int (varsline,a);
									} else {
										varmanager.vars [varmanager.getNewVarPos()]= new Real (varsline,a);
									}
									varmanager.vars [varmanager.getNewVarPos()]= new Int (varsline,a);
									//falta add o error de varmanager.getNewVarPos(vars) retornar -1
								}else{
									return;
								}


							}else{//uma variavel sem atribuição
								varsline=varsline.trim();
								if (varmanager.validname(varsline, i+1)){
									if (tipo==1){
										varmanager.vars [varmanager.getNewVarPos()]= new Int (varsline,0);
									} else {
										varmanager.vars [varmanager.getNewVarPos()]= new Real (varsline,0);
									}
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

				else if (linha.startsWith ("text ")||linha.startsWith ("text	")){
//					System.out.println ("Debug: Declaração de string na linha"+ (i+1));

				}


				else if (linha.startsWith ("loop ")||linha.startsWith ("loop	")){
//					System.out.println ("Debug: Inicio de laço na linha "+ (i+1));


					if (!(linha.endsWith("["))){
						Interpretador.error=true;
						System.out.println("Erro: era esperado um [ no fim da linha "+(i+1));
						return;
					}

					int endindex = linha.lastIndexOf("[");
					String condicao = linha.substring(5, endindex);
//					System.out.println ("Debug: condição enviada "+condicao);
					boolean resultado = ula.getCondicao(condicao);

					if (Interpretador.error){
						System.out.println("Erro: condição invalida na linha "+(i+1));
						return;
					}
					int escopo_ini= i+1, escopo_fim=i+1, flagjump=1;
					String parselinha;
//					if (resultado) System.out.println("Debug: Condição da linha "+(i+1)+" é verdadeira");
//					else System.out.println("Debug: Condição da linha "+(i+1)+" é falsa");


					while (escopo_fim<endinter){
						if(this.linhas[escopo_fim] != null) {
							parselinha = linhas[escopo_fim].trim();
							if (parselinha.endsWith("["))flagjump++;
							if (parselinha.endsWith("]"))flagjump--;
							if (flagjump==0)break;

						}else{
							Interpretador.error=true;
							System.out.println ("Erro: não foi encontrado um fim de escopo para o if da linha "+(i+1));
							return;
						}
						escopo_fim ++;
					}
//					System.out.println ("Debug: o escopo começa na linha "+(escopo_ini+1)+" e termina na "+(escopo_fim+1));




					if (flagjump!=0){
						Interpretador.error=true;
						System.out.println ("Erro: não foi encontrado um fim de escopo para o if da linha "+(i+1));
						return;
					}
					while(resultado){
						interpreta(escopo_ini,escopo_fim );
						if (Interpretador.error) return;

						resultado = ula.getCondicao(condicao);
						if (Interpretador.error){
							System.out.println("Erro: condição invalida na linha "+(i+1));
							return;
						}
					}
					i=escopo_fim;


				}else if (linha.startsWith ("if ")||linha.startsWith ("if	")){
//					System.out.println ("Debug: If reconhecido na linha "+ (i+1));
					if (!(linha.endsWith("["))){
						Interpretador.error=true;
						System.out.println("Erro: era esperado um [ no fim da linha "+(i+1));
						return;
					}
					int endindex = linha.lastIndexOf("[");
					String condicao = linha.substring(3, endindex);
//					System.out.println ("Debug: condição enviada "+condicao);
					boolean resultado = ula.getCondicao(condicao);
					if (Interpretador.error){
						System.out.println("Erro: condição invalida na linha "+(i+1));
						return;
					}
					if (resultado){
//						System.out.println("Debug: a condição da linha "+(i+1)+" é verdadeira");
					}else{
//						System.out.println("Debug: a condição da linha "+(i+1)+" é falsa");
						int flagjump=1, jump=i+1;
						String parselinha;


						while (jump<endinter){
							if(this.linhas[jump] != null) {
								parselinha = linhas[jump].trim();
								if (parselinha.endsWith("["))flagjump++;
								if (parselinha.endsWith("]"))flagjump--;
								if (flagjump==0)break;

							}else{
								Interpretador.error=true;
								System.out.println ("Erro: não foi encontrado um fim de escopo para o if da linha "+(i+1));
								return;
							}
							jump ++;
						}
						if (flagjump!=0){
							Interpretador.error=true;
							System.out.println ("Erro: não foi encontrado um fim de escopo para o if da linha "+(i+1));
							return;
						}
						i=jump;

					}

				}

				else if (linha.startsWith ("show")){
//					System.out.println("Debug: Comando de saida reconhecido na linha "+ (i+1));
					String argumento = new String(linha.substring(4));//String varsline = new String (linha.substring (4));
					argumento=IO.getOutput(argumento);
					if (Interpretador.error){
						System.out.println("Erro: na linha "+(i+1)+", era esperado um comando de saida");
						return;
					}
					System.out.println (argumento);

				}
				else if (linha.startsWith ("inputint ")||linha.startsWith ("inputint	")){
//					System.out.println ("Debug: Entrada de int reconhecida na linha "+(i+1));
					String destino= new String (linha.substring(9));
//					System.out.println ("Debug: o argumento de entrada é :"+destino);
					destino=destino.trim();
					int pdest= varmanager.getVarPos(destino);
					if (pdest==-1){
						Interpretador.error=true;
						System.out.println("Erro: na linha "+(i+1)+" Era esperado uma variavel perto de "+destino);
						return;
					}
					if (!(varmanager.vars[pdest] instanceof Int)){
						Interpretador.error=true;
						System.out.println("Erro: na linha "+(i+1)+" Era esperado uma variavel do tipo int perto de "+destino);
						return;
					}
					int pvalor=IO.getIntInput();
					((Int)varmanager.vars[pdest]).setValor(pvalor);

				}
				else if (linha.startsWith ("inputreal ")||linha.startsWith ("inputreal	")){
//					System.out.println ("Debug: Entrada de real reconhecida na linha "+(i+1));
					String destino= new String (linha.substring(10));
//					System.out.println ("Debug: o argumento de entrada é :"+destino);
					destino=destino.trim();
					int pdest= varmanager.getVarPos(destino);
					if (pdest==-1){
						Interpretador.error=true;
						System.out.println("Erro: na linha "+(i+1)+" Era esperado uma variavel perto de "+destino);
						return;
					}
					if (!(varmanager.vars[pdest] instanceof Real)){
						Interpretador.error=true;
						System.out.println("Erro: na linha "+(i+1)+" Era esperado uma variavel do tipo real perto de "+destino);
						return;
					}
					double pvalor=IO.getRealInput();
					((Real)varmanager.vars[pdest]).setValor(pvalor);

				}


				else if (linha.contains ("=")){
//					System.out.println ("Debug: Atribuição reconhecida na linha "+(i+1));
					String[] atribuicao = linha.split("=");

					if (atribuicao.length != 2){
						Interpretador.error=true;
						System.out.println("Erro: era esperedo uma atribuição na linha "+(i+1)+" mas o comando é desconhecido");
						return;
					}
					String destino = new String (atribuicao[0].trim());
					String origem = new String (atribuicao[1].trim());

					int posdestino = varmanager.getVarPos(destino);
					if (posdestino == -1){
						Interpretador.error=true;
						System.out.println("Erro: na linha "+(i+1)+" não foi encontrado variavel com o nome "+destino);
						return;
					}
					if (varmanager.vars[posdestino] instanceof Int){
						((Int)varmanager.vars[posdestino]).setValor(ula.getIntOpe(origem));
						if (Interpretador.error){
							System.out.println ("Erro: o erro foi na linha "+(i+1)+" era esperado atribuição para int");
							return;
						}

					}else if (varmanager.vars[posdestino] instanceof Real){
						((Real)varmanager.vars[posdestino]).setValor(ula.getRealOpe(origem));
						if (Interpretador.error){
							System.out.println ("Erro: o erro foi na linha "+(i+1)+" era esperado atribuição para real");
							return;
						}
					}
				}
				else if (linha.equals("]")){
//					System.out.println ("Debug: fecha escopo reconhecido na linha "+(i+1));

				}
				else if (linha.isEmpty()){
//					System.out.println ("Debug: a linha "+(i+1)+"ta vazia");
				}
				else {
					Interpretador.error=true;
					System.out.println("Erro: comando não reconhecido na linha "+(i+1));
					return;
				}

//				System.out.println("Linha " + (i + 1) + ":" + linha);

			}
			else {
				//System.out.println ("Debug: a linha(S) "+(i+1)+"eh null");

			}
		}
//		varmanager.printVars(vars);
	}


}
