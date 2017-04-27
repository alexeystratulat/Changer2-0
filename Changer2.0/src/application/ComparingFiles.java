package application;

import java.io.File;
import java.util.logging.Logger;

public class ComparingFiles {
	static Logger logger = Logger.getLogger(Main.settings.get("settings", "logName"));
	
	
	
	private String vportal;
	private String vportal_au;
	private String vportal_st;
	
	
	
	
	
	
	

	public ComparingFiles(Servers servers) {
		// TODO Auto-generated constructor stub
		
		vportal = Main.settings.get("settings", "mainDirectory")+servers.getServerName()+"/"+"Vportal.ini";
		vportal_au = Main.settings.get("settings", "mainDirectory")+servers.getServerName()+"/"+"Vportal_au.ini";
		vportal_st = Main.settings.get("settings", "mainDirectory")+servers.getServerName()+"/"+"Vportal_st.ini";
		
		
		
	}












	public String compareFiles() {
		
		System.out.println(vportal);
		System.out.println(vportal_st);
		System.out.println(vportal_au);
		
		
		
		
		
		
		File vportalFile = new File(vportal);
		File vportal_stFile = new File(vportal_st);
		File vportal_auFile = new File(vportal_au);
		
		
		
		
		
		if (vportalFile.length() == vportal_stFile.length()) {

			return "manual";

		}

		if (vportalFile.length() == vportal_auFile.length()) {

			return "automated";

		}
		
		
		
		
		
		
		
		
		
		
		

		return "unknown";

	}

}
