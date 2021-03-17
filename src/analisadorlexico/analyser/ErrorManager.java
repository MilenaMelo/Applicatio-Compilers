package analisadorlexico.analyser;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;



public class ErrorManager {
	private static ErrorManager instance = new ErrorManager();
	private List<String> error = new ArrayList<String>();

	public void setError(String erro) {
		error.add(erro);
	}
	public void setError(String erro, long l, long c) {
		error.add("Linha "+l+" Coluna "+c+" "+erro);
	}
	public List <String> getError() {
		return error;
	}

	public void writeError() {
		try {
			int indice = AnalisadorLexico.filePath.lastIndexOf("/");
			String caminhoArquivo = AnalisadorLexico.filePath.substring(0, indice) ;
			File file = new File(caminhoArquivo, "errorLog.txt");
			FileWriter arquivo = new FileWriter(file);
			for (String e : error) {
				arquivo.write(e+"\n");
			}
			arquivo.close();
		} catch (Exception e) {
			System.out.println("Arquivo não encontrado.");
		}
	}

	public static ErrorManager getInstance() {
		return instance;
	}
}
