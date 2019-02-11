package com.webdw2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import com.webdw.demo.UIDemo_DataAccess;
import com.webdw2.Golbal2;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//import net.minidev.json.JSONArray;

public class JWebDW_UIDemoV2 extends JApplet {
	private static CWebDWTransaction2 sqlca = new CWebDWTransaction2();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void ReadMe() {
		System.out.println("JWebDW的测试用户界面程序，演示用.");
		System.out.println(Golbal2.JWebDWInfo);
	}

	static JFrame jframe = new JFrame("JWebDW2.0功能演示");

	static ArrayList targetControls = new ArrayList();

	static JPanel target = null;

	public static void setupFrame() {
		jframe.setVisible(true);
		jframe.getContentPane().setLayout(null);
		jframe.getContentPane().setBackground(new Color(204, 204, 204));
		jframe.setBounds(300, 100, 1000, 600);

		WindowListener listener = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		jframe.addWindowListener(listener);

		JPanel targetpic = new JPanel();
		target = targetpic;
		targetpic.setBounds(10, 50, 960, 500);
		// targetpic.setOpaque(true);
		targetpic.setVisible(true);
		targetpic.setLayout(null);
		targetpic.setBorder(BorderFactory.createEtchedBorder());
		jframe.add(targetpic);

		JButton testbutton = new JButton("retrieve test");
		testbutton.setBounds(500, 20, 200, 20);
		jframe.add(testbutton);

		// 点击按钮
		testbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("testbutton clicked!");
				MyInt2 iret = new MyInt2(0);
				String dwname = "d_products";
				String svalue = sqlca.retrieve(dwname, iret);
				System.out.println("svalue:" + svalue);
				JSONObject retjson = JSONObject.fromObject(svalue);
				net.sf.json.JSONArray json = retjson.getJSONArray("uiobjList");// 先将对象转成json数组
				System.out.println("json size:" + json.size());
				for (int i = 0; i < json.size(); i++) {
					JSONObject json1 = (JSONObject) json.get(i);
					CWebDWUIComponent2 ui = new CWebDWUIComponent2();
					ui.fromJson(json1);
					ui.CreateUINode(target);
					target.validate();
					target.repaint();
					// ui.CreateUINode(jframe);
					// jframe.validate();
					// jframe.repaint();
					System.out.println(i);
					System.out.println(ui.left + " " + ui.top + " " + ui.width + " " + ui.height + " " + ui.text);
				}

				// JButton one = new JButton("hello");
				JLabel one = new JLabel("retrieve ok");
				one.setLayout(null);
				one.setBounds(20, 20, 100, 20);
				one.setVisible(true);
				targetControls.add(one);
				jframe.add(one);
				jframe.validate();
				jframe.repaint();
				System.out.println(targetControls.size());
			}
		});

	}

	public static void main(String[] args) {
		init1();
	}

	public void init() {
		init1();
	}

	public static void init1() {
		// Golbal.InitGlobalVariable();
		setupFrame();
	}
}
