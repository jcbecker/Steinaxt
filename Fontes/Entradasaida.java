class Entradasaida{
	
	
	public String getOutput(String arg, Variavel [] vars){
		Varsmanager varmanager=new Varsmanager();
		String saida = new String ();
		String[] args;
		if (arg.contains(",")){
			args = (arg.split(","));
		}else{
			args = new String[1];
			args[0]=new String (arg);
		}
		
		for(int n=0; n < args.length;n++){
			String a =args[n].trim();
			if (a.startsWith("*")){//então concatena texto
				int j=args[n].indexOf("*");
				j++;
				String b= args[n].substring(j);
				saida=saida.concat(b);
				
			}else{//então a = nome da variavel
				int p=varmanager.getVarPos(a, vars);
				if (p==-1){
					Interpretador.error=true;
					System.out.println("Erro: não foi encontrado variavel com o nome "+a);
					return saida;
				}
				if (vars[p] instanceof Int){
					int value = ((Int)vars[p]).getValor();
					String b = new String();
					b=b.valueOf(value);
					saida=saida.concat(b);
					
				}else if (vars[p] instanceof Real){
					double value = ((Real)vars[p]).getValor();
					String b = new String();
					b=b.valueOf(value);
					saida=saida.concat(b);
					
				}else if(vars[p] instanceof Text){
					String value = new String (((Text)vars[p]).getConteudo());
					saida=saida.concat(value);
					
				}
			}
		}
		return saida;
	}
	
	public int getIntInput(){
		int entrada=0;
		
		return entrada;
	}
	public double getRealInput(){
		double entrada=0.0;
		
		return entrada;
	}
	
}
