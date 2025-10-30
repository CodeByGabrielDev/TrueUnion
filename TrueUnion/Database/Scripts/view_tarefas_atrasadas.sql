CREATE VIEW w_tarefas_atrasadas AS
SELECT 
    t.desc_tarefa AS DESC_TAREFA,
    t.prazo_tarefa AS PRAZO_TAREFA,
    e.nome_evento AS NOME_EVENTO
FROM 
    tarefas t
JOIN 
    eventos e ON t.id_evento = e.id
WHERE 
    t.atrasada = 1;
