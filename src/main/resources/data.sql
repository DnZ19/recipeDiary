insert into roles(rolename) values ('USER'), ('ADMIN');

insert into users(user_id, username, password) values (100, 'dennis', 'javaan');
-- insert into users_roles(user_id, rolesname) values (100, 'USER');

-- CREATE SEQUENCE recipe_Diaries_seq START 1;
-- ALTER SEQUENCE recipe_Diaries_seq RESTART WITH 1;

insert into recipe_Diaries(recipe_Diary_Id, name) values(10001, 'DennisRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(10002, 'JohnsRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(10003, 'KeesRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(10004, 'HansRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(10005, 'FredRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(10006, 'KlaasRecipes');

-- CREATE SEQUENCE nutrition_details_seq START 1;

insert into nutrition_details(nutrition_details_Id, calories, fat, protein, carbs) values(20001, 45, 40, 60, 20);
insert into nutrition_details(nutrition_details_Id, calories, fat, protein, carbs) values(20002, 100, 140, 160, 120);
insert into nutrition_details(nutrition_details_Id, calories, fat, protein, carbs) values(20003, 440, 140, 160, 120);
insert into nutrition_details(nutrition_details_Id, calories, fat, protein, carbs) values(20004, 440, 140, 160, 120);

-- CREATE SEQUENCE shopping_List_seq START 1;
-- ALTER SEQUENCE shopping_List_seq RESTART WITH 1;

insert into shopping_List(shopping_list_id, shopping_List_Name) values (30001, 'Tomato Soup');
insert into shopping_List(shopping_list_id, shopping_List_Name) values (30002, 'Frikadellen Frittatatatata');
insert into shopping_List(shopping_list_id, shopping_List_Name) values (30003, 'Spinazie Ijs');

-- CREATE SEQUENCE recipes_seq START 1;
-- ALTER SEQUENCE recipes_seq RESTART WITH 1;

insert into recipes(recipe_Id, name, instructions, prep_time, servings, notes, tags, rating, recipe_source, category_name, nutrition_details_Id, shopping_list_id)
    values (40001, 'Tomato soup', 'Cut tomatoes and boil the shit out of them', '1 hour', 5, 'Put red unions in there for extra flavour', '{healthy, sjizzle}', 7, 'www.soup.com', 'soup', 20001, 30001);

insert into recipes(recipe_Id, name, instructions, prep_time, servings, notes, tags, rating, recipe_source, category_name, nutrition_details_Id, shopping_list_id)
values (40002, 'Frikadellen Fritatta', 'Fritatta de dellen!!', '1 hour', 5, 'Put red unions in there for extra flavour', '{healthy, sjizzle}', 7, 'www.soup.com', 'soup', 20002, 30002);

insert into recipes(recipe_Id, name, instructions, prep_time, servings, notes, tags, rating, recipe_source, category_name, nutrition_details_Id, shopping_list_id)
values (40003, 'Groente Ijs', 'spinazie ijs', '1 hour', 5, 'Put red unions in there for extra flavour', '{healthy, sjizzle}', 7, 'www.soup.com', 'soup', 20003, 30003);


insert into ingredient(ingredient_id, ingredient_name, quantity, unit, category_name, recipe_id) values (50001, 'Tomatoes', 1, 'Kilo', 'Vegetable', 40001);
insert into ingredient(ingredient_id, ingredient_name, quantity, unit, category_name, recipe_id) values (50002, 'Onion', 0.2, 'Kilo', 'Vegetable', 40001);
insert into ingredient(ingredient_id, ingredient_name, quantity, unit, category_name, recipe_id) values (50003, 'Leak', 0.2, 'Kilo', 'Vegetable', 40001);
insert into ingredient(ingredient_id, ingredient_name, quantity, unit, category_name, recipe_id) values (50004, 'Other1', 1, 'Kilo', 'Vegetable', 40001);
insert into ingredient(ingredient_id, ingredient_name, quantity, unit, category_name, recipe_id) values (50005, 'Other2', 1, 'Kilo', 'Vegetable', 40001);
insert into ingredient(ingredient_id, ingredient_name, quantity, unit, category_name, recipe_id) values (50006, 'frikadel', 2, 'stuks', 'frituur', 40002);

insert into ingredient_shopping_list(shopping_list_id, ingredient_id) values (30001, 50001);
insert into ingredient_shopping_list(shopping_list_id, ingredient_id) values (30001, 50002);
insert into ingredient_shopping_list(shopping_list_id, ingredient_id) values (30001, 50003);
insert into ingredient_shopping_list(shopping_list_id, ingredient_id) values (30001, 50004);
insert into ingredient_shopping_list(shopping_list_id, ingredient_id) values (30001, 50005);





