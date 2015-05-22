package com.tarena.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import com.tarena.service.ExamService;

/**
 * 考试界面
 * @author xiaoyao
 *
 */
public class ExamFrame extends DecorateFrame {

	public ExamFrame(int width, int height) {
		super(width, height);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
	}

	private ClientContext clientContext;
	
	public void setClientContext(ClientContext clientContext) {
		this.clientContext = clientContext;
	}
	
	public class OptionJBox extends JCheckBox{
		
		private int value;
		
		public OptionJBox(int value,String text){
			super(text);
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}			
	}
	
	
	private int width;//描述窗体宽
	
	private int height;//描述窗体高
	
	// 顶栏 图片
	public JPanel createContentPane(){
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		URL url = ExamFrame.class.getResource("exam_title.png");
		ImageIcon icon = new ImageIcon(url);
		JLabel l = new JLabel(icon);
		contentPane.add(l,BorderLayout.NORTH);
		contentPane.add(createCenterPane(),BorderLayout.CENTER);
		contentPane.add(createToolsPane(),BorderLayout.SOUTH);
		return contentPane;
	}
		
	// 用户 考试信息提示：
	private JLabel examInfo;
	
	private JPanel createCenterPane(){
		JPanel centerPane = new JPanel();
		centerPane.setLayout(new BorderLayout());
		
		examInfo = new JLabel("",JLabel.CENTER);
		
		centerPane.add(examInfo,BorderLayout.NORTH);
		centerPane.add(createQuestionPane(),BorderLayout.CENTER);
		centerPane.add(createOptionsPane(),BorderLayout.SOUTH);
		
		return centerPane;
	}
	
	// 答案选择 选项
	private List<OptionJBox> optionJBoxs;
	private OptionJBox a;
	private OptionJBox b;
	private OptionJBox c;
	private OptionJBox d;
	
	public void setOptionTrue(Integer value){
		
	}
	
	public List<OptionJBox> getOptions(){
		return optionJBoxs;
		
	}
	
	private JPanel createOptionsPane(){
		JPanel optionsPane = new JPanel();
		optionsPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		a = new OptionJBox(1, "A");
		b = new OptionJBox(2, "B");
		c = new OptionJBox(3, "C");
		d = new OptionJBox(4, "D");
		optionJBoxs = new ArrayList<OptionJBox>();
		optionJBoxs.add(a);
		optionJBoxs.add(b);
		optionJBoxs.add(c);
		optionJBoxs.add(d);
		
		
		optionsPane.add(a);
		optionsPane.add(b);
		optionsPane.add(c);
		optionsPane.add(d);
		
		return optionsPane;
	}
	
	// 显示题目文本域。
	private JTextArea questionArea;
	
	public void showQuestion(String question){
		questionArea.setText(question);
	}
	
	private JScrollPane createQuestionPane(){
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder("题目"));
		
		questionArea = new JTextArea();
		questionArea.setEditable(false);
		
		scrollPane.getViewport().add(questionArea);
		
		return scrollPane;
	}
		
	// 题目 提醒栏
	private JLabel titleProgress;
	private JLabel restTime;
	
	private JPanel createToolsPane(){
		JPanel toolsPane = new JPanel();
		toolsPane.setLayout(new BorderLayout(10,0));
		
		titleProgress = new JLabel("",JLabel.CENTER);
		restTime = new JLabel("",JLabel.CENTER);
		
		toolsPane.add(titleProgress,BorderLayout.WEST);
		toolsPane.add(restTime,BorderLayout.EAST);
		toolsPane.add(createBtnPane(),BorderLayout.CENTER);
		
		return toolsPane;
	}
	
	// 上一题，下一题，交卷按钮
	private JButton prevBtn;
	private JButton nextBtn;
	private JButton overBtn;
	private JButton menuBtn;

	private JPanel createBtnPane(){
		
		JPanel btnPane = new JPanel();
		btnPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		prevBtn = new JButton("上一题");
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientContext.prev(getUserChooseAnswer());
			}
		});
		
		nextBtn = new JButton("下一题");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			clientContext.next(getUserChooseAnswer());
			}
		});
		
		overBtn = new JButton("交卷");
		overBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientContext.over(getUserChooseAnswer());
			}
		});
		
		menuBtn = new JButton("返回菜单");
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientContext.returnMenu();
			}
		});
		
		btnPane.add(prevBtn);
		btnPane.add(nextBtn);
		btnPane.add(overBtn);
		btnPane.add(menuBtn);
		
		return btnPane;
	}
	
	
	private List<Integer> getUserChooseAnswer(){
		List<Integer> answers = new ArrayList<Integer>();
		for(OptionJBox o : optionJBoxs){
			if(o.isSelected()){
				answers.add(o.getValue());
			}
		}
		return answers; 
		
	}
	
	public void showExamInfo(String examInfo) {
		this.examInfo.setText(examInfo);
	}

	public void showTitleProgress(String titleProgress) {
		this.titleProgress.setText(titleProgress);
	}

	public void showRestTime(String restTime) {
		this.restTime.setText(restTime);
	}

	

	OptionJBox getA() {
		return a;
	}
	
	public OptionJBox getB() {
		return b;
	}

	public OptionJBox getC() {
		return c;
	}


	public OptionJBox getD() {
		return d;
	}

	public JButton getPrevBtn() {
		return prevBtn;
	}

	public JButton getNextBtn() {
		return nextBtn;
	}


	public JButton getOverBtn() {
		return overBtn;
	}

}
