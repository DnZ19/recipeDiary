-- CREATE SEQUENCE recipe_Diaries_seq START 1;

insert into recipe_Diaries(recipe_Diary_Id, name) values(nextval('recipe_Diaries_seq'), 'DennisRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(nextval('recipe_Diaries_seq'), 'JohnsRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(nextval('recipe_Diaries_seq'), 'KeesRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(nextval('recipe_Diaries_seq'), 'HansRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(nextval('recipe_Diaries_seq'), 'FredRecipes');
insert into recipe_Diaries(recipe_Diary_Id, name) values(nextval('recipe_Diaries_seq'), 'KlaasRecipes');

-- CREATE SEQUENCE recipes_seq START 1;

insert into recipes(recipe_Id, name, instructions, prep_time, servings, notes, photos, tags, rating, recipe_source, category_name)
    values (nextval('recipes_seq'), 'Tomato soup', 'Cut tomatoes and boil the shit out of them', 1, 5, 'Put red unions in there for extra flavour', '{url1, url2}', '{healthy, sjizzle}', 7, 'www.soup.com', 'soup');