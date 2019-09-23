package FerCAD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] arg) {
		/** 
		 * SEGUNDA PARTE + ou - */
		List<String> s = new ArrayList<>();
		List<String> v_associado = new ArrayList<>();
		List<String> valores = new ArrayList<>();
		List<String> reduzido = new ArrayList<>();
		s.add("000-");
		s.add("0-00");
		s.add("01-0");
		s.add("0110");
		s.add("-111");
		s.add("1-11");
		v_associado.add("0,1");
		v_associado.add("0,4");
		v_associado.add("4,6");
		v_associado.add("6,7");
		v_associado.add("7,15");
		v_associado.add("11,15");
		valores.add("0");
		valores.add("1");
		valores.add("4");
		valores.add("6");
		valores.add("7");
		valores.add("11");
		valores.add("15");
		reduzido = CAD.tabelaCobertura(s, v_associado, valores);
		for(int i = 0; i < reduzido.size(); i++) {
			System.out.print(reduzido.get(i) + " ");
		}
	}
}
 		
		
		/**
 		 * PRIMEIRA PARTE + ou -
 		 *
		
		List<String> minterms = new ArrayList<>();
 		List<Integer> entrada = CAD.lerDados();
 		List<String> valores = new ArrayList<>();
 		Tabela t;
		for(int i = 0; i < entrada.size(); i++) {
			minterms.add(CAD.setZeros(CAD.decimalToBinario(entrada.get(i))));
		}
		/*
		System.out.println("-------------------------------------------");
		System.out.println("Mintermos em binário: ");
		for(int i = 0; i < minterms.size(); i++) {
			System.out.println(minterms.get(i));
		}
		*
		Collections.sort(minterms);
		Collections.sort(entrada);
		/*System.out.println("-------------------------------------------");
		System.out.println("Mintermos em binário (organizado): ");
		for(int i = 0; i < minterms.size(); i++) {
			System.out.println(entrada.get(i) + "\t" + minterms.get(i));
		}
		*
		for(int i = 0; i < entrada.size(); i++) {
			valores.add(Integer.toString(entrada.get(i)));
		}
		//System.out.println("-------------------------------------------");
 		//System.out.println("Aplicando don't cares: ");
		do {
			t = CAD.reduce(minterms, valores);
			minterms = t.getMinterms();
			valores = t.getValores();
			//Collections.sort(minterms);
		} while(!CAD.VectorsEqual(minterms, CAD.reduce(minterms, valores).getMinterms()));
		
		/*for(int i = 0; i < minterms.size(); i++) {
			System.out.print(valores.get(i) + ":\t");
			System.out.println(minterms.get(i));
		}
		*
		System.out.println("-------------------------------------------");
		System.out.println("Equação reduzida (Primeira Parte): ");
		for(int i = 0; i < minterms.size() - 1; i++) {
			System.out.print(CAD.getValue(minterms.get(i)) + " + ");
		}
		System.out.println(CAD.getValue(minterms.get(minterms.size() - 1)));
		System.out.println("-------------------------------------------");
		
		//Entrada para valores
		String[] str; 
		List<String> entrada2 = new ArrayList<>();
		for(int i = 0; i < valores.size(); i++) {
			str = valores.get(i).split(",");
			for(int j = 0; j < str.length; j++) {
				if(!CAD.getIgualdade(entrada2, str[j])) {
					entrada2.add(str[j]);
				}
			}
		}
		
		List<String> reduzido;
		reduzido = CAD.tabelaCobertura(minterms, valores, entrada2);
		System.out.println("Equação reduzida (Segunda Parte): ");
		for(int i = 0; i < reduzido.size() - 1; i++) {
			System.out.print(CAD.getValue(reduzido.get(i)) + " + ");
		}
		System.out.println(CAD.getValue(reduzido.get(reduzido.size() - 1)));
		System.out.println("-------------------------------------------");
		
	}
} */
