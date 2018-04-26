package by.issoft.gui.frame;

import org.apache.commons.jcs.utils.struct.DoubleLinkedListNode;

import javax.swing.*;
import java.awt.*;

public abstract class FrameNode extends DoubleLinkedListNode<JFrame> {
	FrameNode() {
		super(new JFrame());
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