SELECT c.titulo as titulocurso,
       c.descripcion as descurso ,
       s.numero as numeroseccion,
       s.titulo as tituloseccion 
FROM seccion s
inner join curso_secciones cs  on cs.secciones_id = s.id
inner join curso c on c.id = cs.curso_id 


--INSERT GRUPO--

INSERT INTO ensenia.grupo (id, descripcion) 
	VALUES ('1', 'AUTISMO')
INSERT INTO ensenia.grupo (id, descripcion) 
	VALUES ('2', 'PROBLEMAS AUDITIVOS')
INSERT INTO ensenia.grupo (id, descripcion) 
	VALUES ('3', 'PROBLEMAS VISUALES')