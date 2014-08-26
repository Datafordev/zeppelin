package com.nflabs.zeppelin.repl;

import java.io.Serializable;

public class ReplResult implements Serializable{
	public static enum Code {
		SUCCESS,
		INCOMPLETE,
		ERROR
	}
	
	public static enum Type {
		TEXT,
		HTML,
		TABLE
	}
	
	Code code;
	Type type;
	String msg;
	
	public ReplResult(Code code) {
		this.code = code;
		this.msg = null;
		this.type = Type.TEXT;
	}
	
	public ReplResult(Code code, String msg) {
		this.code = code;
		this.msg = getData(msg);
		this.type = getType(msg);
	}
	


	/**
	 * Magic is like
	 * %html
	 * %text
	 * ...
	 * @param msg
	 * @return
	 */
	private String getData(String msg){
		if(msg==null) return null;
		if(msg.startsWith("%html ")){
			int magicLength = "%html ".length();
			if(msg.length()>magicLength){
				return msg.substring(magicLength);
			} else {
				return "";
			}
		} else if(msg.startsWith("%text ")){
			int magicLength = "%text ".length();
			if(msg.length()>magicLength){
				return msg.substring(magicLength);
			} else {
				return "";
			}
		} else if(msg.startsWith("%table ")){
			int magicLength = "%table ".length();
			if(msg.length()>magicLength){
				return msg.substring(magicLength);
			} else {
				return "";
			}			
		} else {
			return msg;
		}
	}
	
	
	private Type getType(String msg){
		if(msg==null) return Type.TEXT;
		if(msg.startsWith("%html ")){
			return Type.HTML;
		} else if(msg.startsWith("%text ")){
			return Type.TEXT;
		} else if(msg.startsWith("%table ")){
			return Type.TABLE;
		} else {
			return Type.TEXT;
		}		
	}
	
	public Code code(){
		return code;
	}
	
	public String message(){
		return msg;
	}
	
	public Type type(){
		return type;
	}
	
	public ReplResult type(Type type){
		this.type = type;
		return this;
	}
}
