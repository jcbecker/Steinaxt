/**
 *	Nomes: João C. Becker e Leonardo Bianchini
 *	Emails: joaoc.becker@hotmail.com e leonardobianchini7@gmail.com
 *
 *	Essa classe contém o gerenciador de variaveis
*/

class Varsmanager{

	public Variavel vars[];
	public Varsmanager (){
		vars = new Variavel [1000];
	}

	public void printVars(){//um método teste para saber se as variáveis estão se alocando da maneira correta
//		System.out.println ("Tamano do vetor é "+vars.length);
		for (int i = 0;i<vars.length; i++){
			if (vars [i] == null){
				System.out.println ("não existe variavel na posição "+(i));
				break;
			}
			else if (vars [i] instanceof Int){
				System.out.println ("Pos:"+i+"\tTipo:int"+"\tNome:"+(vars[i].getNome())+" Valor:"+ ((Int)vars[i]).getValor());
			}
			else if (vars [i] instanceof Real){
				System.out.println ("Pos:"+i+"\tTipo:real"+"\tNome:"+(vars[i].getNome())+" Valor:"+ ((Real)vars[i]).getValor());
			}
			else if (vars [i] instanceof Text){
				System.out.println ("Pos:"+i+"\tTipo:text"+"\tNome:"+(vars[i].getNome())+" Conteudo:"+ ((Text)vars[i]).getConteudo());
			}
			else{
				System.out.println ("Variavel da posição "+i +" não reconhecida");
			}

		}

	}

	public boolean validname(String name, int line){//verifica se string name é um nome valido para variavel

		for (int i=0; vars[i]!= null; i++){
			if (name.equals(vars[i].getNome())){
				System.out.println ("Erro na linha "+line+" já existe uma variavel com o nome "+name);
				return false;
			}

		}
		if (name.contains (" ")|| name.contains ("!")|| name.contains ("@")
		||  name.contains ("#")|| name.contains ("$")|| name.contains ("%")
		||  name.contains ("&")|| name.contains ("*")|| name.contains ("(")
		||  name.contains (")")|| name.contains ("-")|| name.contains ("+")
		||  name.contains ("=")|| name.contains (";")|| name.contains (",")
		||  name.contains (".")|| name.contains ("£")|| name.contains ("¬")){
			System.out.println ("Erro na linha "+line+" variavel "+name+" obtem caracter especial");
			return false;

		}
		if ( name.charAt(0) >= '0' && name.charAt(0) <= '9'){
			System.out.println ("Erro na linha "+line+" variavel "+name+" começa com numero");
			return false;

		}

		if (name.equals("int") || name.equals("real") || name.equals("text") ||
		name.equals("loop") || name.equals("show") || name.equals("if")||
		name.equals("add") || name.equals("sub") || name.equals("mul") || name.equals("div")||
		name.equals("mod")|| name.equals("inputint")|| name.equals("inputreal")){
			System.out.println ("Erro na linha "+line+" variavel "+name+" com palavra reservada");
			return false;
		}
		return true;
	}

	public int getNewVarPos (){//esse método retorna a primeira posição livre, -1 caso esteja sem espaço
		int k=-1;
		for (int i=0;;i++){
			if (vars[i]==null){
				 k=i;
				 break;
			 }
		}
		if (k==-1) System.out.println ("O vetor de variaveis esta cheio");
		return k;
	}

	public int getVarPos (String pname){//retorna a posição da variavel com o nome pname
		int pos = -1;
		pname = pname.trim();
		for (int i=0;vars[i]!=null;i++){
			if (pname.equals(vars[i].getNome())){
				pos=i;
				break;
			}

		}
		return pos;
	}

/********************************ATRIBUICAO DE VARIAVEL*************************************/
public void declara (int tipo, String linha, int i, Ulamanager ula){
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
						if (validname(varofline[k], i+1)){
							if (tipo==1){
								vars [getNewVarPos()]= new Int (varofline[k],a);
							} else {
								vars [getNewVarPos()]= new Real (varofline[k],a);
							}
							//falta add o error de getNewVarPos(vars) retornar -1
						}else{
							return;
						}
					}
					else{//declara sem atribuição
						varofline[k]=varofline[k].trim();
						if (validname(varofline[k], i+1)){
							if (tipo==1){
								vars [getNewVarPos()]= new Int (varofline[k],0);
							} else {
								vars [getNewVarPos()]= new Real (varofline[k],0);
							}
							//falta add error de getNewVarPos(vars) retornar -1
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
					if (validname(varsline, i+1)){
						if (tipo==1){
							vars [getNewVarPos()]= new Int (varsline,a);
						} else {
							vars [getNewVarPos()]= new Real (varsline,a);
						}
						vars [getNewVarPos()]= new Int (varsline,a);
						//falta add o error de getNewVarPos(vars) retornar -1
					}else{
						return;
					}


				}else{//uma variavel sem atribuição
					varsline=varsline.trim();
					if (validname(varsline, i+1)){
						if (tipo==1){
							vars [getNewVarPos()]= new Int (varsline,0);
						} else {
							vars [getNewVarPos()]= new Real (varsline,0);
						}
						//falta add o error de getNewVarPos(vars) retornar -1
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

}
