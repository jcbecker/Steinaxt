class Ulamanager{
	
	public int getIntValue (String decod,Variavel [] vars){//Pega o valor inteiro e retorna ele seja variavel ou constante
		decod = decod.trim();
		int a=0;
		if ( (decod.charAt(0) >= '0' && decod.charAt(0) <= '9') || decod.charAt(0) == '-'){//esperado constante
			try{
				a= Integer.parseInt(decod);
			}catch(NumberFormatException e){
				System.out.println ("Erro era esperado uma constante do tipo inteiro perto de "+decod);
				Interpretador.error=true;
			}
			
		}else{//esperado variavel
			Varsmanager V = new Varsmanager();
			int pos =V.getVarPos(decod, vars);
			if (pos==-1){//ERRO
				System.out.println ("Erro era esperado uma variavel do tipo int com o nome "+decod);
				Interpretador.error=true;
			}else{
				if (vars [pos] instanceof Int){
					a = ((Int)vars[pos]).getValor();
				}else{
					System.out.println ("Erro era esperado uma variavel do tipo int com o nome "+decod);
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
			
			
		}else{//esperando variavel
			
			
			
		}
		
		return a;
	}
	
	
	
	
}
