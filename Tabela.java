package FerCAD;

import java.util.ArrayList;
import java.util.List;

public class Tabela {
	private List<String> minterms = new ArrayList<>();
	private List<String> valores = new ArrayList<>();
	
	public Tabela(List<String> minterms, List<String> valores) {
		this.minterms = minterms;
		this.valores = valores;
	}
	
	public List<String> getMinterms() {
		return minterms;
	}
	
	public List<String> getValores() {
		return valores;
	}
	
}
