package analisadorlexico.components;


public class CodeStructure {
	private int line;
	private int column;
	private String token;
	private String lexema;

	public CodeStructure(int line, int column, String token, String lexema) {
		this.line = line;
		this.column = column;
		this.token = token;
		this.lexema = lexema;
	}
 
	public int getLinha() {
		return this.line;
	}

	public int getColuna() {
		return this.column;
	}

	public String getToken() {
		return this.token;
	}

	public String getLexema() {
		return this.lexema;
	}
}
