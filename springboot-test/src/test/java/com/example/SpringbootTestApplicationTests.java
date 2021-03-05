package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@SpringBootTest
class SpringbootTestApplicationTests{

	@Autowired
	JavaMailSenderImpl mailSender;


	@Test
	void contextLoads() {

		//一个简单的邮件~
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setSubject("柔柔宝贝你好呀~");
		mailMessage.setText("这是我制作的小程序，只想表达我超级爱我的宝贝柔柔");

		mailMessage.setTo("1394473845@qq.com");
		mailMessage.setFrom("616316004@qq.com");

		mailSender.send(mailMessage);
	}


	@Test
	void contextLoads2() throws MessagingException {

		//一个复杂的邮件~
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		//组装
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

		//正文
		helper.setSubject("柔柔宝贝你好呀~plus");
		helper.setText("<p style='red'>这是我制作的小程序，只想表达我超级爱我的宝贝柔柔~</p>",true);

		//附件
		//helper.addAttachment("1.jpg",new File(C:\\Users\\Administrator\\Desktop\\1.jpg));

		helper.setTo("616316004@qq.com");
		helper.setFrom("616316004@qq.com");

		mailSender.send(mimeMessage);
	}

	/**
	 *
	 * @param html
	 * @param subject
	 * @param text
	 * @throws MessagingException
	 * @Author:波波
	 */
	public void sendMail(Boolean html,String subject,String text) throws MessagingException{
		//一个复杂的邮件~
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		//组装
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,html);

		//正文
		helper.setSubject(subject);
		helper.setText(text,true);

		//附件
		//helper.addAttachment("1.jpg",new File(C:\\Users\\Administrator\\Desktop\\1.jpg));

		helper.setTo("616316004@qq.com");
		helper.setFrom("616316004@qq.com");

		mailSender.send(mimeMessage);
	}

}
