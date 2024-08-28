package ci1.cics.Calendar.Controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ci1.cics.Calendar.Model.Evento;
import ci1.cics.Calendar.Service.EventoService;

@RestController
public class MainController {
	
	@Autowired
	private EventoService eventoService;

	@GetMapping("/")
	public ResponseEntity<JSONObject> browse() {
		JSONObject resposta = eventoService.readAll();

		if (!resposta.isEmpty()){
			return ResponseEntity.ok(resposta);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<JSONObject> read(@PathVariable(name = "id") String id) {
		JSONObject resposta = eventoService.read(id);
		if(!resposta.isEmpty())
			return ResponseEntity.ok(resposta);

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/")
	public ResponseEntity<String> create(@RequestBody Evento evento) {
		String res = eventoService.create(evento);
		if(res.equals("OK"))
			return ResponseEntity.ok("Evento criado com sucesso!");

		return ResponseEntity.badRequest().body("Evento não criado, reposta: " + res);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody Evento evento, @PathVariable(name = "id") String id) {
		String res = eventoService.atualiza(evento, id);
		if(res.equals("OK"))
			return ResponseEntity.ok("Evento criado com sucesso!");

		return ResponseEntity.badRequest().body("Evento não criado, reposta: " + res);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable(name = "id") String id){
		String res = eventoService.deleta(id);
		if(res.equals("OK"))
			return ResponseEntity.ok("Evento criado com sucesso!");

		return ResponseEntity.badRequest().body("Evento não criado, reposta: " + res);
	}

}
