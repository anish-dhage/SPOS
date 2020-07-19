package pass2Assembler;

import java.io.*;
import java.util.*;

public class Pass2 {
	HashMap<String,String> symbolTable = new HashMap<String, String>();
	HashMap<String,String> literalTable = new HashMap<String, String>();
	BufferedReader fileReader2, fileReader1, fileReader;

	void readTables(){
		try {
			String line;
			
			fileReader1 = new BufferedReader(new FileReader("/home/anish/College/SPOS/Group A/Assignment 1 & 2/Pass1Assembler/src/pass2Assembler/ST.txt"));
			fileReader2 = new BufferedReader(new FileReader("/home/anish/College/SPOS/Group A/Assignment 1 & 2/Pass1Assembler/src/pass2Assembler/LT.txt"));
	
			line = fileReader1.readLine();
			StringTokenizer tokens;
			String key = null,value = null;
			while(line != null){
				tokens = new StringTokenizer(line, " ", false);
				key = tokens.nextToken();
				value = tokens.nextToken();
				symbolTable.put(key, value);
				line = fileReader1.readLine();
			}	
			line = fileReader2.readLine();
			StringTokenizer tokens2;
			while(line != null){
				tokens2 = new StringTokenizer(line, " ", false);
				key = tokens2.nextToken();
				value = tokens2.nextToken();
				literalTable.put(key, value);
				line = fileReader2.readLine();
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(symbolTable);
		System.out.println(literalTable);

	}
	
	void pass2(){
		String line;
		String mcLine = "";
		try {
			fileReader = new BufferedReader(new FileReader("/home/anish/College/SPOS/Group A/Assignment 1 & 2/Pass1Assembler/src/pass2Assembler/IC.txt"));
			line = fileReader.readLine();
			StringTokenizer tokens;
			String l1,l2,l3,l4;
			while(line != null){
				mcLine = "";
				tokens = new StringTokenizer(line, " ", false);
				l1 = tokens.nextToken();
				if(!l1.equals(":")){
					l2 = tokens.nextToken();
					l2 = tokens.nextToken();
					if(l2.equals("is,00")){
						mcLine = l1+" 00 00 000";
						line = fileReader.readLine();
						System.out.println(mcLine);
						continue;
					}
					
					l3 = tokens.nextToken();
					l4 = tokens.nextToken();

					if(l2.equals("dl,01")){
						mcLine = l1+" 00 00 00"+l4.split(",")[1];
						line = fileReader.readLine();
						System.out.println(mcLine);
						continue;
					}
					mcLine = mcLine + l1;
					mcLine = mcLine +" "+ l2.split(",")[1] +" " + l3.split(",")[1]+" ";
					if(l4.split(",")[0].equals("s")){
						mcLine = mcLine + symbolTable.get(l4);
					}
					else{
						mcLine = mcLine + literalTable.get(l4);
					}
					System.out.println(mcLine);
				}
				line = fileReader.readLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		
		Pass2 obj = new Pass2();
		obj.readTables();
		obj.pass2();
	}

}
