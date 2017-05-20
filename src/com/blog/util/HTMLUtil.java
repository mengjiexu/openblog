package com.blog.util;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

public class HTMLUtil {
	public static String ToHTML(String text)
	{
		//return Text.replace(Regex.Replace(Regex.Replace(Regex.Replace(text.replace("<p>" + text + "</p>", "\r\n", "</p><p>"), "\r", "</p><p>"), "\n", "<br />"), "\t", "    "), "  ", "  ");
		text = text.replace("<", "&lt;").replace(">", "&gt;").
				replace("\n", "<br/>").replace("\r\n", "<br/>")
				.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;").replace(" ", "&nbsp;").replace("\"", "&quot;");
		text = "<p>"+text+"</p>";
		return text;
	}
}
