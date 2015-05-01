class Varsmanager{
	
	
	
	public void printVars(Variavel[] vars){//um método teste para saber se as variáveis estão se alocando da maneira correta
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
	
	public boolean validname(String name, int line, Variavel[] vars){//verifica se string name é um nome valido para variavel
		
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
		name.equals("mod")){
			System.out.println ("Erro na linha "+line+" variavel "+name+" com palavra reservada");
			return false;
		}
		return true;
	}
	
	public int getNewVarPos (Variavel[] v){//esse método retorna a primeira posição livre, -1 caso esteja sem espaço
		int k=-1;
		for (int i=0;;i++){
			if (v[i]==null){
				 k=i;
				 break;
			 }
		}
		if (k==-1) System.out.println ("O vetor de variaveis esta cheio");
		return k;
	}
	
	public int getVarPos (String pname, Variavel[] v){
		int pos = -1;
		pname = pname.trim();
		for (int i=0;v[i]!=null;i++){
			if (pname.equals(v[i].getNome())){
				pos=i;
				break;
			}
			
		}
		if (pos==-1) System.out.println ("Erro: Váriavel "+ pname+ " não encontrada");
		return pos;
	}
}
