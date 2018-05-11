package by.issoft.gui;

import by.issoft.environment.Environment;
import by.issoft.gui.frame.*;
import by.issoft.service.recognition.Recognition;
import org.apache.commons.jcs.utils.struct.DoubleLinkedList;

import javax.swing.*;

public class Navigation {
	private final DoubleLinkedList<FrameNode> frames;
	private final Environment environment;
	private final Recognition recognition;

	public Navigation(final Environment environment, final Recognition recognition) {
		this.environment = environment;
		this.recognition = recognition;
		this.frames = orderedFrames();
	}

	private DoubleLinkedList<FrameNode> orderedFrames() {
		final DoubleLinkedList<FrameNode> orderedFrames = new DoubleLinkedList<>();
		orderedFrames.addLast(new GreetingFrame(environment.camera()));
		orderedFrames.addLast(new CountdownFrame(environment.camera()));
		orderedFrames.addLast(new SpinnerFrame(environment.camera(), recognition));
		orderedFrames.addLast(new ProcessedFrame(environment.servo(), recognition));

		FrameNode node = orderedFrames.getFirst();
		while (node != null) {
			node.frame().setSize(environment.screen().width(), environment.screen().height());
			if (environment.screen().fullScreen()) {
				node.frame().setExtendedState(JFrame.MAXIMIZED_BOTH);
				node.frame().setUndecorated(true);
			}
			node.frame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			node = (FrameNode) node.next;
		}

		return orderedFrames;
	}

	public void showFirstFrame() {
		this.frames.getFirst().frame().setVisible(true);
	}

}