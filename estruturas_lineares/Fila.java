package estruturas_lineares;

import cartas.Carta;

public class Fila {
    private No<Carta> cabeca;
	private No<Carta> cauda;
	private int size;
	
	public Fila () {
		this.cabeca = null;
		this.cauda = null;
		this.size =  0;
	}
	
	public void enqueue(Carta valor) {
		No<Carta> novo = new No<>(valor);

		if (cabeca == null) {
			novo.setProx(cabeca);
			this.cabeca = novo;
			this.cauda = novo;
		} else {
			cauda.setProx(novo);
			this.cauda = novo;
		}

		size++;
	}
	
	public Carta dequeue() {
		if (cabeca != null) {
			Carta aux = cabeca.getValor();
			cabeca = cabeca.getProx();
			
			if(cabeca == null) {
				cauda = null;
			}
			
			size--;
			return aux;
		} else {
			return null;
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

	public No<Carta> getCabeca() {
		return cabeca;
	}

	public void setCabeca(No<Carta> cabeca) {
		this.cabeca = cabeca;
	}

	public No<Carta> getCauda() {
		return cauda;
	}

	public void setCauda(No<Carta> cauda) {
		this.cauda = cauda;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
