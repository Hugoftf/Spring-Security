package com.github.Hugoftf.Spring.JPA.controller.mappers;

import com.github.Hugoftf.Spring.JPA.controller.dto.AutorDTO;
import com.github.Hugoftf.Spring.JPA.model.Autor;
import java.time.LocalDate;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-31T13:00:17-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class AutorMapperImpl implements AutorMapper {

    @Override
    public Autor toEntity(AutorDTO autorDTO) {
        if ( autorDTO == null ) {
            return null;
        }

        Autor autor = new Autor();

        autor.setId( autorDTO.id() );
        autor.setNome( autorDTO.nome() );
        autor.setDataNascimento( autorDTO.dataNascimento() );
        autor.setNacionalidade( autorDTO.nacionalidade() );

        return autor;
    }

    @Override
    public AutorDTO toDTO(Autor autor) {
        if ( autor == null ) {
            return null;
        }

        UUID id = null;
        String nome = null;
        LocalDate dataNascimento = null;
        String nacionalidade = null;

        id = autor.getId();
        nome = autor.getNome();
        dataNascimento = autor.getDataNascimento();
        nacionalidade = autor.getNacionalidade();

        AutorDTO autorDTO = new AutorDTO( id, nome, dataNascimento, nacionalidade );

        return autorDTO;
    }
}
