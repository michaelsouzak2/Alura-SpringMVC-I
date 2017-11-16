package casadocodigo.loja.controllers;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import casadocodigo.loja.dao.ProdutoDAO;
import casadocodigo.loja.models.Produto;

@Controller//Controlador do Spring
public class HomeController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	@Autowired
	private RestTemplate restTemplate;

	//O metodo index atenderá as requisições que chegarem na raiz do nosso projeto ("/").
	@RequestMapping("/")
	/*A cada requisição, o Spring verifica se ouve alteração no parâmetro passado ao método alvo.
	 * Caso não tenha ocorrido, então o Spring retorna a informação que está na memória RAM.
	 * Utilizado quando a lista não muda. Útil para prover performance.
	 * Todo método cacheado precisa ter um nome.
	 * 
	 * Caso a informação seja atualizada, deve-se usar a anotação CacheEvict no método que atualiza a informação cacheada.
	 * Isso limpará este cache toda vez que o método com a anotação CacheEvict for chamado.
	 * 
	 * */
	@Cacheable(value="produtosHome")
	public ModelAndView index() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}
	
	@RequestMapping("/teste")
	public String teste() {
		return "teste";
	}
	
	@RequestMapping("/teste/detalhe/{id}")
	public Callable<ModelAndView> detalheTeste(@PathVariable("id") Integer id) {
		
		return () -> {

//			String uri = "http://localhost:8080/casadocodigo/produtos/detalhe/"+id+".json";
			String uri = "http://localhost:8080/casadocodigo/produtos/"+ id;
				
			Produto response = restTemplate.getForObject(uri, Produto.class);
				
			ModelAndView modelAndView = new ModelAndView("produtos/detalhe");
			modelAndView.addObject("produto", response);
				
			return modelAndView;
		};
			
	}
	
}
