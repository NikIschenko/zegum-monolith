package by.issoft;

import by.issoft.environment.DesktopEnvironment;
import by.issoft.environment.Environment;
import by.issoft.environment.EnvironmentType;
import by.issoft.environment.RaspberryEnvironment;
import by.issoft.environment.servo.RotationParameters;
import by.issoft.gui.carousel.Carousel;
import by.issoft.gui.frame.*;
import by.issoft.gui.frame.Frame;
import by.issoft.gui.frame.carousel.FramesCarousel;
import by.issoft.gui.frame.carousel.item.Countdown;
import by.issoft.gui.frame.carousel.item.Greeting;
import by.issoft.gui.frame.carousel.item.Processed;
import by.issoft.gui.frame.carousel.item.Spinner;
import by.issoft.service.authentication.Authentication;
import by.issoft.service.authentication.JwtAuthentication;
import by.issoft.service.recognition.Recognition;
import by.issoft.service.recognition.ZegumRecognition;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

    public static void main(final String... argv) throws URISyntaxException, IOException, InterruptedException {
        ZegumApplication main = new ZegumApplication();
        // parse input arguments to private variables
        JCommander.newBuilder().addObject(main).build().parse(argv);
        main.run();
    }

    private void run() throws URISyntaxException, IOException, InterruptedException {
        // server URI
        final URI zmileServerUri = new URI("http://" + server + "/");

        // backend services
        final Authentication authentication = new JwtAuthentication(zmileServerUri, login, password);
        final Recognition recognition = new ZegumRecognition(zmileServerUri, authentication);

        final Environment environment = environmentType.equals(EnvironmentType.RASPBERRY)
                ? new RaspberryEnvironment(new RotationParameters(pushAngle, pullAngle))
                : new DesktopEnvironment();
        if (initialize)
            environment.initialize();

        ResizableFrame resizableFrame = new ResizableFrame(new Dimension(480, 320), environmentType.equals(EnvironmentType.RASPBERRY));
        // create frame's carousel
        FramesCarousel framesCarousel =
            new FramesCarousel(
                new Carousel(
                    new Greeting(resizableFrame, environment.camera()),
                    new Countdown(resizableFrame, environment.camera()),
                    new Spinner(resizableFrame, environment.camera(), recognition),
                    new Processed(resizableFrame, environment.servo(), recognition)
                ));

        //carousel.stream
        framesCarousel.createComponents();
        framesCarousel.firstItem().show();
    }
}