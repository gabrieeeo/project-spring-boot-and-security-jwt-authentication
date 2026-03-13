package me.gabriel.authentication.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.gabriel.authentication.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>{

    // O método findByUsername é uma consulta personalizada que permite buscar um usuário pelo seu nome de usuário (username).
    // Optional: é uma classe do Java que representa um valor que pode estar presente ou ausente. Ele é usado 
    // para evitar o uso de null e fornecer uma maneira mais segura de lidar com valores que podem não existir.
    Optional<UserModel> findByUsername(String username);

}
