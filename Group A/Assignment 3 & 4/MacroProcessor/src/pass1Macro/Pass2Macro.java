package pass1Macro;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class Pass2Macro extends Pass1Macro{
	
	String finalCode = "start\n";
	HashMap<String,String> APTAB = new HashMap<String, String>();
	List<String> aList;
	
	public void pass2() throws Exception{
		pass1macro();
		fileReader = new BufferedReader(new FileReader("/home/anish/College/SPOS/Group A/Assignment 3 & 4/MacroProcessor/src/pass1Macro/MacroCode.txt"));
		String line = fileReader.readLine().toLowerCase();
		while(!line.equals("start")){
			line = fileReader.readLine();
			if(line!= null){
				line = line.toLowerCase();
			}
		}
		while(line!=null){
			String MDTlines = "";
			if(MNT1.containsKey(line.split(" ")[0])){
				String para[] = line.split(" ")[1].split(",");
				MNT obj = MNT1.get(line.split(" ")[0]);
				int pp = obj.HPP;
				int kp = obj.HKP;
				APTAB = new HashMap<String, String>();
				
				//read MDT
				int mdtp=obj.MDTP;
				String nline = MDT.get(mdtp);
				MDTlines = "";
				while(!nline.equals("mend")){
					nline = MDT.get(mdtp);
					MDTlines += nline + "\n";
					mdtp++;
				}	
				
				//get list of kpt para in order
				Set<String> keys = KPT.keySet();
				int n = keys.size(); 
			    aList = new ArrayList<String>(n); 
			    for (String x : keys){ 
			        aList.add(x); 
			    }
			    
				//put positional parameters
				for(int j=0;j<pp;j++){
					String key= "(p,"+Integer.toString(j)+")";
					APTAB.put(key, para[j]);
				}
				
				//put default keyword parameters
				int kptp = obj.KPDTP;
				for(int j=pp;j<pp+kp;j++){
					
					String key= "(p,"+Integer.toString(j)+")";
					
					//put KPT entries in APT
					//System.out.println(aList.get(kptp));
					APTAB.put(key, KPT.get(aList.get(kptp)));
				    kptp++;
				}
				
				//put final keyword parameters
				for(int j=pp;j<para.length;j++){
					int index = aList.indexOf(para[j].split("=")[0]);
					index = index + pp - obj.KPDTP;
					String key= "(p,"+Integer.toString(index)+")";
					
					APTAB.put(key, para[j].split("=")[1]);
				}
				
			}
			
			String finalMDT = MDTlines;
			for (HashMap.Entry<String, String> entry : APTAB.entrySet()) {
				
			    finalMDT = finalMDT.replaceAll(entry.getKey(), entry.getValue());
			    
			}
		    finalMDT = finalMDT.replaceAll("mend", "");
			finalCode+=finalMDT;
			//System.out.println(MDTlines);
			//System.out.println(aList);
			//System.out.println(APTAB);
			line = fileReader.readLine();
			if(line!= null){
				line = line.toLowerCase();
			}
			APTAB.clear();
		}
		finalCode += "end";
		System.out.println(finalCode);
	}

	public static void main(String[] args) {
		Pass2Macro pass2 = new Pass2Macro();
		try {
			pass2.pass2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
