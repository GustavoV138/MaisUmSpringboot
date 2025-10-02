package com.general.mais_um_springboot.service;

import com.general.mais_um_springboot.exceptions.RecursoNaoEncontradoException;
import com.general.mais_um_springboot.model.Produto;
import com.general.mais_um_springboot.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto listarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID: " + id + " não encontrado!"));
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id) {

        if(!produtoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto com ID: " + id + " não encontrado.");
        }
        produtoRepository.deleteById(id);
    }
}
