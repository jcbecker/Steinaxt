class Ulamanager{
	
	public int getIntValue (String decod,Variavel [] vars){//Pega o valor inteiro e retorna ele seja variavel ou constante
		decod = decod.trim();
		int a=0;
		if ( (decod.charAt(0) >= '0' && decod.charAt(0) <= '9') || decod.charAt(0) == '-'){//esperado constante
			try{
				a= Integer.parseInt(decod);
			}catch(NumberFormatException e){
				System.out.println ("Erro: era esperado uma constante do tipo inteiro perto de "+decod);
				Interpretador.error=true;
			}
		}else{//esperado variavel
			Varsmanager V = new Varsmanager();
			int pos =V.getVarPos(decod, vars);
			if (pos==-1){//ERRO
				System.out.println ("Erro: era esperado uma variavel do tipo int com o nome "+decod);
				Interpretador.error=true;
			}else{
				if (vars [pos] instanceof Int){
					a = ((Int)vars[pos]).getValor();
				}else{
					System.out.println ("Erro: era esperado uma variavel do tipo int com o nome "+decod);
					Interpretador.error=true;
				}
			}
			
		}
		return a;
	}
	
	public double getRealValue (String decod,Variavel [] vars){//Pega o valor real e retorna ele seja variavel ou constante
		decod = decod.trim();
		double a=0.0;
		if ( (decod.charAt(0) >= '0' && decod.charAt(0) <= '9') || decod.charAt(0) == '-'){//esperado constante
			try{
				a= Double.parseDouble(decod);//Double.parseDouble
			}catch(NumberFormatException e){
				System.out.println ("Erro: era esperado uma constante do tipo real perto de "+decod);
				Interpretador.error=true;
			}
		}else{//esperado variavel
			Varsmanager V = new Varsmanager();
			int pos =V.getVarPos(decod, vars);
			if (pos==-1){//ERRO
				System.out.println ("Erro: era esperado uma variavel do tipo real com o nome "+decod);
				Interpretador.error=true;
			}else{
				if (vars [pos] instanceof Real){
					a = ((Real)vars[pos]).getValor();
				}else{
					System.out.println ("Erro: era esperado uma variavel do tipo real com o nome "+decod);
					Interpretador.error=true;
				}
			}
			
		}
		return a;
	}
	
	public int getIntOpe (String operacao, Variavel [] vars){
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
			operador1 = getIntValue(atr[0],vars);
			operador2 = getIntValue(atr[1],vars);
			if (Interpretador.error) return 1;
			
			if (operacao.startsWith("add ")) return operador1 + operador2;
			else if (operacao.startsWith("sub ")) return operador1 - operador2;
			else if (operacao.startsWith("mul ")) return operador1 * operador2;
			else if (operacao.startsWith("div ")) return operador1 / operador2;
			else if (operacao.startsWith("mod ")) return operador1 % operador2;
			
		}else{
			int argumento= getIntValue(operacao, vars);
			if (Interpretador.error) System.out.println ("Erro: era esperado uma atribuição de argumento unico do tipo int perto de "+operacao);
			return argumento;
		}
		return 1;
	}
	
	
	
	public double getRealOpe (String operacao, Variavel [] vars){
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
			operador1 = getRealValue(atr[0],vars);
			operador2 = getRealValue(atr[1],vars);
			if (Interpretador.error) return 1.0;
			
			if (operacao.startsWith("add ")) return operador1 + operador2;
			else if (operacao.startsWith("sub ")) return operador1 - operador2;
			else if (operacao.startsWith("mul ")) return operador1 * operador2;
			else if (operacao.startsWith("div ")) return operador1 / operador2;
			else if (operacao.startsWith("mod ")) return operador1 % operador2;
			
		}else{
			double argumento= getRealValue(operacao, vars);
			if (Interpretador.error) System.out.println ("Erro: era esperado uma atribuição de argumento unico do tipo real perto de "+operacao);
			return argumento;
		}
		return 1.0;
	}
	
}
