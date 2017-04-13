package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class Connecting {
	static Logger logger = Logger.getLogger(Main.settings.get("settings", "logName"));
	private String hostname;
	private String username;
	private String password;

	public Connecting(String hostname, String username, String password) {
		
		this.hostname = hostname;
		this.username = username;
		this.password = password;
						
		logger.info("hostname ="+ hostname + "; username =" + username +"; password ="+ password);
	}

	public void connect() {

		try {
			/* Create a connection instance */

			Connection conn = new Connection(hostname);

			/* Now connect */

			conn.connect();

			/*
			 * Authenticate. If you get an IOException saying something like
			 * "Authentication method password not supported by the server at this stage."
			 * then please check the FAQ.
			 */

			boolean isAuthenticated = conn.authenticateWithPassword(username, password);

			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");

			/* Create a session */

			Session sess = conn.openSession();

			// sess.execCommand("uname -a && date && uptime && who");
			sess.execCommand("sc query 'rctam'");

			System.out.println("Here is some information about the remote host:");

			/*
			 * This basic example does not handle stderr, which is sometimes
			 * dangerous (please read the FAQ).
			 */

			InputStream stdout = new StreamGobbler(sess.getStdout());

			BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

			while (true) {
				String line = br.readLine();
				if (line == null)
					break;

				System.out.println(line);
			}

			/* Show exit status, if available (otherwise "null") */

			System.out.println("ExitCode: " + sess.getExitStatus());

			/* close buffer Reader */

			br.close();

			/* Close this session */

			sess.close();

			/* Close the connection */

			conn.close();

		} catch (IOException e) {
			//e.printStackTrace(System.err);
			//System.exit(2);
			logger.warning("Connection error !");

		}

	}

}
