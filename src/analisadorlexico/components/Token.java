package analisadorlexico.components;

public class Token {
	
	private int id;
	private long line;
	private long column;
	private String token;
	private String lexema;

	public Token(int id, String token, String lexema) {
		this.id = id;
		this.token = token;
		this.lexema = lexema;
	} 
	public Token(String token, String lexema) {
		
		this.token = token;
		this.lexema = lexema;
	}

	public Token(int id, String token, String lexema, long line, long column) {
		this.id = id;
		this.token = token;
		this.lexema = lexema;
		this.line = line;
		this.column = column;
	} 

	public int getId() {
		return id;
	}

	public String getToken() {
		return token;
	}

	public String getLexema() {
		return lexema;
	}
	
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public long getLinha() {
		return line;
	}

	public long getColuna() {
		return column;
	}

	public void setLinha(long line) {
		this.line = line;
	}

	public void setColuna(long column) {
		this.column = column;
	}

	public Token() {
		super();
	}
}
