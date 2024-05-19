package br.com.alura.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.alura.model.DadosEpisodios;
import br.com.alura.model.DadosSerie;
import br.com.alura.model.DadosTemporada;
import br.com.alura.service.ConsumoApi;
import br.com.alura.service.ConverteDados;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private ConsumoApi consumo = new ConsumoApi();
        private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=e7b642bc";

    public void exibeMenu(){
        System.out.println("Digite o nome da série para busca: ");
        var nomeSerie = leitura.nextLine();
    	var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i<=dados.totalTemporadas(); i++) {
		    json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") +"&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);

        //for(int i = 0; i < dados.totalTemporadas(); i++){
        //    List<DadosEpisodios> episodiosTemporada = temporadas.get(i).episodios();
        //   for(int j = 0; j< episodiosTemporada.size(); j++){
        //       System.out.println(episodiosTemporada.get(j).titulo());
        //   }
        //}

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

    }
}