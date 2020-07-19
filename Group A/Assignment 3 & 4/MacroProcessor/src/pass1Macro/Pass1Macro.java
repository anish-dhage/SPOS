package pass1Macro;

import java.io.*;
import java.util.*;

import pass1Macro.MNT;

public class Pass1Macro {
	BufferedReader fileReader;
	Vector<String> MDT = new Vector<String>();
	LinkedHashMap<String,String> KPT = new LinkedHashMap<String, String>();
	HashMap<String,MNT> MNT1 = new HashMap<String, MNT>();
	HashMap<String, HashMap<String,String>> PNTAB = new HashMap<String, HashMap<String,String>>();

	void pass1macro() throws Exception{
		fileReader = new BufferedReader(new FileReader("/home/anish/College/SPOS/Group A/Assignment 3 & 4/MacroProcessor/src/pass1Macro/MacroCode.txt"));
		int MDT_ctr = 0;
		int KPT_ctr = 0;
		int HPP_ctr , HKP_ctr;
		HashMap<String, String> PNTentry;
		
		String line = fileReader.readLine().toLowerCase();
		while(line!=null){
			if(line.equals("macro")){
				PNTentry = new HashMap<String, String>();
				HPP_ctr = 0;
				HKP_ctr = 0;
				int iKPT = KPT_ctr;
				line = fileReader.readLine().toLowerCase();
				String tokens[] = line.split(" ");
				String para[] = tokens[1].split(",");
				String macro_name = tokens[0];
				for(int i = 0; i<para.length ;i++){
					
					if(para[i].contains("=")){
						//System.out.println(para[i]);
						String def_para[] = para[i].split("=");
						String name = def_para[0];
						String def = " ";
						if(def_para.length == 2){
							def = def_para[1];
						}
						PNTentry.put(name, "(p,"+Integer.toString(i)+")");
						HKP_ctr++;
						
						KPT.put(name, def);
						KPT_ctr++;
						
					}
					else{
						PNTentry.put(para[i], "(p,"+Integer.toString(i)+")");
						HPP_ctr++;
					}
				}
				
				PNTAB.put(macro_name, PNTentry);
				MNT m1 = new MNT(HPP_ctr, HKP_ctr, MDT_ctr ,iKPT);
				MNT1.put(macro_name, m1);
				
				line = fileReader.readLine();
				if(line!= null){
					line = line.toLowerCase();
				}
				
				while(!line.equals("mend")){
					
					String mdt_line = "";
					String tokens1[] = line.split(" ");
					String operand[] = tokens1[1].split(",");
					mdt_line = mdt_line + tokens1[0] +" ";
					String var = PNTentry.getOrDefault(operand[0],operand[0]);
					mdt_line = mdt_line + var +",";
					var = PNTentry.getOrDefault(operand[1],operand[1]);
					mdt_line = mdt_line + var;
					MDT.add(MDT_ctr, mdt_line);
					MDT_ctr++;
					
					line = fileReader.readLine();
					if(line!= null){
						line = line.toLowerCase();
					}
				}
				MDT.add(MDT_ctr, line);
				MDT_ctr++;
				
			}
			//System.out.println(line);
			line = fileReader.readLine();
			if(line!= null){
				line = line.toLowerCase();
			}
		}	

	}
	
	public void display(){
		System.out.println("MNT:");
		
		for (String name: MNT1.keySet()){
            String key = name;
            MNT value = MNT1.get(name);  
            System.out.print(key);
            value.displayMNT();
		} 
		
		System.out.println("\nKPTTAB : \n"+KPT);
		
		System.out.println("\nPNTAB:");
		for (String name: PNTAB.keySet()){
            String key = name;
            HashMap<String, String> value = PNTAB.get(name);  
            System.out.println(key + value);
		} 
		
		System.out.println("\nMDT:");
		for(String i : MDT){
			System.out.println(i);
		}
	}
	
	public static void main(String[] args) {
		Pass1Macro pass1 = new Pass1Macro();
		try {
			pass1.pass1macro();
			pass1.display();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
