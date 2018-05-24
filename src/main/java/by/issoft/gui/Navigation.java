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
		orderedFrames.addLast(new GreetingFrame(environment));
		orderedFrames.addLast(new CountdownFrame(environment));
		orderedFrames.addLast(new SpinnerFrame(environment, recognition));
		orderedFrames.addLast(new ProcessedFrame(environment, recognition));

		FrameNode node = orderedFrames.getFirst();
		while (node != null) {

			node = (FrameNode) node.next;
		}

		return orderedFrames;
	}

	public void showFirstFrame() {
		this.frames.getFirst().frame().setVisible(true);
	}

}