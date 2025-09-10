package com.general.mais_um_springboot.controller;

import com.general.mais_um_springboot.model.Produto;
import com.general.mais_um_springboot.service.ProdutoService;
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
    public ResponseEntity<Produto> buscarProdutosPorId(@PathVariable Long id) {
        return produtoService.listaPorId(id)
                .map(ResponseEntity::ok) // Acho que é pra evitar alguma exception, sla
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public void salvarProduto(@RequestBody Produto produto){
        produtoService.salvarProduto(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
