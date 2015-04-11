/*
Nomes: Leonardo Bianchini e João C. Becker 
Emails: joaoc.becker@hotmail.com e leonardobianchini7@gmail.com

	Essa classe tem métodos e atributos especificos do tipo Real
*/

public class Real extends Variavel{
	private double valor;
	
	public Real (String nome,double i){
		super(nome);//passa a string nome para o construtor do pai
		this.valor=i;
	}
	
	public double getValor(){
		return this.valor;
	} 
	
	public void setValor(double i){
		this.valor=i;
		
	}
	
}
