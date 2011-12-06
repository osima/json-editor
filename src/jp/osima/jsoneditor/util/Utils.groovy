package jp.osima.jsoneditor.util

import groovy.ui.*

import net.sf.json.*
import net.sf.json.groovy.*
import groovy.json.*



public class Utils {

	private static String ENC='UTF-8'

	static String getText(File file){ return file.getText(ENC) }
	static void setText(File file,String text){
		file.setText(text,ENC)
	}

	static String pretty(String json){
		JSONObject jsonObj = JSONObject.fromObject( json )
		JsonOutput.prettyPrint(jsonObj.toString())
	}

}
