package br.com.caelum.livraria.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Vendas;

@ManagedBean
@SessionScoped
public class VendaBean {

	public List<Vendas> getVenda(long sped) {
		List<Livro> livros = new DAO<Livro>(Livro.class).listaTodos();
		List<Vendas> vendas = new ArrayList<Vendas>();
		
		Random random = new Random(sped);
		for (Livro livro : livros) {
			
			Integer quantidade = random.nextInt(500);
			
			vendas.add(new Vendas(livro, quantidade));
		}
		
		return vendas;
	}
	
	public BarChartModel getVendasModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries vendaSerie = new ChartSeries();
        vendaSerie.setLabel("Vendas 2016");
        
        List<Vendas> vendas = getVenda(1234);
        
        for (Vendas venda : vendas) {
			vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
        
        ChartSeries vendaSerie2015 = new ChartSeries();
        vendaSerie2015.setLabel("Vendas 2016");
        
        vendas = getVenda(4321);
        
        for (Vendas venda : vendas) {
			vendaSerie2015.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
        
        model.addSeries(vendaSerie);
        model.addSeries(vendaSerie2015);
         
        return model;
    }
}
