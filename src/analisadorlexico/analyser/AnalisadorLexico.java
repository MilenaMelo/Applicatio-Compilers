package analisadorlexico.analyser;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import analisadorlexico.components.TokenTable;
import analisadorlexico.components.Token;
import analisadorlexico.reader.FileManager;


public class AnalisadorLexico {

	private List<Token> characterPending = new ArrayList<Token>();
	private ErrorManager errorManager = ErrorManager.getInstance();
	private TokenTable tokenTable = TokenTable.getInstance();
	private List<String> idDeclared = new ArrayList<String>();
	private StringBuilder lexema = new StringBuilder();
	private boolean firstCaracter = true;
	public  static String filePath;
	private FileManager fileReader;
	private char caracter;
 
	public Token nextToken() {
		
		try {
			
			// CHAMA A LEITURA DO ARQUIVO
			if (firstCaracter) {
				firstCaracter = false;
				caracter = fileReader.getNextChar();
			}

			// VERIFICA OS CARACTERES VALIDOS
			while (true) {
				
				lexema.delete(0, lexema.length()); 
				
				// ANALISA CADA CARACTER
				switch (caracter) {
				
				
				// OPERADORES ARIMETICOS
				
				case '+':	
					firstCaracter = true;
					lexema.append(caracter);
					caracter = fileReader.getNextChar();
					if(caracter == '+') {
						lexema.append(caracter);
					}
					caracter = fileReader.getNextChar();
					return tokenTable.returnsArithmeticOperator(String.valueOf(lexema),
					fileReader.getLine(),
					fileReader.getColumn());
				case '-':	
					firstCaracter = true;
					lexema.append(caracter);
					caracter = fileReader.getNextChar();
					if(caracter == '-') {
						lexema.append(caracter);
					}
					caracter = fileReader.getNextChar();
					
					return tokenTable.returnsArithmeticOperator(String.valueOf(lexema),
					fileReader.getLine(),
					fileReader.getColumn());
			
				case '*':
				case '/':
					firstCaracter = true;
					lexema.append(caracter);
					caracter = fileReader.getNextChar();
					if(caracter != '/' || caracter != '*') {
						return tokenTable.returnsArithmeticOperator(String.valueOf(lexema),
						fileReader.getLine(),
						fileReader.getColumn());
					}else {
						// VERIFICA COMENTARIO
						caracter = fileReader.getNextChar();
					}
					
				// OPERADORES RELACIONAIS

				case '>':
				case '<':
					firstCaracter = true;
					lexema.append(caracter);
					caracter = fileReader.getNextChar();
					if(caracter == '=') {
						lexema.append(caracter);
					}
					caracter = fileReader.getNextChar();
					
					return tokenTable.returnsRelationalOperator(String.valueOf(lexema),
					fileReader.getLine(),
					fileReader.getColumn());

				// OPERADORES LOGICOS
					
				case '&':
					firstCaracter = true;
					lexema.append(caracter);
					caracter = fileReader.getNextChar();
					if(caracter == '&') {
						lexema.append(caracter);
					}
					caracter = fileReader.getNextChar();
					
					return tokenTable.returnsLogicalOperator(String.valueOf(lexema),
					fileReader.getLine(),
					fileReader.getColumn());
					
				case '|':
					firstCaracter = true;
					lexema.append(caracter);
					caracter = fileReader.getNextChar();
					if(caracter == '|') {
						lexema.append(caracter);
					}
					caracter = fileReader.getNextChar();
					
					return tokenTable.returnsLogicalOperator(String.valueOf(lexema),
					fileReader.getLine(),
					fileReader.getColumn());
					
				case '!':
					firstCaracter = true;
					lexema.append(caracter);
					caracter = fileReader.getNextChar();
					if(caracter == '=') {
						// OPERADOR RELACIONAL
					}					
					return tokenTable.returnsLogicalOperator(String.valueOf(lexema),
					fileReader.getLine(),
					fileReader.getColumn());
					
				// DELIMITADORES
					
				case ';':
				case '{':
				case '}':
				case '[':
				case ']':
				case '(':
				case ')':
				case '.':
				case ',':
					firstCaracter = true;
					lexema.append(caracter);
					caracter = fileReader.getNextChar();
					return tokenTable.returnsDelimiter(String.valueOf(lexema),
					fileReader.getLine(),
					fileReader.getColumn());

				case '_':
					lexema.append(caracter);

					while (Character.isDigit(caracter)
							|| Character.isLetter(caracter) || caracter == '_') {

						caracter = fileReader.getNextChar();
						lexema.append(caracter);
					}
					return tokenTable.returnsWord(
							String.valueOf(lexema), fileReader.getLine(),
							fileReader.getColumn());
					
				case '\'':
					lexema.append(caracter);
					caracter = fileReader.getNextChar();

					while (caracter != '\'') {
						lexema.append(caracter);
						caracter = fileReader.getNextChar();
					}
					if (caracter == '\'') {
						lexema.append(caracter);
						caracter = fileReader.getNextChar();
					}
					return tokenTable.returnsLiteral(String.valueOf(lexema),
							fileReader.getLine(), fileReader.getColumn());
					
									
				// VERIFICA OS CARACTERES E IGNORA OS ESPACOS EM BRANCO
				default:

					if (Character.isWhitespace(caracter)) {
						caracter = fileReader.getNextChar();
						break;
					}
					
					if (caracter == Character.LINE_SEPARATOR
							|| caracter == '\n') {
						caracter = fileReader.getNextChar();
						break;
					}

					if (caracter == '#') {
						caracter = fileReader.getNextChar();
						continueReadingComment();
						caracter = fileReader.getNextChar();
						continue;
					}

					// ANALISA OS NUMEROS
					if (Character.isDigit(caracter)) {
						int indicePonto = 0, indiceE = 0;
						boolean firstLooping = true;

						try {
							while ((Character.isDigit(caracter)
									|| caracter == 'E' || caracter == '+' || caracter == '.')) {
								if (firstLooping) {
									firstLooping = false;

									lexema.append(caracter);
									caracter = fileReader.getNextChar();

								}

								if (Character.isDigit(caracter)
										&& lexema.charAt(lexema.length() - 1) != 'E') {
									lexema.append(caracter);
									caracter = fileReader.getNextChar();
								}

								else if (caracter == 'E' && indiceE == 0) {
									indiceE++;
									caracter = fileReader.getNextChar();
									if (caracter == '+') {
										lexema.append("E" + caracter);
										caracter = fileReader.getNextChar();
									}
								}

								else if (caracter == '.' && indicePonto == 0) {
									indicePonto++;
									caracter = fileReader.getNextChar();
									if (Character.isDigit(caracter)) {
										lexema.append("." + caracter);
										caracter = fileReader.getNextChar();
									} else {
										fileReader.reset();
										fileReader.reset();
										return (tokenTable.returnsNumber(
												String.valueOf(lexema),
												fileReader.getLine(),
												fileReader.getColumn()));
									}
								} else {
									fileReader.reset();
									return (tokenTable.returnsNumber(
											String.valueOf(lexema),
											fileReader.getLine(),
											fileReader.getColumn()));
								}
							} 
							return (tokenTable.returnsNumber(
									String.valueOf(lexema),
									fileReader.getLine(),
									fileReader.getColumn()));
						} catch (Exception e) {
							caracter = ' ';
							return (tokenTable.returnsNumber(
									String.valueOf(lexema),
									fileReader.getLine(),
									fileReader.getColumn()));
						}
					}

					// ANALISA PALAVRAS RESERVADAS
					if (Character.isLetter(caracter)) {
						lexema.append(caracter);
						while (Character.isDigit(caracter)
								|| Character.isLetter(caracter)
								|| caracter == '_') {
							caracter = fileReader.getNextChar();
							if (Character.isDigit(caracter)
									|| Character.isLetter(caracter)
									|| caracter == '_')
								lexema.append(caracter);
						}
						return tokenTable.returnsWord(
								String.valueOf(lexema),
								fileReader.getLine(),
								fileReader.getColumn());
							
					}

					// ERROS GERAIS
					else {
						errorManager.setError("Linha: "+fileReader.getLine() + ", Coluna: "
								+ fileReader.getColumn() + " | Caracter "
								+ caracter + "nao e valido. ");
						caracter = fileReader.getNextChar();
						continue;
					}

				}
			}
		} catch (EOFException eo) {
			return new Token(53, "SIB", "SIB", fileReader.getLine(),
					fileReader.getColumn());
		}

		catch (IOException io) {
			return new Token(54, "EOF", "EOF", fileReader.getLine(),
					fileReader.getColumn());
		} catch (Exception e) {
			System.out.println("Nao foi possivel recuperar caracter.");
		}
		return null;
	}

	public void continueReadingComment() throws EOFException, IOException {
		// VERIFICA ENQUANTO NAO FOR FIM DE COMENTARIO
		while (true) {

			caracter = fileReader.getNextChar();

			if (caracter == '\n')
				break;
		}
	}

	public boolean checkStatementId(Token token) {
		if (idDeclared.contains(token.getLexema()))
			return true;
		 else {
			 errorManager.setError("Variavel nao declarada no codigo: "
					+ token.getLexema());
			return false;
		}
	}
	
	public void addIdDeclared(Token token) {
		if(idDeclared.contains(token.getLexema()))
			errorManager.setError("Variavel ja declarada anteriormente: "
					+ token.getLexema());
		else
				idDeclared.add(token.getLexema());
	}
	
	public void storeToken(Token token){
		characterPending.add(token);
	}

	public List<String> returnError() {
		return errorManager.getError();
	}

	public int exponentialFound() throws EOFException, IOException {
		int indiceE = 0;

		caracter = fileReader.getNextChar();

		if (caracter == '+') {
			// lexema.append("E");
			lexema.append(caracter);
			indiceE++;
		} else
			caracter = 'E';

		return indiceE;
	}

	
	public AnalisadorLexico(String filePath) {
		try {
			AnalisadorLexico.filePath = filePath;
			fileReader = new FileManager(filePath); 
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo nao encontrado.");
		}
	}

	public AnalisadorLexico() {
		super();
	}
}
