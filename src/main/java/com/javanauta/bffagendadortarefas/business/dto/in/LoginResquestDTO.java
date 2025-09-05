package com.javanauta.bffagendadortarefas.business.dto.in;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResquestDTO {
    private String email;
    private String senha;
}
