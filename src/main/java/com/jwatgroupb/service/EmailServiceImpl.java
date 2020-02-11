/*
	@author:Quang Truong
	@date: Feb 4, 2020
*/

package com.jwatgroupb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jwatgroupb.entity.BillEntity;
import com.jwatgroupb.entity.UserEntity;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender emailSender;
	
	

	@Override
	public SimpleMailMessage mailConfirmPayment(BillEntity bill) {
		
		String email=bill.getEmail();
		int index= email.indexOf("@");
		String name=email.substring(0, index);
		String subject="JWAT-GROUP B Shop - Confirm Payment";
		StringBuilder text= new StringBuilder("");
		text.append("Hi "+name+",\n");
		text.append("- We have received your order.\n");
		text.append("- Products will be sent to you within one week.\n");
		text.append("- Please check the invoice at the URL: http://localhost:8080/bill/"+bill.getBillCode()+"\n" );
		text.append("- For further information. Please contact: 0123 456 789" );
		text.append("\n\nThank you for using our service,\n" );
		text.append("JWAT-GROUP B" );
		String textStr = text.toString();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(bill.getEmail());
		message.setSubject(subject);
		message.setText(textStr);
		return message;
	}



	@Override
	public void sendMailConfirmPayment(BillEntity bill) {
		emailSender.send(mailConfirmPayment(bill));
	}

}
