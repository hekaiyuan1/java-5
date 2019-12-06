package model;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class CoursePanel extends JPanel {
	private Vector<String> col;
	private Vector<Vector> rowData;
	private JTable jtable;
	private JButton jb1, jb2, jb3,jb4, jbAddSummit, jbAddCancel, jbDelSummit, jbDelCancel, jbSetSummit, jbSetCancel, jbOrderSummit, jbOrderCancel;
	private CardLayout card = new CardLayout();
	private JTextField jtfAddCid, jtfAddName,jtfAddUser,jtfAddTeacher, jtfDelCidOrName, jtfSetCidOrName, jtfSetCid, jtfSetName, jtfAddXuefen, jtfAddType,jtfSetXuefen, jtfSetType,jtfOrderCidOrName;
	private JPanel jpanelAll = new JPanel(card);
	private JScrollPane jsp;
	private JComboBox jckAddTeacher,jckAddName;
	private JLabel jlCourseCount=new JLabel();
	
	@SuppressWarnings("unchecked")
	public CoursePanel() throws Exception {
		//初始化数据封装成一个方法
		initData();
		
		//添加
		JPanel jpanelAdd = new JPanel();
		jpanelAdd.setBackground(Color.white);
		jpanelAdd.add(new JLabel("课程序号:"));
		jpanelAdd.add(jtfAddCid = new JTextField(20));
		jpanelAdd.add(new JLabel("任课教师:"));
		String teacher[] = { "何开源", "王老师" ,"刘老师", "赵老师" ,"钱老师"};
		jckAddTeacher = new JComboBox(teacher);
		jpanelAdd.add(jckAddTeacher);
		jpanelAdd.add(new JLabel("选课学生:"));
		jpanelAdd.add(jtfAddUser = new JTextField(20));
		jpanelAdd.add(new JLabel("学分:"));
		jpanelAdd.add(jtfAddXuefen = new JTextField(20));
		jtfAddXuefen.setText("2");
		jtfAddXuefen.setEditable(false);
		jpanelAdd.add(new JLabel("课程名字:"));
		String name[] = { "JAVA", "大学语文" ,"英语", "物理" ,"化学"};
		jckAddName = new JComboBox(name);
		jpanelAdd.add(jckAddName);
		jpanelAdd.add(jbAddSummit = new JButton("提交"));
		jpanelAdd.add(jbAddCancel = new JButton("取消"));
		jpanelAdd.setLayout(new GridLayout(7, 2, 150, 50));
		jpanelAdd.setBorder(new TitledBorder("选课:"));

		//删除
		JPanel jpanelDel = new JPanel();
		jpanelDel.setBackground(Color.white);
		jpanelDel.setLayout(new GridLayout(2, 1));
		jpanelDel.setBorder(new TitledBorder("退课:"));

		jpanelAll.setBackground(Color.white);
		jpanelAll.add(jsp, "All");
		jpanelAll.add(jpanelAdd, "Add");
		jpanelAll.add(jpanelDel, "Del");

		JPanel panel = new JPanel();
	
		panel.add(jb1 = new JButton("选课"));
		panel.add(jb2 = new JButton("退课"));

		jb1.setBackground(Color.white);
		jb2.setBackground(Color.white);
	
		Dimension preferredSize = new Dimension(80,60);//设置尺寸
		jb1.setPreferredSize(preferredSize );
		jb2.setPreferredSize(preferredSize );

		panel.setLayout(new GridLayout(3, 3, 3, 10));
		
		this.add(jpanelAll, BorderLayout.EAST);
		this.add(panel, BorderLayout.WEST);
		this.add(jlCourseCount,BorderLayout.SOUTH);
	
	
		//选课按钮监听
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(jpanelAll, "Add");
				jtfAddCid.requestFocusInWindow();
			}
		});
		//退课按钮监听
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jtable.getSelectedRows().length == 0) {
					JOptionPane.showMessageDialog(null, "请先选择一条选课记录!");
				} else {
					if (JOptionPane.showConfirmDialog(null, "确定要删除选定的课程吗?") == 0)
						try {
							deleteCourseByTable();
							refresh();
						} catch (Exception e1) {
							e1.printStackTrace();
						}

				}
			}
		});
		
	
		//添加按钮提交时触发
		jbAddSummit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e){
				String cid = jtfAddCid.getText().trim();
				String name = jckAddName.getSelectedItem().toString();
				String teacher = jckAddTeacher.getSelectedItem().toString();
				String xuefen = jtfAddXuefen.getText().trim();
				String strbuffer=cid+" "+teacher+" "+xuefen+" "+name+"\n";
				
		        try {
		        	FileWriter writer = new FileWriter("F://app.txt", true);
		        	writer.write(strbuffer);
		        	writer.flush();
		        	writer.close();
		        	initData();
		        } catch (IOException e2) {
		            e2.printStackTrace();
		        }
				JOptionPane.showMessageDialog(null, "添加成功!");
				jtfAddCid.setText(null);
				jtfAddUser.setText(null);
				jtfAddXuefen.setText(null);
				card.first(jpanelAll);
			}
		});
		//添加按钮取消时触发
		jbAddCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.first(jpanelAll);
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private void refresh() {
		try {
			rowData = new Vector<Vector>();
			
		    FileReader reader = new FileReader("F://app.txt");
			BufferedReader br = new BufferedReader(reader);
	 
			String eachLine = null;
			while((eachLine = br.readLine()) != null){
			
				String[] temp = eachLine.split(" ");
			
				Vector<String> row = new Vector<String>();
				for(int i = 0; i < temp.length; i++){
					row.add(temp[i]);
				}
				rowData.add(row);
			}
			
			jtable.updateUI();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private void initData() throws IOException {
		//声明表头
		col = new Vector<String>();
	    col.addElement("课程序号");
	    col.addElement("任课教师");
	    col.addElement("选课学生");
	    col.addElement("课程学分");
	    col.addElement("课程名字");
	    
	    //声明所有行
	    rowData = new Vector<Vector>();
		
	    FileReader reader = new FileReader("F://app.txt");
		BufferedReader br = new BufferedReader(reader);
 
		String eachLine = null;
		while((eachLine = br.readLine()) != null){
		
			String[] temp = eachLine.split(" ");
			
			Vector<String> row = new Vector<String>();
			for(int i = 0; i < temp.length; i++){
				row.add(temp[i]);
			}
			rowData.add(row);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(rowData, col);
		jtable = new JTable();
		jtable.setModel(dtm);
		jsp = new JScrollPane(jtable);
		jsp.setBorder(new TitledBorder("选课信息："));
	}

	private void deleteCourseByTable() throws Exception {
		String values[] = new String[100];
		String courseName[] = new String[100];
		int rows[] = jtable.getSelectedRows();
		for (int i = 0; i < rows.length; i++) {
			values[i] = (String) jtable.getValueAt(rows[i], 0);
			courseName[i] = (String) jtable.getValueAt(rows[i], 1);
			
			try {
				String readedLine;
				String strbuffer="";
	            BufferedReader br = new BufferedReader(new FileReader("F://app.txt"));
	            while ((readedLine = br.readLine()) != null) {
	                if (readedLine.startsWith(values[i])) {
	                    continue;
	                }
	                strbuffer=strbuffer+readedLine+"\n";
	            }
	            FileWriter writer = new FileWriter("F://app.txt");
	            writer.write(strbuffer);
	            writer.flush();
	            writer.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
			JOptionPane.showMessageDialog(null, " 【 " + courseName[i] + " 】 删除成功!");
		}
	}
}