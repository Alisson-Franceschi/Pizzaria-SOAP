package br.unipar.trabalho1B;

import jakarta.xml.ws.Endpoint;
import java.util.Timer;
import java.util.TimerTask;

public class pizzariaSoap {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/pizzaria", new pizzariaSoap());

        System.out.println("A pizzaria está aberta e pronta para entregas!");
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                atualizarStatusPedidos();
            }
        }, 0, 60000);
    }

    private static void atualizarStatusPedidos() {
        PedidoDAO pedidoDAO = new PedidoDAO();
        List<Pedido> pedidos = pedidoDAO.buscarTodos();

        for (Pedido pedido : pedidos) {
            String novoStatus = obterProximoStatus(pedido.getStatus());
            if (novoStatus != null) {
                pedido.setStatus(novoStatus);
                pedidoDAO.atualizar(pedido);
            }
        }
    }

    private static String obterProximoStatus(String statusAtual) {
        switch (statusAtual) {
            case "Recebido":
                return "Em Preparo";
            case "Em Preparo":
                return "Pronto para Retirada";
            case "Pronto para Retirada":
                return "Saiu para Entrega";
            case "Saiu para Entrega":
                return "Entregue";
            default:
                return null;
        }
    }
}

}
