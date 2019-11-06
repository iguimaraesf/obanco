package com.exercicio.banco.validacao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class NovoCorrentista {
	private static final String SENHA_INVALIDO1 = "123";
	private static final String CPF_VALIDO1 = "32068361078";
	private static final String NOME_VALIDO1 = "Carinha Novo";
	private static final String EMAIL_VALIDO1 = "e@mail.com";
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void criarCorrentistaComSenhaInvalida() throws Exception {
		// TODO FAZER A REQUISIÇÃO FUNCIONAR!
		// TODO TESTAR: NÃO DEVE RETORNAR O CAMPO "senha"
		this.mockMvc
			.perform(
					MockMvcRequestBuilders.post("/api/v1/onboarding/criar")
						.param("email", EMAIL_VALIDO1)
						.param("nome", NOME_VALIDO1)
						.param("cpf", CPF_VALIDO1)
						.param("senha", SENHA_INVALIDO1)
						.param("confirmacaoSenha", SENHA_INVALIDO1)
			)
			.andExpect(MockMvcResultMatchers.model().hasErrors())
			.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrorCode("correntista", "senha", "confirmacaoSenha"))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
