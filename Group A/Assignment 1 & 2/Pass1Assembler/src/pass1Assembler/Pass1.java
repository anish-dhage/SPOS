package pass1Assembler;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Symbol{
	int position;
	String name;
	int lineNo;
}

public class Pass1 {
	
	BufferedReader optReader,fileReader;
	HashMap<String,String> optMap = new HashMap<String, String>();
	HashMap<String,String> symbolTable = new HashMap<String, String>();
	ArrayList<String> literalTable = new ArrayList<String>();
	Integer lineCounter,symbolcounter,literalcounter;
	ArrayList<String> poolTable = new ArrayList<String>();
	
	void readOptTable(){
		try {
			String line;			
			optReader = new BufferedReader(new FileReader("/home/TE/31105/SPOS/Pass1Assembler/src/pass1Assembler/OPTTAB.txt"));
			line = optReader.readLine();
			StringTokenizer tokens;
			String key = null,value = null;
			while(line != null){
				tokens = new StringTokenizer(line, " ", false);
				key = tokens.nextToken();
				value = tokens.nextToken();
				optMap.put(key, value);
				line = optReader.readLine();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void pass1(){
		try {
			fileReader = new BufferedReader(new FileReader("/home/TE/31105/SPOS/Pass1Assembler/src/pass1Assembler/AssemblyCode.txt"));
			String line = fileReader.readLine().toLowerCase();
			StringTokenizer tokens;
			String label = null,opcode = null,operands = null;
			lineCounter = 0;
			symbolcounter = 1;
			literalcounter = 1;
			String icLine;
			int ctr = 0;
			int polctr = 1;
			while(line != null){
				icLine = "";
				tokens = new StringTokenizer(line, " ", false);
				label = tokens.nextToken();
				
				//check if token is a label
				if(label.substring(label.length()-1).equals(":")){
					opcode = tokens.nextToken();
					
					if(!symbolTable.containsKey(label)){
						symbolTable.put(label, "s"+","+Integer.toString(symbolcounter)+" "+Integer.toString(lineCounter));
						symbolcounter++;
					}
					else{
						String sym = symbolTable.get(label);
						sym = sym +" "+Integer.toString(lineCounter);
						symbolTable.put(label, sym);
					}
				}
				
				//else if not a label
				else{
					opcode = label;
					label = null;				
				}
				
				icLine = icLine + optMap.get(opcode);
				
				//check if opcode is a assembler directive or define statement
				if(optMap.get(opcode).split(",")[0].equals("ad") || opcode.equals("stop")||optMap.get(opcode).split(",")[0].equals("dl")){
					if(opcode.equals("start")){
						operands = tokens.nextToken();
						lineCounter = Integer.parseInt(operands) ;
						System.out.println("   "+": "+icLine);
						line = fileReader.readLine();
						if(line!= null){
							line = line.toLowerCase();
						}
						continue;
					}
					if(opcode.equals("ltorg")||opcode.equals("end")){
						ArrayList<String> literalTable2 = new ArrayList<String>(literalTable);
						poolTable.add(Integer.toString(polctr)+" "+literalTable.get(ctr).split(" ")[1].split(",")[1]);
						polctr++;
						for(int i=ctr; i<literalTable.size();i++){
							String ni = literalTable.get(i);
							ni = ni+" "+Integer.toString(lineCounter);
							lineCounter++;
							literalTable.set(ctr, ni);
							ctr++;
						}
						literalTable2.clear();
						System.out.println("   "+": "+icLine);
						line = fileReader.readLine();
						if(line!= null){
							line = line.toLowerCase();
						}
						continue;
					}
					if(opcode.equals("ds")){
						
						operands = tokens.nextToken();
						icLine = icLine +" "+symbolTable.get(label).split(" ")[0];
						lineCounter = lineCounter + Integer.parseInt(operands) ;
						System.out.println("   "+": "+icLine);
						line = fileReader.readLine();
						if(line!= null){
							line = line.toLowerCase();
						}
						continue;
					}
					if(opcode.equals("dc")){
						operands = tokens.nextToken();
						icLine = icLine +" "+symbolTable.get(label).split(" ")[0];
						icLine = icLine + " c,"+operands;
						lineCounter = lineCounter ++;
						
					}
					if(opcode.equals("origin")){
						operands = tokens.nextToken();
						
						String temp = tokens.nextToken();
						
						int num = Integer.parseInt(temp);
						num += Integer.parseInt(symbolTable.get(operands+":").split(" ")[1]);
						lineCounter = num;
						
						System.out.println("   "+": "+icLine);
						line = fileReader.readLine();
						if(line!= null){
							line = line.toLowerCase();
						}
						continue;
					}
					if(opcode.equals("equ")){
						
						operands = tokens.nextToken();
						String num = symbolTable.get(operands+":").split(" ")[1];
						String loc = symbolTable.get(label).split(" ")[0];
						symbolTable.put(label, loc+" "+num);
						System.out.println("   "+": "+icLine);
						line = fileReader.readLine();
						if(line!= null){
							line = line.toLowerCase();
						}
						continue;
					}
				}
				
				//else if it is not ad
				else{
					operands = tokens.nextToken();
					
					tokens = new StringTokenizer(operands,",", false);
					String opr1 = tokens.nextToken();
					String opr2 = tokens.nextToken();
					icLine = icLine +" "+optMap.get(opr1);
					
					if(symbolTable.containsKey(opr2+":")){
						icLine = icLine +" "+symbolTable.get(opr2+":").split(" ")[0];
					}
					else if(opr2.charAt(0) == '='){
						literalTable.add(opr2+" l,"+Integer.toString(literalcounter));
						icLine = icLine +" "+"l,"+Integer.toString(literalcounter);

						literalcounter++;
					}
					else{
						symbolTable.put(opr2+":", "s"+","+Integer.toString(symbolcounter)+" ");
						symbolcounter++;
						icLine = icLine +" "+symbolTable.get(opr2+":").split(" ")[0];
					}
					
				}
				
				System.out.println(lineCounter+": "+icLine);
				line = fileReader.readLine();
				lineCounter++;
				if(line!= null){
					line = line.toLowerCase();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		Pass1 first_pass = new Pass1();
		first_pass.readOptTable();
		System.out.println(first_pass.optMap);
		first_pass.pass1();
		System.out.println(first_pass.symbolTable);
		System.out.println(first_pass.literalTable);
		System.out.println(first_pass.poolTable);
		

	}
}
