CREATE VIEW w_usuarios_inativos AS
SELECT 
    u.cpf AS CPF,
    u.nome AS NOME
FROM 
    usuarios u
WHERE 
    u.inativo = 1;
