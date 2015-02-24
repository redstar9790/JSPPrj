package com.newlecture.jspprj;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class NewlecRequestListener implements ServletRequestListener{
	
	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		System.out.println("��û ���� Remote IP=" + event.getServletRequest().getRemoteAddr());
	}

	@Override
	public void requestInitialized(ServletRequestEvent event) {
		System.out.println("��û �ʱ�ȭ Remote IP=" + event.getServletRequest().getRemoteAddr());
	}
}
