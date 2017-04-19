package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.annotation.processing.SupportedSourceVersion;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class Connecting {
	static Logger logger = Logger.getLogger(Main.settings.get("settings", "logName"));

	Servers server;
	Connection conn;
	String status;

	public Connecting(Servers server) {
		this.server = server;
		logger.info(server.toString());
		conn = new Connection(server.getIpAdress());
	}

	public Connecting(Servers server, String status) {
		this.server = server;
		this.status = status;
		logger.info(server.toString());
		conn = new Connection(server.getIpAdress());
	}

	public String connect() {

		try {

			conn.connect();

			boolean isAuthenticated = conn.authenticateWithPassword(server.getUserName(), server.getPassword());

			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");

			Session sess = conn.openSession();

			sess.execCommand("pwd");

			logger.info("CONNECTING CHECK" + "\n" + "ExitCode: " + sess.getExitStatus());

			sess.close();

			conn.close();
			return "connected";

		} catch (IOException e) {
			// e.printStackTrace(System.err);

			logger.warning("Connection error !");
			return "Connection error !";

		}

	}

	public String statusTam() {

		try {

			conn.connect();

			boolean isAuthenticated = conn.authenticateWithPassword(server.getUserName(), server.getPassword());

			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");

			Session sess = conn.openSession();

			sess.execCommand("sc query 'rctam'");

			InputStream stdout = new StreamGobbler(sess.getStdout());

			BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
			String status = "null";
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;

				if (line.contains("RUNNING")) {

					status = "running";
				}

				if (line.contains("STOPPED")) {

					status = "stopped";
				}

			}
			logger.info("STATUS FOR TAM CHECK" + "\n" + "ExitCode: " + sess.getExitStatus());

			br.close();

			sess.close();

			conn.close();
			return status;

		} catch (IOException e) {
			e.printStackTrace(System.err);
			logger.warning("Connection error !");
			return "Connection error !";

		}

	}

	public String restartingTam() {

		try {

			conn.connect();

			boolean isAuthenticated = conn.authenticateWithPassword(server.getUserName(), server.getPassword());

			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");

			Session sess = conn.openSession();

			if (status.equals("running")) {
				sess.execCommand("bin/stop_tas");
				System.out.println("running ---> stop");

			} else {
				sess.execCommand("bin/start_tas");
				System.out.println("stopped ---> run");
			}

			

			InputStream stdout = new StreamGobbler(sess.getStdout());

			BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
			String status = "null";
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;

				if (line.contains("RUNNING")) {

					status = "running";
				}

				if (line.contains("STOPPED")) {

					status = "stopped";
				}

			}
			logger.info("STATUS FOR TAM CHECK" + "\n" + "ExitCode: " + sess.getExitStatus());

			br.close();

			sess.close();

			conn.close();
			return status;

		} catch (IOException e) {
			e.printStackTrace(System.err);
			logger.warning("Connection error !");
			return "Connection error !";

		}

	}

}
