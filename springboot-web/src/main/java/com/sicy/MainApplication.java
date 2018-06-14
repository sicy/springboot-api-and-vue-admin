package com.sicy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 本地测试入口
 *
 * @author github.com/sicy
 * @version 1.0
 *          <p>
 *          <br/>
 *          <br/>修订人		修订时间			描述信息
 *          <br/>-----------------------------------------------------
 *          <br/>github.com/sicy		2018/5/8		初始创建
 */
@MapperScan("com.sicy.**.dao")
@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}
