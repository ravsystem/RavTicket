package br.com.connection;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.model.Agente;
import br.com.model.Categoria;
import br.com.model.Cliente;
import br.com.model.ItemCategoria;
import br.com.model.SubCategoria;

public class Conexao {
	
	public static EntityManagerFactory emf;
	
	public static void iniciarFabrica() {
		emf = Persistence.createEntityManagerFactory("ravsystem"); 
	}
	
	public static void guardar(Object o) {
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		em.persist(o);
		et.commit();
		em.close();
	}
	
	public static void alterar(Object o) {
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		em.merge(o);
		et.commit();
		em.close();
         
	}
	
	public static void removeCliente(Cliente cliente) {
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
        cliente = em.find(Cliente.class, cliente.getId());
        em.remove(cliente);
        em.getTransaction().commit();
		
	}
	
	public static void removeAgente(Agente agente) {
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
        agente = em.find(Agente.class, agente.getId());
        em.remove(agente);
        em.getTransaction().commit();
		
	}
	
	public static void removeCategoria(Categoria categoria) {
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		categoria = em.find(Categoria.class, categoria.getId());
        em.remove(categoria);
        em.getTransaction().commit();
		
	}
	
	public static void removeSubCategoria(SubCategoria subcategoria) {
		
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		subcategoria = em.find(SubCategoria.class, subcategoria.getIn());
        em.remove(subcategoria);
        em.getTransaction().commit();
		
	}

	public static void removeItemCategoria(ItemCategoria itemcategoria) {
	
	EntityManager em = emf.createEntityManager();
	
	em.getTransaction().begin();
	itemcategoria = em.find(ItemCategoria.class, itemcategoria.getId());
    em.remove(itemcategoria);
    em.getTransaction().commit();
	
	}
	
	public static List<Agente> listarAgent() {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Agente");
		
		List<Agente> listaAgentes = query.getResultList();
        return listaAgentes;
	}
	
	public static List<Cliente> listarClient() {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Cliente");
		
		List<Cliente> listaClientes = query.getResultList();
        return listaClientes;
	}
	
	public static List<Categoria> listarCateg() {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Categoria");
		
		List<Categoria> listaCategs = query.getResultList();
        return listaCategs;
	}
	
	public static List<SubCategoria> listarSubCateg() {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM SubCategoria");
		
		List<SubCategoria> listarSubCategs = query.getResultList();
        return listarSubCategs;
	}

	public static List<ItemCategoria> listarItemCateg() {
	
	EntityManager em = emf.createEntityManager();
	Query query = em.createQuery("FROM ItemCategoria");
	
	List<ItemCategoria> listarItemCategs = query.getResultList();
    return listarItemCategs;
	}

	public static boolean validaAgente(String operador, String senha) {
		
		boolean resultado = false;
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Agente o where o.nome = :nome and o.senha = :senha");
		query.setParameter("nome", operador);
		query.setParameter("senha", senha);
		
		List<Agente> listaAgentes = query.getResultList();
		
		if (listaAgentes.size() > 0) resultado = true;	
        
		return resultado;
	}
	
	public static List<SubCategoria> categoriaInSubcategoria(String categ) {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM SubCategoria o where o.Categoria = :categ");
		query.setParameter("categ", categ);
		
		@SuppressWarnings("unchecked")
		List<SubCategoria> subcategs = query.getResultList();
        
		return subcategs;
	}
	
	public static List<ItemCategoria> subCategoriaInItemcategoria(String categ) {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM ItemCategoria o where o.Categoria = :categ");
		query.setParameter("categ", categ);
		
		@SuppressWarnings("unchecked")
		List<ItemCategoria> itemcategs = query.getResultList();
        
		return itemcategs;
	}
	
	public static boolean tipoAgente(String nome) {
		
		boolean resultado = false;
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Agente o where o.nome = :nome and o.tipo = 'ADM' ");
		query.setParameter("nome", nome);
		
		List<Agente> listaAgentes = query.getResultList();
		
		if(listaAgentes.size() > 0) resultado = true;
		
		return resultado;
	}
	
	public static Agente selecionaAgente(Long id) {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Agente p where p.id = :id");
		query.setParameter("id", id);
		
		List<Agente> agentes = query.getResultList();
		Agente agente = new Agente();;
		
		for(int i = 0; i < agentes.size(); i++) {
			
			agente.setId(agentes.get(i).getId());
			agente.setNome(agentes.get(i).getNome());
			agente.setSenha(agentes.get(i).getSenha());
			agente.setTipo(agentes.get(i).getTipo());

		}
		
		return agente;
		
	}
		
	public static Cliente selecionaCliente(Long id) {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Cliente p where p.id = :id");
		query.setParameter("id", id);
		
		List<Cliente> clientes = query.getResultList();
		Cliente cliente = new Cliente();;
		
		for(int i = 0; i < clientes.size(); i++) {
			
			cliente.setId(clientes.get(i).getId());
			cliente.setFantasia(clientes.get(i).getFantasia());
			cliente.setRazao(clientes.get(i).getRazao());
			cliente.setTelefone(clientes.get(i).getTelefone());
			cliente.setEmail(clientes.get(i).getEmail());
			cliente.setData_cadastro(clientes.get(i).getData_cadastro());
			cliente.setCpf_cnpj(clientes.get(i).getCpf_cnpj());

		}
		
		return cliente;
		
	}
	
	public static Categoria selecionaCategoria(Long id) {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM Categoria p where p.id = :id");
		query.setParameter("id", id);
		
		List<Categoria> categorias = query.getResultList();
		Categoria categoria = new Categoria();;
		
		for(int i = 0; i < categorias.size(); i++) {
			
			categoria.setId(categorias.get(i).getId());
			categoria.setDescr(categorias.get(i).getDescr());

		}
		
		return categoria;
		
	}
	
	public static SubCategoria selecionaSubCategoria(Long id) {
		
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("FROM SubCategoria p where p.id = :id");
		query.setParameter("id", id);
		
		List<SubCategoria> subCategorias = query.getResultList();
		SubCategoria subCategoria = new SubCategoria();;
		
		for(int i = 0; i < subCategorias.size(); i++) {
			
			subCategoria.setIn(subCategorias.get(i).getIn());
			subCategoria.setDescr(subCategorias.get(i).getDescr());
			subCategoria.setCategoria(subCategorias.get(i).getCategoria());

		}
		
		return subCategoria;
		
	}

	public static ItemCategoria selecionaItemCategoria(Long id) {
	
	EntityManager em = emf.createEntityManager();
	Query query = em.createQuery("FROM ItemCategoria p where p.id = :id");
	query.setParameter("id", id);
	
	List<ItemCategoria> itemcategorias = query.getResultList();
	ItemCategoria itemcategoria = new ItemCategoria();;
	
	for(int i = 0; i < itemcategorias.size(); i++) {
		
		itemcategoria.setId(itemcategorias.get(i).getId());
		itemcategoria.setNome(itemcategorias.get(i).getNome());
		itemcategoria.setCategoria(itemcategorias.get(i).getCategoria());
		itemcategoria.setUrgencia(itemcategorias.get(i).getUrgencia());
	}
	
	return itemcategoria;
	
	}
}
