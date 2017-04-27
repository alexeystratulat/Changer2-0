package application;

import java.io.File;
import java.util.logging.Logger;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class RemoteFiles {
	static Logger logger = Logger.getLogger(Main.settings.get("settings", "logName"));
	Servers server;
	private static int port = 22;
	private String prompts;

	public RemoteFiles(Servers server) {
		this.server = server;
		logger.info(server.toString());

	}
	
	public RemoteFiles(Servers server, String prompts ) {
		this.server = server;
		this.prompts = prompts;
		logger.info(server.toString());

	}

	public String toGetFile() {
		String done = "connected";
		String error = "connection error!";

		try {

			JSch jsch = new JSch();
			Session session = jsch.getSession(server.getUserName(), server.getIpAdress(), port);
			session.setPassword(server.getPassword());
			session.setConfig("StrictHostKeyChecking", " no");
			System.out.println(server.getIpAdress().toString() + " Establishing Connection...");
			session.connect();
			System.out.println(server.getIpAdress().toString() + " Connection established.");

			ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
			sftpChannel.connect();
			System.out.println(server.getIpAdress().toString() + " SFTP Channel created.");
			//
			// Main.settings.get("settings", "iniForEnv");
			File file = new File(
					Main.settings.get("settings", "mainDirectory") + server.getServerName() + "/" + "Vportal.ini");
			file.createNewFile();
			sftpChannel.get(Main.settings.get("server", "Vportal"),
					Main.settings.get("settings", "mainDirectory") + server.getServerName() + "/" + "Vportal.ini");

			// System.out.println(host.toString() + " -----> "+Vportal);

			sftpChannel.disconnect();
			session.disconnect();

			// creating and editing alternative Vportals
			// Editor editVportal = new Editor(Vportal,pahtForVportal,host);
			// editVportal.makingVportal_au();
			// editVportal.makingVportal_st();

		} catch (Exception e) {
			return error;
		}
		// creating and editing alternative Vportals
		Editor makingVportal = new Editor(server,
				Main.settings.get("settings", "mainDirectory") + server.getServerName());
		makingVportal.makingVportalSt();
		makingVportal.makingVportalAutomation();

		return done;
	}

	public String toPutFile() {
		String done = "connected";
		String error = "connection error!";

		try {

			JSch jsch = new JSch();
			Session session = jsch.getSession(server.getUserName(), server.getIpAdress(), port);
			session.setPassword(server.getPassword());
			session.setConfig("StrictHostKeyChecking", " no");
			System.out.println(server.getIpAdress().toString() + " Establishing Connection...");
			session.connect();
			System.out.println(server.getIpAdress().toString() + " Connection established.");

			ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
			sftpChannel.connect();
			System.out.println(server.getIpAdress().toString() + " SFTP Channel created.");
			//

			File file = new File(
					Main.settings.get("settings", "mainDirectory") + server.getServerName() + "/" + "Vportal.ini");
			file.createNewFile();
			
			
			if(prompts.contains("manual")){
				System.out.println(server.getServerName() + "/" + "Vportal_au.ini");
				
			sftpChannel.put(Main.settings.get("settings", "mainDirectory")+server.getServerName() + "/" + "Vportal_au.ini", Main.settings.get("server", "Vportal"));
			}
			if(prompts.contains("automated")){
				System.out.println("automated");
				sftpChannel.put(Main.settings.get("settings", "mainDirectory")+server.getServerName() + "/" + "Vportal_st.ini", Main.settings.get("server", "Vportal"));
				}
			// System.out.println(host.toString() + " -----> "+Vportal);

			sftpChannel.disconnect();
			session.disconnect();

		} catch (Exception e) {
			return error;
		}

		return "done";

	}

}
