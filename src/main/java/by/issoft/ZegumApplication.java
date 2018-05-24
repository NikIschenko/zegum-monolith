package by.issoft;

import by.issoft.environment.Environment;
import by.issoft.environment.EnvironmentType;
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

public class ZegumApplication {
	// required
	@Parameter(names={"--user", "-u"}, description = "Username for connection to backend", required = true)
	private String login;
	@Parameter(names={"--pass", "-p"}, description = "User's password for connection to backend", required = true, password = true)
	private String password;
	// custom
	@Parameter(names={"--env", "-e"})
	private EnvironmentType environment;
	@Parameter(names={"--init", "-i"}, description = "Initialize device by default")
	private boolean initialize = false;
	@Parameter(names={"--push"}, description = "Servo's pushAngle angle")
	private int pushAngle = 100;
	@Parameter(names={"--pull"}, description = "Servo's pullAngle angle")
	private int pullAngle = 30;
	@Parameter(names = {"--server", "-s"}, description = "Zegum server ip")
    private String server = "zmile-back.gq:8080";

	public static void main(final String ... argv) throws URISyntaxException {
		ZegumApplication main = new ZegumApplication();
		// parse input arguments to private variables
		JCommander.newBuilder().addObject(main).build().parse(argv);
		main.run();
	}

	private void run() throws URISyntaxException {
		// server URI
		final URI zegumServerUri = new URI("http://" + server + "/");
		// backend services
		final Authentication authentication = new JwtAuthentication(zegumServerUri, login, password);
		final Recognition recognition = new ZegumRecognition(zegumServerUri, authentication);
		// environment devices initialization
		final Environment environment = new Environment(this.environment, new RotationParameters(pushAngle, pullAngle));
		if (initialize) {
			environment.initialize();
		}
		// gui interface
		final Navigation navigation = new Navigation(environment, recognition);
		navigation.showFirstFrame();
	}

}