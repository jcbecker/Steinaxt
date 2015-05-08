/**
 *	Nomes: João C. Becker e Leonardo Bianchini
 *	Emails: joaoc.becker@hotmail.com e leonardobianchini7@gmail.com
 *
 *	Essa classe tem métodos e atributos especificos do tipo Text
*/

public class Text extends Variavel{
	private String conteudo;
	
	public Text(String nome, String conteudo){
		super(nome);
		this.conteudo=conteudo;
	}
	
	public String getConteudo(){
		return this.conteudo;
	}
	
	public void setConteudo(String conteudo){
		this.conteudo=conteudo;
	}
}
