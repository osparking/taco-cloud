delete from Ingredient_Ref;
delete from Taco;
delete from Taco_Order;

delete from Ingredient;

INSERT INTO INGREDIENT(ID, NAME, TYPE) VALUES ('FLTO', '밀가루 토르티야', 0);
INSERT INTO INGREDIENT(ID, NAME, TYPE) VALUES ('COTO', '옥수수 토르티야', 0);   
INSERT INTO INGREDIENT(ID, NAME, TYPE) VALUES ('GRBF', '다진 소고기', 1);  
INSERT INTO INGREDIENT(ID, NAME, TYPE) VALUES ('CARN', '삶은 돼지고기', 1); 
INSERT INTO INGREDIENT(ID, NAME, TYPE) VALUES ('TMTO', '토마토 깍뚜기', 2); 
INSERT INTO INGREDIENT(ID, NAME, TYPE) VALUES ('LETC', '상추', 2);      
INSERT INTO INGREDIENT(ID, NAME, TYPE) VALUES ('CHED', '체더치즈', 3);     
INSERT INTO INGREDIENT(ID, NAME, TYPE) VALUES ('JACK', '몬테레이 잭', 3);   
INSERT INTO INGREDIENT(ID, NAME, TYPE) VALUES ('SLSA', '살사', 4);        
INSERT INTO INGREDIENT(ID, NAME, TYPE) VALUES ('SRCR', '사워 크림', 4);        
        