/**
 *	Nomes: João C. Becker e Leonardo Bianchini
 *	Emails: joaoc.becker@hotmail.com e leonardobianchini7@gmail.com
 *
 *	Essa classe contém o gerenciador de operacoes
*/

class Ulamanager{

	public Interpretador i;

	public Ulamanager(Interpretador o){

		this.i = o;

	}

	public int getIntValue (String decod){//Pega o valor inteiro e retorna ele seja variavel ou constante
		decod = decod.trim();
		int a=0;
		if ( (decod.charAt(0) >= '0' && decod.charAt(0) <= '9') || decod.charAt(0) == '-'){//esperado constante
			try{
				a= Integer.parseInt(decod);
			}catch(NumberFormatException e){
//				System.out.println ("Erro: era esperado uma constante do tipo inteiro perto de "+decod);
				Interpretador.error=true;
			}
		}else{//esperado variavel
			int pos = i.varmanager.getVarPos(decod);
			if (pos==-1){//ERRO
//				System.out.println ("Erro: era esperado uma variavel do tipo int com o nome "+decod);
				Interpretador.error=true;
			}else{
				if (i.varmanager.vars [pos] instanceof Int){
					a = ((Int)i.varmanager.vars[pos]).getValor();
				}else{
//					System.out.println ("Erro: era esperado uma variavel do tipo int com o nome "+decod);
					Interpretador.error=true;
				}
			}

		}
		return a;
	}

	public double getRealValue (String decod){//Pega o valor real e retorna ele seja variavel ou constante
		decod = decod.trim();
		double a=0.0;
		if ( (decod.charAt(0) >= '0' && decod.charAt(0) <= '9') || decod.charAt(0) == '-'){//esperado constante
			try{
				a= Double.parseDouble(decod);//Double.parseDouble
			}catch(NumberFormatException e){
//				System.out.println ("Erro: era esperado uma constante do tipo real perto de "+decod);
				Interpretador.error=true;
			}
		}else{//esperado variavel
			int pos = i.varmanager.getVarPos(decod);
			if (pos==-1){//ERRO
//				System.out.println ("Erro: era esperado uma variavel do tipo real com o nome "+decod);
				Interpretador.error=true;
			}else{
				if (i.varmanager.vars [pos] instanceof Real){
					a = ((Real)i.varmanager.vars[pos]).getValor();
				}else{
//					System.out.println ("Erro: era esperado uma variavel do tipo real com o nome "+decod);
					Interpretador.error=true;
				}
			}

		}
		return a;
	}

	public int getIntOpe (String operacao){
		operacao=operacao.trim();
		if (operacao.startsWith("add ")||operacao.startsWith("sub ")
		||  operacao.startsWith("mul ")||operacao.startsWith("div ")
		||  operacao.startsWith("mod ")){
			String operandos = new String (operacao.substring (4));
			operandos=operandos.trim();
//			System.out.println ("Debug: "+operandos);
			if (!(operandos.contains ("&"))){
				System.out.println ("Erro: era esperado dois argumentos separados por & perto de "+operandos);
				Interpretador.error=true;
				return 1;
			}
			String[] atr = operandos.split("&");
			if (atr.length != 2){
				System.out.println ("Erro: era esperado dois argumentos separados por 1 & perto de "+operandos);
				Interpretador.error=true;
				return 1;
			}
			int operador1, operador2;
			operador1 = getIntValue(atr[0]);
			operador2 = getIntValue(atr[1]);
			if (Interpretador.error) return 1;

			if (operacao.startsWith("add ")) return operador1 + operador2;
			else if (operacao.startsWith("sub ")) return operador1 - operador2;
			else if (operacao.startsWith("mul ")) return operador1 * operador2;
			else if (operacao.startsWith("div ")) return operador1 / operador2;
			else if (operacao.startsWith("mod ")) return operador1 % operador2;

		}else{
			int argumento= getIntValue(operacao);
			if (Interpretador.error) System.out.println ("Erro: era esperado uma atribuição de argumento unico do tipo int perto de "+operacao);
			return argumento;
		}
		return 1;
	}

	public double getRealOpe (String operacao){
		operacao=operacao.trim();
		if (operacao.startsWith("add ")||operacao.startsWith("sub ")
		||  operacao.startsWith("mul ")||operacao.startsWith("div ")
		||  operacao.startsWith("mod ")){
			String operandos = new String (operacao.substring (4));
			operandos=operandos.trim();
//			System.out.println ("Debug: "+operandos);
			if (!(operandos.contains ("&"))){
				System.out.println ("Erro: era esperado dois argumentos separados por & perto de "+operandos);
				Interpretador.error=true;
				return 1.0;
			}
			String[] atr = operandos.split("&");
			if (atr.length != 2){
				System.out.println ("Erro: era esperado dois argumentos separados por 1 & perto de "+operandos);
				Interpretador.error=true;
				return 1.0;
			}
			double operador1, operador2;
			operador1 = getRealValue(atr[0]);
			operador2 = getRealValue(atr[1]);
			if (Interpretador.error) return 1.0;

			if (operacao.startsWith("add ")) return operador1 + operador2;
			else if (operacao.startsWith("sub ")) return operador1 - operador2;
			else if (operacao.startsWith("mul ")) return operador1 * operador2;
			else if (operacao.startsWith("div ")) return operador1 / operador2;
			else if (operacao.startsWith("mod ")) return operador1 % operador2;

		}else{
			double argumento= getRealValue(operacao);
			if (Interpretador.error) System.out.println ("Erro: era esperado uma atribuição de argumento unico do tipo real perto de "+operacao);
			return argumento;
		}
		return 1.0;
	}

	public boolean getCondicao(String condicao){
		condicao = condicao.trim();
		String token;
		if (condicao.contains("==")){
			token =  new String ("==");
		}else if (condicao.contains("!=")){
			token =  new String ("!=");
		}else if (condicao.contains(">=")){
			token =  new String (">=");
		}else if (condicao.contains("<=")){
			token =  new String ("<=");
		}else if (condicao.contains(">")){
			token =  new String (">");
		}else if (condicao.contains("<")){
			token =  new String ("<");
		}else{
			Interpretador.error=true;
			System.out.println("Erro: era esperado uma condição perto e "+condicao);
			System.out.println("Erro: não foi encontrado token de condição ==, !=, >=, <=, > e <");
			return false;
		}
		String[] argumentos = condicao.split(token);
		if (argumentos.length != 2){
			Interpretador.error=true;
			System.out.println("Erro: era esperado uma condicao com dois argumentos um antes e outro depois de "+token);
			return false;
		}
		argumentos[0]=argumentos[0].trim();
		argumentos[1]=argumentos[1].trim();

		int i1=0, i2=0;
		double d1=0.0, d2=0.0;
		boolean b1=true, b2=true;

		i1=getIntValue(argumentos[0]);
		if (Interpretador.error){
			Interpretador.error=false;
			b1=false;
			d1=getRealValue(argumentos[0]);
		}
		if (Interpretador.error){
			System.out.println("Erro: não foi possivel encontrar um valor para "+argumentos[0]);
			return false;
		}

		i2=getIntValue(argumentos[1]);
		if (Interpretador.error){
			Interpretador.error=false;
			b2=false;
			d2=getRealValue(argumentos[1]);
		}
		if (Interpretador.error){
			System.out.println("Erro: não foi possivel encontrar um valor para "+argumentos[1]);
			return false;
		}
		if (token.equals("==")){//---------------------------------------==
			if (b1){
				if (b2){
					if (i1==i2) return true;
					else return false;
				}else{
					if (i1==d2) return true;
					else return false;
				}
			}else{
				if (b2){
					if (d1==i2) return true;
					else return false;
				}else{
					if (d1==d2) return true;
					else return false;
				}
			}
		}else if (token.equals("!=")){//---------------------------------------!=
			if (b1){
				if (b2){
					if (i1!=i2) return true;
					else return false;
				}else{
					if (i1!=d2) return true;
					else return false;
				}
			}else{
				if (b2){
					if (d1!=i2) return true;
					else return false;
				}else{
					if (d1!=d2) return true;
					else return false;
				}
			}

		}else if (token.equals(">=")){//--------------------------------------->=
			if (b1){
				if (b2){
					if (i1>=i2) return true;
					else return false;
				}else{
					if (i1>=d2) return true;
					else return false;
				}
			}else{
				if (b2){
					if (d1>=i2) return true;
					else return false;
				}else{
					if (d1>=d2) return true;
					else return false;
				}
			}

		}else if (token.equals("<=")){//---------------------------------------<=
			if (b1){
				if (b2){
					if (i1<=i2) return true;
					else return false;
				}else{
					if (i1<=d2) return true;
					else return false;
				}
			}else{
				if (b2){
					if (d1<=i2) return true;
					else return false;
				}else{
					if (d1<=d2) return true;
					else return false;
				}
			}

		}else if (token.equals(">")){//---------------------------------------->
			if (b1){
				if (b2){
					if (i1>i2) return true;
					else return false;
				}else{
					if (i1>d2) return true;
					else return false;
				}
			}else{
				if (b2){
					if (d1>i2) return true;
					else return false;
				}else{
					if (d1>d2) return true;
					else return false;
				}
			}

		}else if (token.equals("<")){//----------------------------------------<
			if (b1){
				if (b2){
					if (i1<i2) return true;
					else return false;
				}else{
					if (i1<d2) return true;
					else return false;
				}
			}else{
				if (b2){
					if (d1<i2) return true;
					else return false;
				}else{
					if (d1<d2) return true;
					else return false;
				}
			}

		}


		return false;
	}

}
