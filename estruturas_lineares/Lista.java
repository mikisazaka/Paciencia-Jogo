package estruturas_lineares;

import cartas.Carta;

public class Lista {
    private No<Carta> cabeca;
	private No<Carta> cauda;
	private int size;
	
	public Lista () {
		this.cabeca = null;
		this.cauda = null;
		this.size =  0;
	}
	
	public Carta get(int i) {
		No<Carta> atual = cabeca;
		
		if(cabeca == null) {
			return null;
		}
		
		if(i < 0 || i >= size) {
			return null;
		}
		
		for(int j = 0 ; j < i ; j++) {
			atual = atual.getProx();
		}
		return atual.getValor();
	}
	
	public void add(Carta valor) {
		No<Carta> novo = new No<>(valor);
		novo.setProx(cabeca);
		this.cabeca = novo;
		
		if (cauda == null) {
			this.cauda = novo;
		} 
		size++;
	}
	
	public void addFinal(Carta valor) {
		No<Carta> novo = new No<>(valor);
		
		if(cabeca == null) {
			novo.setProx(cabeca);
			this.cabeca = novo;
			this.cauda = novo;
		} else {
			cauda.setProx(novo);
			this.cauda = novo;
		}
		
		size++;
	}
	
	public boolean removerCabeca() {
		if (cabeca != null) {
			cabeca = cabeca.getProx();
			
			if(cabeca == null) {
				cauda = null;
			}
			
			size--;
			return true;
		} else {
			return false;
		}
	}

	public boolean removerElemento(int i) {
		No<Carta> aux = cabeca;
		No<Carta> anterior = cabeca;
		
		if(get(i) == null) {
			return false;
		}
		
		if (cabeca != null) {
			
			if (i == 0) {
				removerCabeca();
			} else {
				while (aux != null) {
					if (aux.getValor() == get(i)) {
						if (aux == cauda) {
							cauda = anterior;
						}
						anterior.setProx(aux.getProx());
						size--;
						break;
					}
					anterior = aux;
					aux = aux.getProx();
				}
			}
			return true;
			
		} else {
			return false;
		}
	}
	
	public void exibirLista() {
		No<Carta> aux = cabeca;
		
		while (aux != null) {
			System.out.print(aux.getValor() + " ");
			aux = aux.getProx();
		}
		
		System.out.println();
	}

	public boolean estaVazio() {
		if (this.size == 0) {
			return true;
		} else {
			return false;
		}
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
