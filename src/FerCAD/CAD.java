package FerCAD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CAD {
	public static List<String> getLetters() {
		List<String> v = new ArrayList<>();
		v.add("a");
		v.add("b");
		v.add("c");
		v.add("d");
		return v;
	}
	
	public static String decimalToBinario(int n) {
		if((n == 0) || (n == 1)) {
			return n + "";
		}
		if((n % 2) == 0) {
			return decimalToBinario(n / 2) + "0";
		} else {
			return decimalToBinario(n / 2) + "1";
		}
	}
	
	public static String setZeros(String bin) {
		int max = 4 - bin.length();
		for(int i = 0; i < max; i++) {
			bin = "0" + bin;
		}
		return bin;
	}
	
	public static boolean confere(String n1, String n2) {
		int flag = 0;
		for(int i = 0; i < n1.length(); i++) {
			if(n1.charAt(i) != n2.charAt(i)) {
				flag++;
			}
		}
		return (flag == 1);
	}
	
	public static String dontCares(String n1, String n2) {
		String temp = "";
		for(int i = 0; i < n1.length(); i++) {
			if(n1.charAt(i) != n2.charAt(i)) {
				temp = temp + "-";
			} else {
				temp = temp + n1.charAt(i);
			}
		}
		return temp;
	}
	
	public static boolean getIgualdade(List<String> n1, String n2) {
		for(int i = 0; i < n1.size(); i++) {
			if(n1.get(i).compareTo(n2) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public static Tabela reduce(List<String> minterms, List<String> valores) {
		List<String> newminterms = new ArrayList<>();
		List<String> newvalores = new ArrayList<>();
		int max = minterms.size();
		int[] checked = new int[max];
		for(int i = 0; i < max; i++) {
			for(int j = i; j < max; j++) {
				if(confere(minterms.get(i), minterms.get(j))) {
					checked[i] = 1;
					checked[j] = 1;
					if(!getIgualdade(newminterms, dontCares(minterms.get(i), minterms.get(j)))) {
	                     newminterms.add(dontCares(minterms.get(i),minterms.get(j)));
	                     String str = valores.get(i) + "," + valores.get(j);
	                     newvalores.add(str);
					}
				}
			}
		}
		for(int i = 0; i < max; i++) {
			if((checked[i] != 1) && (!getIgualdade(newminterms, minterms.get(i)))) {
				newminterms.add(minterms.get(i));
				newvalores.add(valores.get(i));
			}
		}
		Tabela t = new Tabela(newminterms, newvalores);
		return t;
	}
	
	
	public static String getValue(String n1) {
		String temp = "";
		List<String> vars = getLetters();
		for(int i = 0; i < n1.length(); i++) {
			if(n1.charAt(i) != '-') {
				if(n1.charAt(i) == '0') {
					temp = temp + vars.get(i) + "\'";
				} else {
					temp = temp + vars.get(i);
				}
			}
		}
		return temp;
	}
	
	public static boolean VectorsEqual(List<String> n1, List<String> n2) {
		if(n1.size() != n2.size()) {
			return false;
		}
		Collections.sort(n1);
		Collections.sort(n2);
		for(int i = 0; i < n1.size(); i++) {
			if(n1.get(i) != n2.get(i)) {
				return false;
			}
		}
		return true;
	}
	
	public static List<String> tabelaCobertura(List<String> s, List<String> v_associado, List<String> valores){
		String[][] tabela = new String[s.size()][valores.size()];
		List<String> reduzido = new ArrayList<>();
		String n = null;
		int count = 0;
		for(int i = 0; i < s.size(); i++) {
			String[] str = v_associado.get(i).split(",");
			for(int k = 0; k < str.length; k++) {
				for(int j = 0; j < valores.size(); j++) {
					if(str[k].equals(valores.get(j))) {
						tabela[i][j] = "X";
					}
				}
			}
		}
		/*
		for(int i = 0; i < s.size(); i++) {
			for(int j = 0; j < valores.size(); j++) {
				if(tabela[i][j] == null) {
					tabela[i][j] = "-";
				}
				System.out.print(tabela[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("________________\n\n");
		*/
		for(int i = 0; i < s.size(); i++) {
			for(int j = 0; j < valores.size(); j++) {
				if(tabela[i][j] == "X") {
					tabela[i][j] = valores.get(j);
				}
				if(tabela[i][j] == "-") {
					tabela[i][j] = "-";
				}
			}
		}
		/*
		for(int i = 0; i < s.size(); i++) {
			for(int j = 0; j < valores.size(); j++) {
				System.out.print(tabela[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("________________\n\n");
		*/
		;//ACHAR MINTERMOS ESSENCIAIS:
		for(int i = 0; i < s.size(); i++) {
			for(int j = 0; j < valores.size(); j++) {
				if(tabela[i][j] != "*") {
					if(tabela[i][j] != "-") {
						n = tabela[i][j];
						count = contador(n, tabela, s.size(), valores.size());
						if(count == 1) {
							reduzido.add(s.get(i));
							for(int k = 0; k < s.size(); k++) {
								tabela[k][j] = "*";
							}
							for(int k = 0; k < valores.size(); k++) {
								tabela[i][k] = "*";
							}
						}
					}
				}
			}
		}
		
		/*for(int i = 0; i < s.size(); i++) {
			for(int j = 0; j < valores.size(); j++) {
				System.out.print(tabela[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("________________\n\n");
		*/
		
		//BUSCA O MINTERMOS QUE COBRE MAIS VALORES
		
		while(completo(tabela, s.size(), valores.size())) {
			int linha = busca(tabela, s.size(), valores.size());
			//System.out.println(linha);
			reduzido.add(s.get(linha));
			//System.out.print("________________\n\n");
		
			for(int i = 0; i < valores.size(); i++) {
				if(tabela[linha][i] != "*") {
					if(tabela[linha][i] != "-") {
						for(int j = 0; j < s.size(); j++) {
							tabela[j][i] = "*";
						}
					}
				}
			}
		}
		
		/*for(int i = 0; i < s.size(); i++) {
			for(int j = 0; j < valores.size(); j++) {
				System.out.print(tabela[i][j] + " ");
			}
			System.out.print("\n");
		}
		*/
		return reduzido;
	}
	
	public static boolean completo(String[][] aux, int X, int Y) {
		for(int i = 0; i < X; i++) {
			for(int j = 0; j < Y; j++) {
				if(aux[i][j] != "*") {
					return true;
				}
			}
		}
		return false;
	}
	public static int busca(String[][] aux, int X, int Y) {
		int linha = 0;
		int atual = 0;
		int count;
		for(int i = 0; i < X; i++) {
			count = 0;
			for(int j = 0; j < Y; j++) {
				if(aux[i][j] != "*") {
					if(aux[i][j] != "-") {
						count++;
					}
				}
			}
			if(count > atual) {
				atual = count;
				linha = i;
			}
		}
		return linha;
	}
	public static int contador(String n, String[][] aux, int X, int Y) {
		int count = 0;
		for(int i = 0; i < X; i++) {
			for(int j = 0; j < Y; j++) {
				if(n == aux[i][j]) {
					count++;
				}
			}
		}
		return count;
	}
	
	@SuppressWarnings("resource")
	public static List<Integer> lerDados() {
		System.out.println("Digite os mintermos separados com vírgula: ");
		Scanner sc = new Scanner(System.in);
		String[] entrada = sc.nextLine().split(",");
		List<Integer> valores = new ArrayList<>();
		
		for(int i = 0; i < entrada.length; i++) {
			valores.add(Integer.parseInt(entrada[i]));
		}
		return valores;
	}
}
