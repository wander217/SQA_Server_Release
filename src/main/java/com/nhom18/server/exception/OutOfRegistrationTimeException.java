package com.nhom18.server.exception;

import java.time.LocalDateTime;

public class OutOfRegistrationTimeException extends Exception{
	private static final long serialVersionUID = -1142243242257692013L;

	public OutOfRegistrationTimeException(LocalDateTime start, LocalDateTime end) {
        super("Thời gian đăng kí tín bắt đầu từ "+start.getHour()
                +":"+start.getMinute()+":"+start.getSecond()
                +" "+start.getDayOfMonth()+"/"+start.getMonthValue()+"/"+start.getYear()
                +" đến "+end.getHour()+":"+end.getMinute()+":"+end.getSecond()
                +" "+end.getDayOfMonth()+"/"+end.getMonthValue()+"/"+end.getYear());
    }
}
