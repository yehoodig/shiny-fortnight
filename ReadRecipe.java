//James McConnel 5/6/2020
//Plan:
//     Use RecipeFile Class from the RecipeJar project, to read and write recipe contents from the commmand line.
//
class readRecipe {
   public static void main(String args[]){
      RecipeFile recipe = new RecipeFile("WheatBread.html");
      System.out.println(recipe.getNotes()); 
      System.out.print("Hello world!");
   }
}
