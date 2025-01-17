package analisadorlexico.reader;

import java.io.*;

public class FileManager extends BufferedReader {
	
	private long line;
	private long column;
	private long lastLineSize;
	
	/**
	* Construtor que recebe nome do arquivo.
	 * @param fileName nome do arquivo que deve ser aberto e mantido pela classe
	 * @throws FileNotFoundException o arquivo nao foi encontrado
	 */
	
	public FileManager(String fileName) 
	throws FileNotFoundException {
		this(new File(fileName));
	}
	
	/**
	* Construtor que recebe um arquivo.
	 * @param file arquivo que deve ser aberto e mantido pela classe
	 * @throws FileNotFoundException o arquivo nao foi encontrado
	 */
	
	public FileManager(File file) 
	throws FileNotFoundException {
		super((new FileReader(file)));
		line = 1;
		column = 0;
		lastLineSize = 0;
		
	}
	
	/**
	* Metodo que retorna um caractere da entrada. 
	 * Marca sua posicao no buffer e testa o final do arquivo.
	 * @return proximo caractere do buffer de entrada
	 * @throws IOException caso um erro de leitura ocorra
	 * @throws EOFException excessao retornada quando o final do arquivo � atingido.
 	 */
	
	public char getNextChar() 
	throws EOFException, IOException {
		this.mark(1);
		int charValue = this.read();
		column++;
		if (charValue == Character.LINE_SEPARATOR) {
			line++;
			lastLineSize = column;
			column = 0;
		} 
		
		if (charValue == -1) throw new EOFException(); 
		return (char) charValue; 
	}
	
    /**
    * Metodo que retorna o ultimo caracter lido.
     * @throws IOException
     */
	
	public void resetLastChar() throws IOException {
        this.reset();
        column--;
        if (column < 0) {
        	column = lastLineSize;
        	line--;
        }
    }
 
	/**
	 * @return the line
	 */
	public long getLine() {
		return line;
	}

	/**
	 * @return the column
	 */
	public long getColumn() {
		return column;
	}
}
