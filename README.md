# Projeto Banco Digital
Permite abrir a conta corrente, fazer transferências entre contas e fazer depósito.
O usuário administrador pode listar os correntistas, e ativar/desativar um correntista.

# Dados técnicos
Os usuários para teste são:
1. mariazinha@email.com - Correntista
2. joaozinho@email.com - Correntista
3. mestre@email.com - Superusuário
4. inativo@email.com - Correntista inativo

# Dados de acesso
1. A senha de cada um dos usuários é: 123
2. Ela está criptografada na base de dados no campo senha.
3. A base de dados é o Postgresql, usuário postgres, senha postgres - jdbc:postgresql://localhost:5432/obancobanco
4. Um certificado digital auto-assinado foi gerado com o nome bootsecurity.p12, acessível pela porta 8483.
5. server.ssl.key-store-password=senha123  server.ssl.key-store-type=PKCS12 server.ssl.key-alias=bootsecurity  server.port=8483

# Feito
1. Login no sistema (POST) - https://localhost:8483/api/v1/onboarding/entrar
2. Listagem de correntistas (somente Superusuário) (GET) - https://localhost:8483/api/v1/admin/correntistas

# Pendências
1. Não está verificando se o correntista está ativo ou não.
2. Abertura de conta (POST) - https://localhost:8483/api/v1/onboarding/criar
3. Ativar/Desativar correntista (somente Superusuário) (PATCH) - https://localhost:8483/api/v1/admin/usuarios
4. Extrato (Correntista) (GET) - https://localhost:8483/api/v1/conta/extrato
5. Depósito (POST) - https://localhost:8483/api/v1/conta/depositar
6. Transferência (POST) - https://localhost:8483/api/v1/conta/transferir
7. Alterar dados pessoais (PATCH) - https://localhost:8483/api/v1/conta/editar
