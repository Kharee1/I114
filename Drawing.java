package com.example.drawing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Drawing extends JPanel{
	
	public static boolean isRunning = true;
	Point ball = new Point(15,15);
	Point dir = new Point(0,0);
	int speed = 1;
	int radius = 3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("My App");
		JPanel panel = new Drawing();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setVisible(true);
		panel.setPreferredSize(new Dimension(400, 400));
		frame.add(panel);
		
		JTextField input = new JTextField(20);
		JTextField output = new JTextField(20);
		input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				output.setText(input.getText());
			}
		});
		panel.add(input);
		panel.add(output);
		
		frame.add(panel);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		frame.pack();
		frame.setVisible(true);
		
		((Drawing)panel).run();
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawOval(ball.x,  ball.y,  100, 100);
	}
	public void run() {
		dir.x = 1;
		dir.y = 1;
		while(isRunning) {
			ball.x += (dir.x * speed);
			ball.y += (dir.x * speed);
			if (ball.x+(radius) <= 0 || (ball.x+(radius)) >= 400) {
				dir.x *= -1;
			}
			if (ball.y+(radius) <= 0 || (ball.y+(radius)) >= 400) {
				dir.y *= -1;
			}
			repaint();
			try {
				Thread.sleep(16);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
