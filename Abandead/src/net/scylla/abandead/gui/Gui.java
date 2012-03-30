package net.scylla.abandead.gui;

public abstract class Gui {

	// TODO

	public boolean visible = false;

	public boolean isVisible() {
		if (visible) {
			return true;
		} else {
			return false;
		}
	}

	public void destroyGui() {

	}

	abstract public void initializeGui();

	public void openGui() {
		initializeGui();
		visible = true;
	}

	public void closeGui() {
		destroyGui();
		visible = false;
	}

}
