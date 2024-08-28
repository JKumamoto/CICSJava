package ci1.cics.Calendar.Service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import ci1.cics.Calendar.Model.Evento;
import ci1.cics.Calendar.Repository.VsamRespository;

@Service("EventoService")
public class EventoService {

	VsamRespository vsam;

	public EventoService(){
		vsam = new VsamRespository();
	}

	public JSONObject readAll(){
		List<byte[]> registros = vsam.Browse();
		if(registros.isEmpty())
			return null;
		
		String jsonArray = "[";
		for(int i=0; i<registros.size(); i++){
			String json = new Evento(registros.get(i)).toString();
			jsonArray += json;
			if (i != (registros.size() - 1))
				jsonArray += ",";
				
		}
		jsonArray += "]";

		return new JSONObject(jsonArray);
	}

	public JSONObject read(String id){
		Evento ev = new Evento(vsam.Read(id.getBytes()));
		return new JSONObject(ev.toString());
	}

	public String create(Evento evento){
		return vsam.Add(evento.getId(), evento.getRegistro());
	}

	public String atualiza(Evento evento, String id){
		return vsam.Update(id.getBytes(), evento.getRegistro());
	}

	public String deleta(String id){
		return vsam.Delete(id.getBytes());
	}
	
}
