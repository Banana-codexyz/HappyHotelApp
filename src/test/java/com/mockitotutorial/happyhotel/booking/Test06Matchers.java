package com.mockitotutorial.happyhotel.booking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

class Test06Matchers {
	
	private BookingService bookingService;
	private PaymentService paymentServiceMock;
	private RoomService roomServiceMock;
	private BookingDAO bookingDAOMock;
	private MailSender mailSenderMock;
	
	@BeforeEach
	void setUp() {
		this.paymentServiceMock = mock(PaymentService.class);	
		this.roomServiceMock = mock(RoomService.class);
		this.bookingDAOMock = mock(BookingDAO.class);
		this.mailSenderMock = mock(MailSender.class);
		
		this.bookingService	= new BookingService(paymentServiceMock, roomServiceMock, bookingDAOMock, mailSenderMock);
	}
	
	@Test
	void should_ThrowException_When_NoRoomAvailable() {
		//given
		BookingRequest bookingRequest = new BookingRequest("1",LocalDate.of(2020,01,01),LocalDate.of(2020,01,05) , 2, true);
		when(this.paymentServiceMock.pay(any(), anyDouble())).thenThrow(BusinessException.class);
		
		//when
		Executable executable = () -> bookingService.makeBooking(bookingRequest);
		
		//then
		assertThrows(BusinessException.class,executable);
	}

}
