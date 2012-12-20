package gui;

import geometry.Vector3D;

import java.awt.Color;

public class Star {

	public Vector3D location;
	public Color color;
	public double radius;
	
	public Star(Vector3D location, Color color, double radius) {
		this.location = location;
		this.color = color;
		this.radius = radius;
	}
	
}
