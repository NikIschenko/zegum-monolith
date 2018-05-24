package by.issoft.gui.frame;

import by.issoft.environment.EnvironmentType;
import org.apache.commons.jcs.utils.struct.DoubleLinkedListNode;

import javax.swing.*;

public abstract class FrameNode extends DoubleLinkedListNode<JFrame> {
	FrameNode(final EnvironmentType environmentType) {
		this(480, 320, environmentType.equals(EnvironmentType.RASPBERRY));
	}

	FrameNode(final int width, final int height, final boolean fullScreen) {
		super(new JFrame());
		this.frame().setSize(width, height);
		if (fullScreen) {
			this.frame().setExtendedState(JFrame.MAXIMIZED_BOTH);
			this.frame().setUndecorated(true);
		}
		this.frame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public JFrame frame() {
		return getPayload();
	}

	private FrameNode nextFrame() {
		FrameNode node = (FrameNode) this.next;
		return node == null? firstFrame() : node;
	}

	private FrameNode firstFrame() {
		FrameNode node = null;
		FrameNode prevNode = this;
		while (prevNode != null) {
			node = prevNode;
			prevNode = (FrameNode) prevNode.prev;
		}
		return node;
	}

	void showNextFrame() {
		nextFrame().onShow();
		nextFrame().frame().setVisible(true);
		this.frame().setVisible(false);
	}

	abstract void onShow();
}