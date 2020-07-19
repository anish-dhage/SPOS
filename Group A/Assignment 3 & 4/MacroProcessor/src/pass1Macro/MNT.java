package pass1Macro;

public class MNT {
	int HPP;
	int HKP;
	int MDTP;
	int KPDTP;
	
	MNT(int HPP, int HKP, int MDTP, int KPDTP){
		this.HPP = HPP;
		this.HKP = HKP;
		this.MDTP = MDTP;
		this.KPDTP = KPDTP;
	}
	
	int getHPP(){
		return HPP;
	}
	
	int getHKP(){
		return HKP;
	}
	
	int getMDTP(){
		return MDTP;
	}
	
	int getKPDTP(){
		return KPDTP;
	}
	
	void displayMNT(){
		System.out.println("\t" + HPP + "\t" + HKP + "\t" + MDTP + "\t" + KPDTP );
	}
}
