package analisadorlexico.components;


import java.util.ArrayList;


public class TokenTable {
	
	ArrayList<Token> tokenTable = new ArrayList<Token>();
	private static TokenTable instance = new TokenTable();

	
	public ArrayList <Token> getListaToken(){
		return tokenTable;
	}
	
	public static TokenTable getInstance() {
		return instance;
	}
	public ArrayList <Token> getTabela(){
		return tokenTable;
	}

	public Token returnsRelationalOperator(String lex, long l, long c){
		Token token;
		
		if (lex.equals("<")) {
			token = new Token(6, "REL", "<", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals(">")) {
			token = new Token(6, "REL", ">", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else if(lex.equals("<=")) {
			token = new Token(6, "REL", "<=", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else if(lex.equals(">=")) {
			token = new Token(6, "REL", ">=", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else if(lex.equals("==")) {
			token = new Token(6, "REL", "==", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else if(lex.equals("!=")) {
			token = new Token(6, "REL", "!=", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else
			return null;
		
	}
	
	
	public Token returnsArithmeticOperator(String lex, long l, long c){
		Token token;
		
		if (lex.equals("*")){
			token = new Token(10, "ART", "*", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals("/")) {
			token = new Token(10, "ART", "/", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals("+")) {
			token = new Token(10, "ART", "+", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals("++")) {
			token = new Token(10, "ART", "++", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals("-")) {
			token = new Token(10, "ART", "-", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals("--")) {
			token = new Token(10, "ART", "--", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else
			return null;
	}
	
	public Token returnsLogicalOperator(String lex, long l, long c){
		Token token;
		
		if (lex.equals("&&"	)){
			token = new Token(12, "LOG", "&&", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals("||")) {
			token = new Token(12, "LOG", "||", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals("!")) {
			token = new Token(12, "LOG", "!", l, c);
			tokenTable.add(token);
		 	return token;
		}
		else
			return null;
	}
	
	public Token returnsDelimiter(String lex, long l, long c){
		Token token;
		
		if (lex.equals("(")) {
			token = new Token(13, "DEL", "(", l, c);
		 	tokenTable.add(token);
		 	return token;
		}
		else if(lex.equals(")")) {
			token = new Token(13, "DEL", ")", l, c);
		 	tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals(";")) {
			token = new Token(13, "DEL", ";", l, c);
		 	tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals("{")) {
			token = new Token(13, "DEL", "{", l, c) ;
		 	tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals("}")) {
			token = new Token(13, "DEL", "}", l, c) ;
		 	tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals("[")) {
			token = new Token(13, "DEL", "[", l, c) ;
		 	tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals("]")) {
			token = new Token(13, "DEL", "]", l, c) ;
		 	tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals(",")) {
			token = new Token(13, "DEL", ",", l, c) ;
		 	tokenTable.add(token);
		 	return token;
		}
		else if (lex.equals(".")) {
			token = new Token(13, "DEL", ".", l, c) ;
		 	tokenTable.add(token);
		 	return token;
		}
		else
			return null;
	}
	
	public Token returnsLiteral(String lex, long l, long c){
		return new Token(18, "IDE", lex, l, c);
		
	}
	
	 	 
	public Token returnsNumber(String lex, long l, long c){
		if(lex.contains("."))
			
			return (new Token(19, "NRO", lex, l, c));
			
		
		else if (!lex.contains(".")) {
			tokenTable.add(new Token(20, "NRO", lex, l, c));
			return (new Token(21, "NRO", lex, l, c));
		}
		else
			return null;
	}


	public Token returnsWord(String lex, long l, long c){
		Token token;
	
		if (lex.equals("if")) {
			token = new Token(23, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("else")) {
			token = new Token(24, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("for")) {
			token = new Token(25, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("while")) {
			token = new Token(26, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("case")) {
			token = new Token(27, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("int")) {
			token = new Token(28, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("float")) {
			token = new Token(29, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("string")) {
			token = new Token(30, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("boolean")) {
			token = new Token(31, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("true")) {
			token = new Token(32, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("false")) {
			token = new Token(33, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("real")) {
			token = new Token(34, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("return")) {
			token = new Token(35, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("print")) {
			token = new Token(36, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("boolean")) {
			token = new Token(37, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("var")) {
			token = new Token(38, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("const")) {
			token = new Token(39, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("void")) {
			token = new Token(40, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("function")) {
			token = new Token(41, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("extends")) {
			token = new Token(42, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("public")) {
			token = new Token(43, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("private")) {
			token = new Token(44, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("class")) {
			token = new Token(45, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("import")) {
			token = new Token(46, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("trows")) {
			token = new Token(47, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("new")) {
			token = new Token(48, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("packge")) {
			token = new Token(49, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("write")) {
			token = new Token(50, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		else if (lex.equals("read")) {
			token = new Token(51, "PRE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
		
		else{
			token = new Token(52, "IDE", lex.trim(), l, c);
			tokenTable.add(token);
			return token;
		}
	}
}