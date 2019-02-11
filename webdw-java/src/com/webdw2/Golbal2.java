package com.webdw2;

import javax.swing.JComponent;
import javax.swing.JTextField;
import java.util.List;
import java.awt.Color;
import java.io.File;

/**
 * 全局变量定义
 * 
 * @author admin
 * 
 */
public class Golbal2 extends VBFunction2 {
	public static final String JWebDWInfo = "    Author: Liujunsong  \r\n"
			+ "    E_Mail: liujunsong@aliyun.com  \r\n"
			+ "    http://webdw.vicp.net  \r\n"
			+ "    Info:If You Can See SourceCode and find bug in it  \r\n"
			+ "    Please contract me.  \r\n";

	public void ReadMe() {
		System.out.println("全局变量定义");
		System.out.println(JWebDWInfo);
	}

//	public static WebDWSyntax GG_webdw = new WebDWSyntax();
//
//	public static WebDWSyntax GG_empty_webdw = new WebDWSyntax();

	public static String G_ServerURL = "http://localhost/webdw/retrieve";

	public static String G_Lang = "";

	public static LangDef G_LangDef = new LangDef();

	public static Transaction_Const G_Transaction_Const = new Transaction_Const();

	private static String WebDWSite1 = "http://localhost:8001/myproj/Table";

	private static String WebDWSite2 = "http://localhost/myproj/Table";
	
	public static boolean True = true;
	public static boolean False = false;

	/**
	 * 初始化全局变量
	 * 
	 */
	public static void InitGlobalVariable() {
		// G_ServerURL = WebDWSite;// '设置默认连接,连接到webdw.vicp.net

		// '对全局语言常量赋值
		G_LangDef.Lang_English = "english";
		G_LangDef.Lang_French = "french";
		G_LangDef.Lang_Japanese = "japanese";
		G_LangDef.Lang_SimpleChinese = "simplechinese";

		G_Lang = G_LangDef.Lang_SimpleChinese; // '默认为简体中文显示

		// '对全局数据掩码所用的数据类型来赋值
		// '数据掩码暂时不予支持
		// 'G_EditMaskDataType.EditMask_Date = 1
		// 'G_EditMaskDataType.EditMask_Time = 2
		// 'G_EditMaskDataType.EditMask_DateTime = 3
		// 'G_EditMaskDataType.EditMask_Decimal = 4
		// 'G_EditMaskDataType.EditMask_Numeric = 5
		// 'G_EditMaskDataType.EditMask_String = 6

		// '初始化全局性常量定义的数值
		G_Transaction_Const.Trans_Oper_Query = "1";
		G_Transaction_Const.Trans_Oper_Exec = "2";
		G_Transaction_Const.Trans_Oper_TableList = "3";
		G_Transaction_Const.Trans_Oper_ColumnList = "4";

		G_Transaction_Const.Trans_BeginTrans = "begintrans";
		G_Transaction_Const.Trans_AddCommand = "addcommand";
		G_Transaction_Const.Trans_Commit = "commit";
		G_Transaction_Const.Trans_Rollback = "rollback";

		G_Transaction_Const.Trans_GetDWDefine = "getdwdefine";// '得到数据窗口的定义

	}

	public Golbal2() {
		// 实例化时对全局变量来赋值
		InitGlobalVariable();
	}

	// '从一个targetControls容器中，根据给定控件名称来检索控件
	// '如果控件不存在，则返回Nothing
	public static JComponent GF_GetObjectByName(List targetControls,
			String objName) {
		JComponent vobj = null;
		for (int i = 0; i < targetControls.size(); i++) {
			vobj = (JComponent) targetControls.get(i);
			if (vobj == null) {
				continue;
			}
			if (vobj.getName() == null) {
				continue;
			}
			if (UCase(vobj.getName()).equals(UCase(objName))) {
				return vobj;
			}
		}
		return null;
	}

	public long GF_GetVBColor(long pbColor, long defColor) {
		long iret = 0;
		long SYSCOLOR = 16777215;// '最大颜色值,256 * 256 * 256 - 1

		if (pbColor <= SYSCOLOR) {
			return pbColor;
		}

		iret = defColor;// '设置默认颜色
		if (pbColor == 1090519039)
			iret = RGB(255, 255, 255); // '如果是windows默认背景色
		if (pbColor == 276856960)
			iret = RGB(125, 125, 125);// ' 如果是应用工作区默认背景色
		if (pbColor == 81324524)
			iret = RGB(125, 125, 125); // ' 如果是按钮表色
		if (pbColor == 33554592)
			iret = RGB(0, 0, 0); // 'window文本默认颜色

		// '下面是几种固定定义的颜色转换
		if (pbColor == 536870912)
			iret = RGB(255, 255, 255); // '白色

		return iret;

	}

	public double GF_GetConvertRate(List targetControls) {
		double convertRate;
		JComponent TextConvertRate;

		TextConvertRate = GF_GetObjectByName(targetControls, "TextConvertRate");

		if (TextConvertRate == null) {
			return 0.5;
		}
		JTextField jt = (JTextField) TextConvertRate;
		convertRate = Double.parseDouble(jt.getText()); // '获取设置值
		if (convertRate <= 0.1 || convertRate >= 10) {
			convertRate = 0.5;
		}

		return convertRate;
	}

//	public String GF_RetrieveBySyntax(String dwSyntax) {
//		MyInt iret = new MyInt(0);
//		CWebDW temp_webdw = new CWebDW();
//		CWebDWTransaction temp_sqlca = new CWebDWTransaction();
//		String str_retrieve = "";
//		String sdata = "";
//
//		if (temp_webdw.Create(dwSyntax) == -1) {// Then '解析失败，返回空字符串
//			return "";
//		}
//		log(dwSyntax);
//		log("" + GG_webdw.getColumnNumber());
//		str_retrieve = temp_webdw.GetRetrieveSQL();// '得到检索用的SQL语句
//		log(str_retrieve);
//		if (str_retrieve.length() == 0) {
//			return "";
//		}
//
//		//    
//		// 'temp_sqlca.opertype = 1
//		// 'temp_sqlca.beginPos = 0
//		// 'temp_sqlca.readNum = 1000
//		temp_sqlca.Eval("Setcommand(" + str_retrieve + ")", iret);
//
//		sdata = temp_sqlca.ExecuteSelect(iret);// '执行sql,得到数据结果
//
//		if (iret.intvalue == -1) {// Then
//			return "";
//		}
//
//		return sdata;// '返回数据
//	}

	public long GF_GetDBlength(String sdata) {
		int i = 0;
		long ilen = 0;
		String stemp = "";
		ilen = 0;
		for (i = 1; i <= Len(sdata); i++) {
			stemp = Mid(sdata, i, 1);
			if (Asc(stemp) < 128 && Asc(stemp) > 0) {
				ilen = ilen + 1;
			} else {
				ilen = ilen + 2;
			}

		}
		return ilen;
	}

	public String GF_IF(boolean ifClause, String YesValue, String NoValue) {
		if (ifClause) {
			return YesValue;
		} else {
			return NoValue;
		}
	}

	public int GF_IF_Long(boolean ifClause, int YesValue, int NoValue) {
		if (ifClause) {
			return YesValue;
		} else {
			return NoValue;
		}
	}

	public int GF_GetAlignType(int intype) {
		if (intype == 0) {
			return JTextField.LEFT;
		}
		if (intype == 1) {
			return JTextField.RIGHT;
		}
		if (intype == 2) {
			return JTextField.CENTER;
		}
		return JTextField.CENTER;
	}

	/**
	 * 将VB的颜色转换成Java的颜色
	 * 
	 * @param vbcolor
	 * @return
	 */
	public Color GF_GetJavaColor(int vbcolor) {
		if (vbcolor == 255) {
			return Color.RED;
		}
		return new Color(0, 0, 0);
	}

	public void log(String s) {
		System.out.println(s);
	}
}

/**
 * 语言类型定义,用于进行多语言支持
 * 
 * @author liujunsong
 * 
 */
class LangDef {
	public String Lang_SimpleChinese = "";// '简体中文定义

	public String Lang_English = ""; // '英文定义

	public String Lang_French = ""; // '法文定义

	public String Lang_Japanese = ""; // '日文定义

}

class Transaction_Const {
	public String Trans_Oper_Query = ""; // '查询操作 //1

	public String Trans_Oper_Exec = ""; // '执行操作 //2

	public String Trans_Oper_TableList = ""; // '数据表列表操作 //3

	public String Trans_Oper_ColumnList = ""; // '数据列列表操作 //4

	// '下面是事务相关方法
	public String Trans_BeginTrans = ""; // '启动一个事务

	public String Trans_AddCommand = ""; // '增加命令

	public String Trans_Commit = ""; // '提交事务

	public String Trans_Rollback = ""; // '回滚（取消）事务

	// '下面是获得数据窗口定义的方法
	public String Trans_GetDWDefine = ""; // '从后台检索数据窗口定义文件

}