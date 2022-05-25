package net.codejava;

import javax.persistence.*;

/*
    Classe pojo per db, è una classe entity che fa riferimento alla tabella users
    vari parametri column per i singoli parametri
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 45)
    private String email;

    @Column(length = 64)
    private String password;

    @Column(length = 20)
    private String firstName;

    @Column(length = 20)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name="auth_provider")
    private AuthenticationProvider authProvider;

    public AuthenticationProvider getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(AuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
