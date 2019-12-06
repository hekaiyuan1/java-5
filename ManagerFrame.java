package model;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ManagerFrame extends JFrame {
	private String userName;
	JButton jbCheckStudent, jbCheckTeacher, jbCheckStatis, jbCheckCourse, jbCheckStatisXuefen, jbMainframe, jbCheck, jbExit, jbOrder;
	CardLayout card = new CardLayout();
	JPanel cardpanel;

	public ManagerFrame() throws Exception {
		super();
		initFrame();
	}

	private void initFrame() throws Exception {

		this.setTitle("北京石油化工大学选修选课系统");
		JLabel jlWelcon = new JLabel("课程信息", JLabel.CENTER);
		jlWelcon.setFont(new Font("Aharoni", Font.BOLD, 30));
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.white);
		panel1.add(jlWelcon, BorderLayout.CENTER);
		this.add(panel1, BorderLayout.NORTH);
		JPanel jpButtons = new JPanel();
		jpButtons.setBackground(Color.white);
		

		cardpanel = new JPanel(card);
		cardpanel.setBackground(Color.white);
		cardpanel.add(new CoursePanel(), "Courseframe");
		
		this.add(cardpanel, BorderLayout.CENTER);
		this.add(jpButtons, BorderLayout.WEST);

		this.setSize(800, 600);
		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

	}

}