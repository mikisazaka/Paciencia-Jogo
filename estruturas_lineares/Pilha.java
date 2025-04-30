package estruturas_lineares;

import cartas.Carta;

public class Pilha {
    private No<Carta> cabeca;
	private int size;
	
	public Pilha () {
		this.cabeca = null;
		this.size =  0;
	}

	public No<Carta> getCabeca() {
		return cabeca;
	}

	public void setCabeca(No<Carta> cabeca) {
		this.cabeca = cabeca;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public void push(Carta valor) {
		No<Carta> novo = new No<>(valor);
		novo.setProx(cabeca);
		cabeca = novo;
		size++;
	}
	
	public boolean pop() {
		if (cabeca == null) {
			return false;
		} else {
			cabeca = cabeca.getProx();
			size--;
			return true;
		}
	}
	
	public void exibir() {
		No<Carta> aux = cabeca;

		while (aux != null) {
			System.out.print(aux.getValor() + " ");
			aux = aux.getProx();
		}

		System.out.println();
	}
}
