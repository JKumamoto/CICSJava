package ci1.cics.Calendar.Service;
/*
package ci1.cics.calendar.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ci1.cics.calendar.Model.Evento;
import ci1.cics.calendar.Repository.VsamRespository;

@ExtendWith(MockitoExtension.class)
public class EventoServiceTests {
	
	@InjectMocks
	private EventoService service;

	@Mock
	private VsamRespository vsam;

	public byte[] initEvento(){
		byte registro[] = new byte[340];
		for(int i=0; i<4; i++)
			registro[i] = 0;
		
		for(int i=4; i<340; i++)
			registro[i] = ' ';
		
		System.arraycopy("teste".getBytes(), 0, registro, 4, 80);
		System.arraycopy("01/01/0001 00:00".getBytes(), 0, registro, 84, 16);
		System.arraycopy("01/01/2001 00:00".getBytes(), 0, registro, 100, 16);
		System.arraycopy("0000".getBytes(), 0, registro, 116, 4);

		return registro;
	}

	@Test
	public void browse_basic(){
		when(vsam.Browse()).thenReturn(Arrays.asList(new Evento(initEvento())));
		JSONArray arr = service.readAll();
		System.out.println(arr.get(0).toString());
		
		//assertEquals(arr.get(0)., );
	}

}
 */