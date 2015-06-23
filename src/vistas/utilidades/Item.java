package vistas.utilidades;

import java.awt.Color;

public class Item {
	private Color id;
	private String description;

	public Item(Color color, String description) {
		this.id = color;
		this.description = description;
	}

	public Color getColor() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String toString() {
		return description;
	}
}