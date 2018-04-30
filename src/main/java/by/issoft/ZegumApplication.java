package by.issoft;

import by.issoft.environment.Environment;
import by.issoft.environment.servo.RotationParameters;
import by.issoft.gui.Navigation;
import by.issoft.service.authentication.Authentication;
import by.issoft.service.authentication.JwtAuthentication;
import by.issoft.service.recognition.Recognition;
import by.issoft.service.recognition.ZegumRecognition;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.System.exit;

public class ZegumApplication {
	// required
	@Parameter(names={"--user", "-u"}, description = "Username for connection to backend", required = true)
	private String login;
	@Parameter(names={"--password", "-p"}, description = "User's password for connection to backend", required = true, password = true)
	private String password;
	// custom
	@Parameter(names={"--environment", "-e"}, description = "Environment: [rasp, desk]")
	private String environment = "desk";
	@Parameter(names={"--initialize", "-i"}, description = "Initialize device by default")
	private boolean initialize = false;
	@Parameter(names={"--pushAngle"}, description = "Servo's pushAngle angle")
	private int pushAngle = 220;
	@Parameter(names={"--pullAngle"}, description = "Servo's pullAngle angle")
	private int pullAngle = 155;
	@Parameter(names = {"--server, -s"}, description = "Zegum server ip")
	private String server = "40.68.189.184:7070";

	public static void main(String ... argv) throws URISyntaxException {
		ZegumApplication main = new ZegumApplication();
		// parse input arguments to private variables
		JCommander.newBuilder().addObject(main).build().parse(argv);
		main.run();
	}

	private void run() throws URISyntaxException {
		// server URI
		URI zegumServerUri = new URI("http://" + server + "/");
		// backend services
		Authentication authentication = new JwtAuthentication(zegumServerUri, login, password);
		Recognition recognition = new ZegumRecognition(zegumServerUri, authentication);
		// environment devices initialization
		Environment environment = new Environment(this.environment, new RotationParameters(pushAngle, pullAngle));
		if (initialize) {
			environment.initialize();
		}
		// gui interface
		Navigation navigation = new Navigation(environment, recognition);
		navigation.showFirstFrame();
	}

}