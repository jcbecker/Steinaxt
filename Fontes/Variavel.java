/**
 *	Nomes: João Carlos Becker e Leonardo Bianchini
 *	Emails: joaoc.becker@hotmail.com e leonardobianchini7@gmail.com
 *
 *	Essa classe tem métodos e atributos que as variaveis especificas 
	tem em comum. Sera a classe mãe das variaveis com um tipo
*/

public abstract class Variavel{//abstrato então não pode ser instanciado apenas seus filhos podem
	private String nome;
	
	public Variavel (String nome){//método construtor
		this.nome=nome;
		
	}
	public String getNome (){
		return this.nome;
	}
	
}
