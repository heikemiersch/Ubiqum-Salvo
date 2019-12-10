package com.codeoftheweb.salvo;

//        import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public abstract class GameRepository implements JpaRepository<Game, Long> {
}

