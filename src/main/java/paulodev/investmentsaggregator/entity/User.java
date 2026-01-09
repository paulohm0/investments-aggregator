package paulodev.investmentsaggregator.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

// @Entity -> indicar que sera uma entidade da tabela
// @Table -> indicar qual sera o nome da minha tabela
// @Id -> indicar no db que esse atributo sera usado como primary key da tabela
// @GeneratedValue -> gera um novo id para o usuário de forma automatica
// @Column -> posso definir o nome da coluna do atributo
// creation/updateTimestamp -> preenche automaticamente o campo com data e hora em que o registro foi feito

@Entity
@Table(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userID;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updateTimestamp;

    public User() {
        //  JPA/Hibernate exige um construtor vazio, sem ele o hibernate nao consegue instanciar o obj qnds lê o banco de dados
    }

    public User(UUID userID, String username, String email, String password, Instant creationTimestamp, Instant updateTimestamp) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creationTimestamp = creationTimestamp;
        this.updateTimestamp = updateTimestamp;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Instant updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}
