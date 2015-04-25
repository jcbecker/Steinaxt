class Ulamanager{
	
	
	
	public void printVars(Variavel[] vars){
		
//		System.out.println ("Tamano do vetor é "+vars.length);
		for (int i = 0;i<vars.length; i++){
			if (vars [i] == null){
				System.out.println ("não existe variavel na posição "+(i));
				break;
			}
			else if (vars [i] instanceof Int){
				System.out.println ("Pos:"+i+" Tipo:int "+"Nome:"+(vars[i].getNome())+" Valor:"+ ((Int)vars[i]).getValor());
				
			}
			
			else if (vars [i] instanceof Real){
				System.out.println ("Pos:"+i+" Tipo:real "+"Nome:"+(vars[i].getNome())+" Valor:"+ ((Real)vars[i]).getValor());
				
			}
			
			else if (vars [i] instanceof Text){
				System.out.println ("Pos:"+i+" Tipo:text "+"Nome:"+(vars[i].getNome())+" Conteudo:"+ ((Text)vars[i]).getConteudo());
				
				
			}
			
			else{
				System.out.println ("Variavel da posição "+i +" não reconhecida");
				
			}
			
		}
		
	}
	
	
	
}
