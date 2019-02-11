package com.webdw2;

//Rem -------------------------------------------------
//Rem WebDW������������
//Rem Ŀ���ǶԿͻ��˵ķ����ṩһ���ں�̨������֧������
//Rem �����Ȳ���һ�����ݿ������
//Rem @CopyRight Mr.Liu Junsong 2008-2009
//Rem @��Ȩ���� ������ 2008-2009
//Rem E_mail : liujunsong@yahoo.com.cn
//Rem -------------------------------------------------
import java.io.*;
import java.net.*;

public class CWebDWTransaction2 extends Golbal2 {
	public String _ReadMe = "����֧�֣���̨���������";

	// '���ʵ��ò���,��Ҫ�ⲿ������
	public String errString = ""; // '������Ϣ�洢�ַ���

	public String resultString = ""; // 'ִ�к�ķ����ַ���

	// '����������ִ��һ��SQL��䣬����һ����׼�Ľ���ַ���
	// '���������һ���ײ��ʵ�ʵ��ù��̣�
	// '�����������������������������е��õ�
	// '��׼����һ���ַ���
	// '����д��󣬴���洢��errString����
	public String retrieve(String dwname, MyInt2 iret) {// As String
		String surl = "";// As String

		errString = "";
		// opertype = opertypearg;// '����Ϊ��ѯ����

		// '����Ҫִ�е�sql�����һ��rand������Ϊ�˽�����������
		surl = Golbal2.G_ServerURL + "?dwname=" + dwname +"&args="+ "&rand=" + Rnd(10);
		System.out.println(surl);
		try {
			URL url = new URL(surl);
			URLConnection conn = url.openConnection();
			// conn.setReadTimeout(30000);
			System.out.println("getting inputStream finished");
			// conn.connect();
			HttpURLConnection hconn = (HttpURLConnection) conn;
			hconn.setDoInput(true);
			// hconn.setReadTimeout(30000);
			InputStream ins = hconn.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(ins));
			String s = null;
			String svalue = "";
			int i = 0, j = 0;
			while ((s = in.readLine()) != null) {
				if (!s.equals("OK")) {
					if (svalue.equals("")) {
						svalue = s;
					} else {
						svalue = svalue + "\r\n" + s;
					}
				} else {
					svalue = svalue + "\r\n" + s;
				}
			}
			in.close();

			if (svalue.indexOf("Exception") > 0) {
				iret.intvalue = -1;
				errString = svalue;
				svalue = "";
			} else {
				iret.intvalue = 0;
			}
			return svalue;
		} catch (Exception e) {
			e.printStackTrace();
			iret.intvalue = -1;
			errString = e.toString();
			// MessageBox(e.toString());
			return "";
		}
	}

}
