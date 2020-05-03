package com.coderby.myapp.util;

import java.io.FileWriter;
import java.io.IOException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class PropertyEnc {

	public static void writeProperties() {
		StandardPBEStringEncryptor enc = new StandardPBEStringEncryptor();
		enc.setPassword("djadudqja");
		FileWriter fw = null;
		try {
			fw = new FileWriter("src/main/resources/db/jdbc.properties");
			fw.write("jdbc.driverClassName=net.sf.log4jdbc.DriverSpy\n");
			fw.write("jdbc.url=ENC("+enc.encrypt("jdbc:log4jdbc:oracle:thin:@localhost:1521/xepdb1")+")\n");
			fw.write("jdbc.username=ENC("+enc.encrypt("hr")+")\n");
			fw.write("jdbc.password=ENC("+enc.encrypt("hr")+")\n");
			System.out.println("파일 작성 완료.");
		}catch(IOException e) {
			System.out.println("정상적으로 암호화가 되지 않았습니다.");
			System.out.println(e.getMessage());
		}finally {
			if(fw!=null) {try{fw.close();}catch(IOException e) {}}
		}
	}

}
