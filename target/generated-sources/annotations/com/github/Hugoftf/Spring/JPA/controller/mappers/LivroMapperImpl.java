package com.github.Hugoftf.Spring.JPA.controller.mappers;

import com.github.Hugoftf.Spring.JPA.controller.dto.AutorDTO;
import com.github.Hugoftf.Spring.JPA.controller.dto.CadastroLivroDTO;
import com.github.Hugoftf.Spring.JPA.controller.dto.ResultadoPesquisaLivroDTO;
import com.github.Hugoftf.Spring.JPA.model.GeneroLivro;
import com.github.Hugoftf.Spring.JPA.model.Livro;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-31T13:00:17-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class LivroMapperImpl extends LivroMapper {

    @Autowired
    private AutorMapper autorMapper;

    @Override
    public Livro toEntity(CadastroLivroDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Livro livro = new Livro();

        livro.setPreco( dto.preco() );
        livro.setGenero( dto.genero() );
        livro.setDataPublicacao( dto.dataPublicacao() );
        livro.setTitulo( dto.titulo() );
        livro.setIsbn( dto.isbn() );

        livro.setIdAutor( autorRepository.findById(dto.idAutor()).orElse(null) );

        return livro;
    }

    @Override
    public ResultadoPesquisaLivroDTO toDTO(Livro livro) {
        if ( livro == null ) {
            return null;
        }

        AutorDTO autor = null;
        UUID id = null;
        String isbn = null;
        String titulo = null;
        LocalDate dataPublicacao = null;
        GeneroLivro genero = null;
        BigDecimal preco = null;

        autor = autorMapper.toDTO( livro.getIdAutor() );
        id = livro.getId();
        isbn = livro.getIsbn();
        titulo = livro.getTitulo();
        dataPublicacao = livro.getDataPublicacao();
        genero = livro.getGenero();
        preco = livro.getPreco();

        ResultadoPesquisaLivroDTO resultadoPesquisaLivroDTO = new ResultadoPesquisaLivroDTO( id, isbn, titulo, dataPublicacao, genero, preco, autor );

        return resultadoPesquisaLivroDTO;
    }
}
