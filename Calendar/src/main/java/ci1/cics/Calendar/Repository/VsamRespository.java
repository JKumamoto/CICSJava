package ci1.cics.Calendar.Repository;

import java.util.ArrayList;
import java.util.List;

import com.ibm.cics.server.CicsConditionException;
import com.ibm.cics.server.DuplicateRecordException;
import com.ibm.cics.server.EndOfFileException;
import com.ibm.cics.server.FileDisabledException;
import com.ibm.cics.server.FileNotFoundException;
import com.ibm.cics.server.InvalidRequestException;
import com.ibm.cics.server.KSDS;
import com.ibm.cics.server.KeyHolder;
import com.ibm.cics.server.KeyedFileBrowse;
import com.ibm.cics.server.RecordHolder;
import com.ibm.cics.server.RecordNotFoundException;
import com.ibm.cics.server.SearchType;

public class VsamRespository {
	
	private static final String FILE_NAME = "CALENDAR";
	private static final int CHAVE = 4;
	private KSDS FILE;

	public VsamRespository(){
	}

	public String Add(byte[] chave, byte[] registro){
		try{
			this.FILE = new KSDS();
			this.FILE.setName(FILE_NAME);
			this.FILE.write(chave, registro);
		}catch(FileDisabledException e){
			return FILE_NAME + " Disabled";
		}catch(FileNotFoundException e){
			return FILE_NAME + " Not Found";
		}catch(DuplicateRecordException e){
			return "Registro duplicado " + chave;
		}catch(InvalidRequestException e){
			if(e.getRESP2() == 20)
				return "Add não permitido no " + FILE_NAME;

			return "Resp 1: " + e.getRESP() + " Resp2: " + e.getRESP2();
		}catch(CicsConditionException e){
			return "Resp 1: " + e.getRESP() + " Resp2: " + e.getRESP2();
		}
		return "OK";
	}

	public List<byte[]> Browse(){
		List<byte[]> rec = new ArrayList<byte[]>();
		this.FILE = new KSDS();
		this.FILE.setName(FILE_NAME);

		RecordHolder rh = new RecordHolder();
		KeyHolder kh = new KeyHolder();
		byte key[] = new byte[CHAVE];
		for(int i=0; i<CHAVE; i++)
			key[i] = 0;

		try{
			KeyedFileBrowse browser = this.FILE.startBrowse(key, SearchType.GTEQ);
			while (true) {
				browser.next(rh, kh);
				rec.add(rh.getValue());
			}
		}catch(RecordNotFoundException e){
			// Browse initial com falha
		}catch(EndOfFileException e){
			// Final normal do loop
		}catch(FileDisabledException e){
			System.err.println(FILE_NAME + " Disabled");
		}catch(FileNotFoundException e){
			System.err.println(FILE_NAME + " Not Found");
		}catch(CicsConditionException e){
			System.err.println("Resp 1: " + e.getRESP() + " Resp2: " + e.getRESP2());
		}

		return rec;
	}

	public String Delete(byte[] chave){       
		this.FILE = new KSDS();
		this.FILE.setName(FILE_NAME);
        try {
            RecordHolder rh = new RecordHolder();

            // Read the record at the specified key and lock
            this.FILE.readForUpdate(chave, SearchType.EQUAL, rh);
            
			this.FILE.delete();
		}catch(FileDisabledException e){
			return FILE_NAME + " Disabled";
		}catch(FileNotFoundException e){
			return "Arquivo " + FILE_NAME + " Not Found";
		}catch(RecordNotFoundException e){
			return "Chave " + chave + " Not Found";
		}catch(InvalidRequestException e){
			if(e.getRESP2() == 20)
				return "READ ou UPDATE não permitido no " + FILE_NAME;

			return "Resp 1: " + e.getRESP() + " Resp2: " + e.getRESP2();
		}catch(CicsConditionException e){
			return "Resp 1: " + e.getRESP() + " Resp2: " + e.getRESP2();
		}

		return "OK";
    }

	public byte[] Read(byte key[]){
		byte[] record = null;
		this.FILE = new KSDS();
		this.FILE.setName(FILE_NAME);

		try{
			RecordHolder rh = new RecordHolder();

			this.FILE.read(key, SearchType.EQUAL, rh);
			record = rh.getValue();
		}catch(FileDisabledException e){
			System.err.println(FILE_NAME + " Disabled");
		}catch(FileNotFoundException e){
			System.err.println("Arquivo " + FILE_NAME + " Not Found");
		}catch(RecordNotFoundException e){
			System.err.println("Chave " + key + " Not Found");
		}catch(InvalidRequestException e){
			if(e.getRESP2() == 20)
				System.err.println("Read não permitido no " + FILE_NAME);

			System.err.println("Resp 1: " + e.getRESP() + " Resp2: " + e.getRESP2());
		}catch(CicsConditionException e){
			System.err.println("Resp 1: " + e.getRESP() + " Resp2: " + e.getRESP2());
		}
		return record;
	}

	public String Update(byte[] key, byte[] registro){
		this.FILE = new KSDS();
		this.FILE.setName(FILE_NAME);
        try {
            // Holder object to receive the data
            RecordHolder rh = new RecordHolder();

            // Read the record at the specified key and lock
            this.FILE.readForUpdate(key, SearchType.EQUAL, rh);
            
            // Rewrite the record with the updated data
            this.FILE.rewrite( registro );
		}catch(FileDisabledException e){
			return FILE_NAME + " Disabled";
		}catch(FileNotFoundException e){
			return "Arquivo " + FILE_NAME + " Not Found";
		}catch(RecordNotFoundException e){
			return "Chave " + key + " Not Found";
		}catch(InvalidRequestException e){
			if(e.getRESP2() == 20)
				return "READ, UPDATE ou DELETE não permitido no " + FILE_NAME;

			return "Resp 1: " + e.getRESP() + " Resp2: " + e.getRESP2();
		}catch(CicsConditionException e){
			return "Resp 1: " + e.getRESP() + " Resp2: " + e.getRESP2();
		}

		return "OK";
    }

}
