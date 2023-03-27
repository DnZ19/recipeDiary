-- CREATE SEQUENCE recipe_Diaries_seq START 1;
ALTER SEQUENCE recipe_Diaries_seq RESTART WITH 1;

insert into recipe_Diaries(recipe_Diary_Id, name) values(nextval('recipe_Diaries_seq'), 'DennisRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(nextval('recipe_Diaries_seq'), 'JohnsRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(nextval('recipe_Diaries_seq'), 'KeesRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(nextval('recipe_Diaries_seq'), 'HansRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(nextval('recipe_Diaries_seq'), 'FredRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(nextval('recipe_Diaries_seq'), 'KlaasRecipes');

-- CREATE SEQUENCE nutrition_details_seq START 1;

insert into nutrition_details(nutrition_details_Id, calories, fat, protein, carbs) values(1, 45, 40, 60, 20);
insert into nutrition_details(nutrition_details_Id, calories, fat, protein, carbs) values(2, 100, 140, 160, 120);
insert into nutrition_details(nutrition_details_Id, calories, fat, protein, carbs) values(3, 440, 140, 160, 120);

-- CREATE SEQUENCE recipes_seq START 1;
ALTER SEQUENCE recipes_seq RESTART WITH 1;

insert into recipes(recipe_Id, name, instructions, prep_time, servings, notes, photos, tags, rating, recipe_source, category_name, nutrition_details_Id)
    values (nextval('recipes_seq'), 'Tomato soup', 'Cut tomatoes and boil the shit out of them', '1 hour', 5, 'Put red unions in there for extra flavour', '{url1, url2}', '{healthy, sjizzle}', 7, 'www.soup.com', 'soup', 1);

insert into recipes(recipe_Id, name, instructions, prep_time, servings, notes, photos, tags, rating, recipe_source, category_name, nutrition_details_Id)
values (nextval('recipes_seq'), 'Frikadellen Fritatta', 'Fritatta de dellen!!', '1 hour', 5, 'Put red unions in there for extra flavour', '{url1, url2}', '{healthy, sjizzle}', 7, 'www.soup.com', 'soup', 2);

-- CREATE SEQUENCE ingredient_seq START 1;
ALTER SEQUENCE ingredient_seq RESTART WITH 1;

insert into ingredient(ingredient_id, ingredient_name, quantity, unit, category_name ) values (nextval('ingredient_seq'), 'Tomatoes', 1, 'Kilo', 'Vegetable');
insert into ingredient(ingredient_id, ingredient_name, quantity, unit, category_name ) values (nextval('ingredient_seq'), 'Onion', 0.2, 'Kilo', 'Vegetable');
insert into ingredient(ingredient_id, ingredient_name, quantity, unit, category_name ) values (nextval('ingredient_seq'), 'Leak', 0.2, 'Kilo', 'Vegetable');
insert into ingredient(ingredient_id, ingredient_name, quantity, unit, category_name ) values (nextval('ingredient_seq'), 'Other1', 1, 'Kilo', 'Vegetable');
insert into ingredient(ingredient_id, ingredient_name, quantity, unit, category_name ) values (nextval('ingredient_seq'), 'Other2', 1, 'Kilo', 'Vegetable');