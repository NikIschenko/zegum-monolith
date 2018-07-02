package by.issoft;

import by.issoft.environment.EnvironmentType;
import by.issoft.gui.frame.*;
import by.issoft.gui.frame.Frame;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static by.issoft.ApplicationVariables.*;

public class ZegumApplication {
    // required
    @Parameter(names = {"--user", "-u"}, description = "Username for connection to backend", required = true)
    private String login;
    @Parameter(names = {"--pass", "-p"}, description = "User's password for connection to backend", required = true, password = true)
    private String password;
    // custom
    @Parameter(names = {"--env", "-e"})
    private EnvironmentType environmentType;
    @Parameter(names = {"--init", "-i"}, description = "Initialize device by default")
    private boolean initialize = false;
    @Parameter(names = {"--push"}, description = "Servo's pushAngle angle")
    private int pushAngle = 100;
    @Parameter(names = {"--pull"}, description = "Servo's pullAngle angle")
    private int pullAngle = 30;
    @Parameter(names = {"--server", "-s"}, description = "Zegum server ip")
    private String server = "zmile-back.gq:8080";

    public static void main(String... argv) throws URISyntaxException, IOException, InterruptedException {
        ZegumApplication main = new ZegumApplication();
        // parse input arguments to private variables
        JCommander.newBuilder().addObject(main).build().parse(argv);
        main.run();
    }

    private void run() throws URISyntaxException, IOException, InterruptedException {
        // SERVICE
        SERVER_URI = new URI("http://" + server + "/");
        USER_NAME = this.login;
        PASSWORD = this.password;
        // SERVO
        PUSH_ANGLE = this.pushAngle;
        PULL_ANGLE = this.pullAngle;
        INITIALIZE = this.initialize;

        Navigator navigator = new Navigator(initialize, environmentType);
        navigator.frames.forEach(Frame::createComponents);
        navigator.frames.get(0).showFrame();
    }
}