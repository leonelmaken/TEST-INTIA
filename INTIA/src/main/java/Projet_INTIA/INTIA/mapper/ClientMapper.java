package Projet_INTIA.INTIA.mapper;

import Projet_INTIA.INTIA.dto.ClientDTO;
import Projet_INTIA.INTIA.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper automatique DTO ↔ Entity
 */
@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client toEntity(ClientDTO dto);

    ClientDTO toDto(Client entity);
}