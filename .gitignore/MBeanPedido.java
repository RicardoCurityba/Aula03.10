package br.up.mbean;

import java.util.ArrayList;
import java.util.Collections;

import javax.faces.bean.ManagedBean;

import br.up.dao.PedidoDao;
import br.up.entidades.Pedido;

@ManagedBean(name="mBeanPedido")
public class MBeanPedido {
	
	private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();  
	private Pedido pedido;
	
	public void listar(Integer id) {
		ArrayList<Pedido> p = new PedidoDao().listar();
		for(Pedido pedido : p) {
			if(pedido.getUsuario().getId() == id) {
				pedidos.add(pedido);
			}
		}
		Collections.reverse(pedidos);
	}
	
	public void pedidoCar(Pedido p) {
		pedido = p;
	}
	
	public void buscar(Integer id) {
		pedido = new PedidoDao().buscar(id);
	}
	
	public String carregarPedido(Pedido p) {
		buscar(p.getId());
		return "pedido.jsf";
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	
}
