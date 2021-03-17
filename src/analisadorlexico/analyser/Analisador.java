package analisadorlexico.analyser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;

import analisadorlexico.components.TokenTable;
import analisadorlexico.components.Token;


public class Analisador {
	
	ArrayList<String> fileOut = new ArrayList<String>();
	TokenTable symbol = TokenTable.getInstance();
	private AnalisadorLexico analisadorLexico;
	private LineNumberReader lineCounter;
	String nextLine = null;
	int sizeLine = 0;
	
	public void parsing(String path) throws FileNotFoundException {
		lineCounter = new LineNumberReader(new InputStreamReader(new FileInputStream(path)));
		analisadorLexico = new AnalisadorLexico(path);
		
		try {
			while ((nextLine = lineCounter.readLine()) != null) {
				sizeLine++;
			}
		} catch (Exception done) {
			done.printStackTrace();
		}
		
		// GERAR CADA ARQUIVO DE SAÍDA CHAMANDO O ANALISADOR LEXICO
		String output = path.substring(path.lastIndexOf("a") + 1, path.indexOf(".txt"));
		outputFile("output" + output + ".txt");

	} 
	  
	public ArrayList<String> analisadorLexico() throws IOException {
		String stringOut = null;
	 
		// PROCESSO DO ANALISADOR
		buildParsing(sizeLine); 
		
		for (Token t : symbol.getListaToken()) {
			stringOut = "\n" + t.getLinha() + "  " + t.getToken() + "  " + t.getLexema();
			fileOut.add(stringOut);
		}  
		   
		return fileOut;
	}
	
	public void buildParsing(int sizeLine) throws IOException {
		Token token = analisadorLexico.nextToken();
		
		while(sizeLine > 0) {
			if ("PRE".equals(token.getToken())){
				analisadorLexico.storeToken(token);
			}
			if ("IDE".equals(token.getToken())){
				analisadorLexico.storeToken(token);
			}
			if ("NRO".equals(token.getToken())){
				analisadorLexico.storeToken(token);
			}
			if ("DEL".equals(token.getToken())){
				analisadorLexico.storeToken(token);
			}
			if ("REL".equals(token.getToken())){
				analisadorLexico.storeToken(token);
			}
			if ("LOG".equals(token.getToken())){
				analisadorLexico.storeToken(token);
			}
			if ("ART".equals(token.getToken())){
				analisadorLexico.storeToken(token);
			}
			if ("SIB".equals(token.getToken())){
				analisadorLexico.storeToken(token);
			}
			if ("CMF".equals(token.getToken())){
				analisadorLexico.storeToken(token);
			}
			if ("CAD".equals(token.getToken())){
				analisadorLexico.storeToken(token);
			}
			if ("NMF".equals(token.getToken())){
				analisadorLexico.storeToken(token);
			}
			if ("CoMF".equals(token.getToken())){
				analisadorLexico.storeToken(token);
			}
			if ("OpMF".equals(token.getToken())){
				analisadorLexico.storeToken(token);
			}
			token = analisadorLexico.nextToken();
			sizeLine--;
		}
		
	} 
	
	
	public void outputFile(String pathOut) {
		File outputFile = new File("output/" + pathOut);
		try( FileWriter fileWriter = new FileWriter(outputFile) ){
			fileWriter.write("ESTADO DA TABELA DE SIMBOLOS:");
			fileWriter.write("\nLINHA | TOKEN | LEXEMA ");
		 	fileWriter.write(analisadorLexico().toString().replaceAll(",", "").replace("[", "").replace("]", ""));
			fileWriter.flush();
		}catch(IOException ex){
		  ex.printStackTrace();
		}
	}
	
}
