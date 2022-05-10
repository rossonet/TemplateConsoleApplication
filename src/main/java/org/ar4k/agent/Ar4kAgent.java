/*
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    */
package org.ar4k.agent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

/**
 * Classe main per avvio Agente Ar4k
 *
 * @author Andrea Ambrosini
 */
public class Ar4kAgent {
	private static final AppManager appManager = new AppManagerImplementation();
	private static int identLevelStatusFile = 2;
	static boolean running = true;

	public static final long WHILE_DELAY = 60 * 1000L;

	public static AppManager getAppmanager() {
		return appManager;
	}

	public static int getIdentLevelStatusFile() {
		return identLevelStatusFile;
	}

	public static boolean isRunning() {
		return running;
	}

	public static void main(final String[] args) {
		runApp();
	}

	public static void runApp() {
		System.out.println("agent started");
		System.out.println(appManager.toString());
		while (running) {
			try {
				final JSONObject status = appManager.getJsonStatus();
				final String statusFilePath = appManager.getStatusFilePath();
				writeStringToFile(status.toString(identLevelStatusFile), statusFilePath);
				Thread.sleep(WHILE_DELAY);
			} catch (final Exception e) {
				System.out.println("agent stopped");
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public static void setIdentLevelStatusFile(final int identLevelStatusFile) {
		Ar4kAgent.identLevelStatusFile = identLevelStatusFile;
	}

	public static void stopAgent() {
		Ar4kAgent.running = false;
	}

	public static void writeStringToFile(final String text, final String fileName) throws IOException {
		final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(text);
		writer.close();
	}
}
