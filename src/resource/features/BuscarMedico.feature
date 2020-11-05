# language: pt
Funcionalidade: Pesquisa de médicos
 
 @teste1
  Cenário: Pesquisar médico
    Dado que eu sou um cliente
    Quando eu pesquisar um médico especialista em "Pediatria" 
    E informar o estado "Rio de Janeiro" e a cidade "Nova Iguaçu" 
    Então serão exibidos resultados para o Rio de Janeiro
    Mas nenhum resultado para os demais estados
    
