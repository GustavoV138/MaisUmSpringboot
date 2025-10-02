package com.general.mais_um_springboot.controller;

import com.general.mais_um_springboot.exceptions.RecursoNaoEncontradoException;
import com.general.mais_um_springboot.model.Produto;
import com.general.mais_um_springboot.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listarProdutos(){
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    // <?> Uma classe generica, pq no try pode retornar um produto ou no catch pode retornar StatusCode
    // O try/catch pode ser retirado pq criamos as exceptions personalizadas.
    // Com isso, o próprio Spring gerencia.
    public ResponseEntity<?> buscarProdutosPorId(@PathVariable Long id) {
            Produto produto = produtoService.listarPorId(id);
            return ResponseEntity.ok(produto);
    }

    @PostMapping
    public void salvarProduto(@RequestBody Produto produto){
        produtoService.salvarProduto(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RecursoNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
