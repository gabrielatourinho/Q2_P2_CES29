package ita.Q2_P2_CES29;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VulnerableClass {
	
	static final int TAM_MAX_LINHA = 100;
	
	public void vulnerableMethod(String FILENAME) throws IOException{
		
		//Verifica se FILENAME é seguro
		Pattern pattern = Pattern.compile("[^A-Za-z0-9._]");
		Matcher matcher = pattern.matcher(FILENAME);
		if (matcher.find()) {
			throw new IOException();
		}
		
		int i = 0;
		while (i == 0) {
			i++;
			Scanner console = new Scanner(System.in);
		    System.out.print("Digite a operacao desejada para realizar no arquivo <R para ler um arquivo, "
		    		+ "W para escrever em um arquivo>? ");
			
		    String opr = console.nextLine();
			
		    if (opr.equals("R")){ //verifica se digitou R
				BufferedReader br = null;
				FileReader fr = null;

				fr = new FileReader(FILENAME);
				br = new BufferedReader(fr);

				String sCurrentLine;
				char[] buffer = new char[TAM_MAX_LINHA];

				br = new BufferedReader(new FileReader(FILENAME));

				while (br.read(buffer, 0, TAM_MAX_LINHA) != -1) { //só lê TAM_MAX_LINHA caracteres pra não estourar o readLine do BufferedReader
					sCurrentLine = String.valueOf(buffer);
					System.out.println(sCurrentLine);
				}
			}
			
			else if (opr.equals("W")) { //verifica se digitou W
				  BufferedWriter buffWrite;
				  
				buffWrite = new BufferedWriter(new FileWriter(FILENAME));
				String linha = "";
				System.out.println("Escreva algo: ");
				linha = console.nextLine();
				   
				if(linha.length() > TAM_MAX_LINHA) { //só permite que escreva TAM_MAX_LINHA caracteres pra não estourar o append do BufferedWriter
				   	char[] buffer = linha.toCharArray();
				   	linha = String.copyValueOf(buffer, 0, TAM_MAX_LINHA);
				}
				    
				buffWrite.append(linha + "\n");
				      
			}
			else { //se não digitou R nem W lança exceção
				throw new IllegalArgumentException();
			}
		}
	}
}
