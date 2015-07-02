/**
 *	Nomes: João Carlos Becker e Leonardo Bianchini
 *	Emails: joaoc.becker@hotmail.com e leonardobianchini7@gmail.com
 *
 *	Essa classe contém os metodos de entrada e saída
*/

import java.util.Scanner;


class Entradasaida{

	public Interpretador i;

	public Entradasaida (Interpretador o){

		this.i = o;

	}

	public String getOutput(String arg){
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
				int p = i.varmanager.getVarPos(a);
				if (p==-1){
					Interpretador.error=true;
					System.out.println("Erro: não foi encontrado variavel com o nome "+a);
					return saida;
				}
				if (i.varmanager.vars[p] instanceof Int){
					int value = ((Int)i.varmanager.vars[p]).getValor();
					String b = new String();
					b=b.valueOf(value);
					saida=saida.concat(b);

				}else if (i.varmanager.vars[p] instanceof Real){
					double value = ((Real)i.varmanager.vars[p]).getValor();
					String b = new String();
					b=b.valueOf(value);
					saida=saida.concat(b);

				}else if(i.varmanager.vars[p] instanceof Text){
					String value = new String (((Text)i.varmanager.vars[p]).getConteudo());
					saida=saida.concat(value);

				}
			}
		}
		return saida;
	}

	public int getIntInput(){
		int entrada=0;
		Scanner s= new Scanner (System.in);
		entrada = s.nextInt();

		return entrada;
	}
	public double getRealInput(){
		double entrada=0.0;
		Scanner s= new Scanner (System.in);
		entrada = s.nextDouble();

		return entrada;
	}

}
