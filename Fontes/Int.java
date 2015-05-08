/**
 *	Nomes: João Carlos Becker e Leonardo Bianchini
 *	Emails: joaoc.becker@hotmail.com e leonardobianchini7@gmail.com
 *
 *	Essa classe tem métodos e atributos especificos do tipo Int
*/

public class Int extends Variavel{
	private int valor;
	
	public Int (String nome,int i){
		super(nome);
		this.valor=i;
	}
	
	public int getValor (){
		return this.valor;
	}
	
	public void setValor (int i){
		this.valor=i;
	}
	
}
