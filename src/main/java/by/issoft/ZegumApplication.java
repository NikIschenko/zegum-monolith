package by.issoft;

import by.issoft.environment.Environment;
import by.issoft.environment.servo.ServoParameters;
import by.issoft.gui.Navigation;
import by.issoft.service.authenticate.Authentication;
import by.issoft.service.authenticate.JwtAuthentication;
import by.issoft.service.recognition.Recognition;
import by.issoft.service.recognition.ZegumRecognition;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

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
	@Parameter(names={"--push"}, description = "Servo's push angle")
	private int push = 220;
	@Parameter(names={"--pull"}, description = "Servo's pull angle")
	private int pullArgv = 155;
	@Parameter(names = {"--server, -s"}, description = "Zegum server ip")
	private String serverArgv = "40.68.189.184:7070";

	public static void main(String ... argv) {
		ZegumApplication main = new ZegumApplication();
		// parse input arguments to private variables
		JCommander.newBuilder().addObject(main).build().parse(argv);
		main.run();
	}

	private void run() {
		// environment devices initialization
		Environment environment = new Environment(this.environment, new ServoParameters(push, pullArgv));
		if (initialize) {
			environment.initialize();
		}

		// backend services
		String zegumServerUrl = "http://" + serverArgv + "/";
		Authentication authentication = new JwtAuthentication(zegumServerUrl, login, password);
		Recognition recognition = new ZegumRecognition(zegumServerUrl, authentication);

		// gui interface
		Navigation navigation = new Navigation(environment, recognition);
		navigation.showFirstFrame();
	}


}