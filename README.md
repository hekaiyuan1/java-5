综合性实验学生选课系统
====================
实验目的
-------
分析学生选课系统<br>
使用GUI常规及其组件设计常规<br>
完成学生选课过程业务逻辑编程<br>
根据文件保存并读取数据<br>

实验要求
-------
一、系统角色分析及类设计
----------------------
属性：教师、学生、课程、学分、课程序号<br>

二、要求
-------
1、设计GUI窗体，支持学生注册、课程新加、学生选课、学生退课、打印学生选课列表<br>
2、基于事件模型对业务逻辑编程，实现在界面上支持上述操作<br>
3、针对操作过程中可能会出现的各种异常，做异常处理<br>
4、基于输入、输出编程，支持学生，课程，教师等数据的读写操作<br>
5、基于Github.com提交实验，包括实验SRC源文件夹程序、README.MD实验报告文档<br>


核心代码
=======

创建窗体
-------

     public class ManagerFrame extends JFrame {
	 CardLayout card = new CardLayout();
	JPanel cardpanel;
	public ManagerFrame() throws Exception {
		super();
		initFrame();
	}
      private void initFrame() throws Exception 
		 this.setTitle("北京石油化工大学选修选课");
		JLabel jlWelcon = new JLabel("课程信息", JLabel.CENTER);
		jlWelcon.setFont(new Font("Aharoni", Font.BOLD, 32));
		JPanel panel1 = new JPanel();
	}

     }





添加面板
-------

     JPanel jpanelAdd = new JPanel();//添加

     JPanel jpanelDel = new JPanel();//删除

		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(jpanelAll, "Add");
				jtfAddCid.requestFocusInWindow();
			}
		});

		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jtable.getSelectedRows().length == 0) {
					JOptionPane.showMessageDialog(null, "请选择课程信息");
				    } else {
					if (JOptionPane.showConfirmDialog(null, "是否要删除") == 0)
					try {
					deleteCourseByTable();
					refresh();
					} catch (Exception e1) {
					e1.printStackTrace();
						}

				    }
			        }
		            });
		
    
    
    
    
文件信息序列化
-------------




	private void initData() throws IOException {
	........................................
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
		jsp.setBorder(new TitledBorder("选课信息"));
	}


测试主类
-------

    public class Start {
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) { 
			e.printStackTrace();
		}
		try {
			new ManagerFrame().setVisible(true);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}<
	}
    }




实验结果
-------
![1](https://github.com/hekaiyuan1/java-5/blob/master/1.png)
![2](https://github.com/hekaiyuan1/java-5/blob/master/2.png)
![3](https://github.com/hekaiyuan1/java-5/blob/master/3.png)

流程图
---------
![4](https://github.com/hekaiyuan1/java-5/blob/master/4.png)

实验感想
-------

通过此次实验是我对java的应用有得到了新的进步，从第一次写代码到现在为止对于Java的理解更得到新的理解
但是这一次的综合实验基本上符合了老师的要求但是还有不足没有做出登录注册界面。对于选课系统的内容还有很
大扩展行脑子里有很多的想法但是基于自己的基础薄弱只能对此次实验达到了这个地步。希望自己在以后能对java
有更高的见解








  
