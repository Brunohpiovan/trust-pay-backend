package com.utfpr.trustpay.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    @NotNull(message = "O campo username é requerido.")
    @Size(max = 255, message = "O username deve ter no maximo 255 caracteres")
    private String username;
    @Email(message = "O email informado é inválido")
    @NotNull(message = "O campo EMAIL é requerido.")
    @Size(max = 255, message = "O EMAIL deve ter no maximo 255 caracteres")
    private String email;
    @NotNull(message = "O campo CPF é requerido.")
    @Size(max = 14, message = "O cpf deve ter no maximo 14 caracteres")
    private String cpf;
    @NotNull(message = "O campo TELEFONE é requerido.")
    @Size(max = 15, message = "O telefone deve ter no maximo 15 caracteres")
    private String celular;
    @NotNull(message = "o campo password é requerido")
    @Size(min = 8, max = 255, message = "A Senha deve ter entre 8 e 100 caracteres")
    private String password;
    @NotNull(message = "o campo confirmPassword é requerido")
    @Size(min = 8, max = 255, message = "A Senha deve ter entre 8 e 100 caracteres")
    private String confirmPassword;
}
