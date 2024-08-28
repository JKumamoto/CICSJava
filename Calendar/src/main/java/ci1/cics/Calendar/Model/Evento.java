package ci1.cics.Calendar.Model;

import java.io.UnsupportedEncodingException;

public class Evento {
	
	private static final String CCSID = System.getProperty("com.ibm.cics.jvmserver.local.ccsid");
	private static final int CHAVE=4;
	private static final int TITULO=80;
	private static final int START=16;
	private static final int END=16;
	private static final int TIPO=4;
	private static final int DESCRICAO=220;
	private byte registro[];
	
	public Evento(byte registro[]) {
		super();
		this.registro = registro;
	}

	public byte[] getRegistro() {
		return registro;
	}

	public void setRegistro(byte[] registro) {
		this.registro = registro;
	}

	public byte[] getId() {
		byte key[] = new byte[CHAVE];
		System.arraycopy(registro, 0, key, 0, CHAVE);
		return key;
	}

	public void setId(String id) {
		System.arraycopy(id.getBytes(), 0, this.registro, 0, CHAVE);
	}

	public byte[] getTitle() {
		byte campo[] = new byte[TITULO];
		System.arraycopy(registro, CHAVE, campo, 0, TITULO);
		return campo;
	}

	public byte[] getStart() {
		byte campo[] = new byte[START];
		System.arraycopy(registro, CHAVE+TITULO, campo, 0, START);
		return campo;
	}

	public byte[] getEnd() {
		byte campo[] = new byte[END];
		System.arraycopy(registro, CHAVE+TITULO+START, campo, 0, END);
		return campo;
	}

	public byte[] getTipo() {
		byte campo[] = new byte[TIPO];
		System.arraycopy(registro, CHAVE+TITULO+START+END, campo, 0, TIPO);
		return campo;
	}

	public byte[] getDescription() {
		byte campo[] = new byte[DESCRICAO];
		System.arraycopy(registro, CHAVE+TITULO+START+END+TIPO, campo, 0, DESCRICAO);
		return campo;
	}

	// retorno como json no formato:
	/* {
			"id": "0000",
			"title": "teste",
			"start": "01/01/0001 00:00",
			"end": "01/01/0001 00:00",
			"type": "0000",
			"description": "teste teste"
		}
		 */
	@Override
	public String toString(){
		try{
			return "{\"id\":\"" + new String(this.getId(), CCSID) + "\",\"title\":\"" + new String(this.getTitle(), CCSID) +
					"\",\"start\":\"" + new String(this.getStart(), CCSID) + "\",\"end\":\"" + new String(this.getEnd(), CCSID) +
					"\",\"type\":\"" + new String(this.getTipo(), CCSID) + "\",\"description\":\"" + new String(this.getDescription(), CCSID) + "\"}";
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return "erro no encoding " + CCSID;
		}
	} 	
}
