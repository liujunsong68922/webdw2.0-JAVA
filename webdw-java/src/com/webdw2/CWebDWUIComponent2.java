package com.webdw2;

import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.sf.json.JSONObject;

/**
 * WebDW����ķ��ص�UI��� Version:2.0
 * 
 * @author Administrator
 *
 */
public class CWebDWUIComponent2 {
	public String id = "";
	public String name = "";
	public String text = "";
	public String classname = "";

	public int left = 0;
	public int top = 0;
	public int width = 0;
	public int height = 0;

	public double convertRate = 1;

	// constructor
	/**
	 * �յĹ��캯��
	 * 
	 * @param parent
	 */
	public CWebDWUIComponent2() {

	}

	/**
	 * ����classname��������̬����������Ҫ�Ľ���Ԫ�س���
	 * 
	 * @return
	 */
	public Object CreateUINode(Container parent) {
		JLabel newone = new JLabel(this.text);
		newone.setName(this.name);
		newone.setLayout(null);
		newone.setBorder(BorderFactory.createEtchedBorder());
		
		newone.setBounds(this.left, this.top, this.width, this.height);
		newone.setVisible(true);
		parent.add(newone);
		
		return null;
	}

	/**
	 * ��Json�����н�������ת��
	 * 
	 * @param obj
	 */
	public void fromJson(JSONObject obj) {
		double convertRate = 0.3;
		this.id = obj.getString("Id");
		this.name = obj.getString("Name");
		this.classname = obj.getString("classname");
		this.text = obj.getString("Text");

		this.left = (int) (obj.getInt("Left") * convertRate);
		this.top = (int) (obj.getInt("Top") * convertRate);
		this.width = (int) (obj.getInt("Width") * convertRate);
		this.height = (int) (obj.getInt("Height") * convertRate);
	}
}
